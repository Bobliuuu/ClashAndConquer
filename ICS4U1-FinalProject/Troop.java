import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Write a description of class Troop here.
 * 
 * @author Matthew Gong, Jerry Zhu
 * @version (a version number or a date)
 */
public abstract class Troop extends SuperSmoothMover
{
    protected int health;
    protected int attack;
    protected int statusLength;
    protected int actNumber;
    protected double radius;
    protected double movementSpeed;
    protected double attackSpeed;
    protected boolean isEnemy;
    protected boolean isAttacking;
    protected Actor target;
    protected Queue <Coordinate> path;
    protected SuperStatBar healthBar;
    protected final double[][] bridges = {{127, 385}, {398, 385}, {673, 385}};
    protected final int[][] enemyPath = {{130, 585}, {670, 585}, {400, 680}};
    protected final int[][] myPath = {{130, 170}, {670, 170}, {400, 100}};
    
    public Troop(int health, int attack, double movementSpeed, double attackSpeed, double radius, boolean isEnemy){
        this.health = health;
        this.attack = attack;
        this.movementSpeed = movementSpeed;
        this.attackSpeed = attackSpeed;
        this.radius = radius;
        this.isEnemy = isEnemy;
        this.isAttacking = false;
        this.statusLength = 0;
        healthBar = new SuperStatBar(this.health, this.health, this, 48, 4, 36, Color.GREEN, Color.RED, false);
        actNumber = 0;
    }
    
    protected void addedToWorld(World world){
        getPath();
        getWorld().addObject(healthBar, getX(), getY()+50);
    }
    
    public void getPath(){
        path = new LinkedList <Coordinate> ();
        // Find closest bridge
        double minDis = 1000;
        int closestBridge = 0;
        if (isEnemy){
            for (int i = 0; i < 3; i++){
                if (findDistanceBetween(bridges[i][0], bridges[i][1]) < minDis){
                    minDis = findDistanceBetween(bridges[i][0], bridges[i][1]);
                    closestBridge = i;
                }
            }
            path.add(new Coordinate(bridges[closestBridge][0], bridges[closestBridge][1]));
            if (closestBridge == 0){
                path.add(new Coordinate(enemyPath[0][0], enemyPath[0][1]));
            }
            else if (closestBridge == 2){
                path.add(new Coordinate(enemyPath[1][0], enemyPath[1][1]));
            }
            path.add(new Coordinate(enemyPath[2][0], enemyPath[2][1]));
        }
        else {
            for (int i = 0; i < 3; i++){
                if (findDistanceBetween(bridges[i][0], bridges[i][1]) < minDis){
                    minDis = findDistanceBetween(bridges[i][0], bridges[i][1]);
                    closestBridge = i;
                }
            }
            path.add(new Coordinate(bridges[closestBridge][0], bridges[closestBridge][1]));
            if (closestBridge == 0){
                path.add(new Coordinate(myPath[0][0], myPath[0][1]));
            }
            else if (closestBridge == 2){
                path.add(new Coordinate(myPath[1][0], myPath[1][1]));
            }
            path.add(new Coordinate(myPath[2][0], myPath[2][1]));
        }
    }
    
    public void act()
    {
        animate();
        findTarget();
        moveTowardsTarget();
        checkStatus();
        healthBar.update(this.health);
    }
    
    public void findTarget(){
        target = null;
        ArrayList <Troop> troops = (ArrayList <Troop>) getWorld().getObjects(Troop.class);
        for (Troop troop : troops){
            if (troop.enemy() != isEnemy){
                if (findDistanceBetween(troop, this) <= radius){
                    target = troop;
                    break;
                }
            }
        }
        if (((Level)getWorld()).getMyCastle().getWorld() != null && getWorld() != null){
            if (isEnemy && findDistanceBetween(this, ((Level)getWorld()).getMyCastle()) <= 100){
                target = ((Level)getWorld()).getMyCastle();
            }
        }
        if (((Level)getWorld()).getEnemyCastle().getWorld() != null && getWorld() != null){
            if (!isEnemy && findDistanceBetween(this, ((Level)getWorld()).getEnemyCastle()) <= 100){
                target = ((Level)getWorld()).getEnemyCastle();
            }
        }
    }
    
    public void moveTowardsTarget(){
        if (target != null){ //target exists
            turnTowards(target.getX(), target.getY());
            attack();
        }
        else {
            if (!path.isEmpty()){
                if (Math.abs(this.getX() - path.peek().getX()) <= 2 * movementSpeed && 
                    Math.abs(this.getY() - path.peek().getY()) <= 2 * movementSpeed){
                    path.poll();
                }
                if (!path.isEmpty()){
                    turnTowards(path.peek().getX(), path.peek().getY());
                }
            }
            move(movementSpeed);
        }
    }
    
    public void checkStatus(){
        if(statusLength != 0) {
            statusLength--;
        }
        else{
            // reset limits
        }
    }
    
    public void attack(){
        attackAnimate();
        if (getAttackCounter() == 40){
            if (target instanceof Troop){
                ((Troop)target).subtractHealth(10);
            }
            else if (target instanceof Castle){
                if (!Level.removed){
                    ((Castle)target).subtractHealth(10);
                }
            }
        }
    }
    
    public void subtractHealth(int value){
        health -= value;
        if (health <= 0){
            ((Level)getWorld()).removeObject(this);
        }
    }
    
    public abstract void animate();
    public abstract void attackAnimate();
    public abstract int getAttackCounter();
    
    public void setMovementSpeed(double speed){
        this.movementSpeed = speed;
    }
    
    public void setAttackSpeed(double speed){
        this.attackSpeed = speed;
    }
    
    public void setAttack(int attack){
        this.attack = attack;
    }
    
    public void setHealth(int health){
        this.health = health;
    }
    
    public boolean enemy(){
        return isEnemy;
    }
    
    public boolean setAttacking(boolean isAttacking){
        return isAttacking;
    }
    
    public void setEnemy(boolean isEnemy){
        this.isEnemy = isEnemy;
    }
    
    private void findNextTarget(){
        ArrayList<Actor> possible = (ArrayList<Actor>)getWorld().getObjects(Actor.class);
        double closest = 900;
        
        for(Actor a : possible){
            if(findDistanceBetween(this, a) < closest){
                closest = findDistanceBetween(this, a);
                target = a;
            }
        }
    }
    
    public void getHit(int dmg, String status){
        health -= dmg;
        
        // apply different effects depending on status
        switch(status){
            default:
                // nothing
            case "Slow":
                movementSpeed /= 2;
                attackSpeed /= 2;
                statusLength = 100;
            case "Frozen":
                // stopped temporarily
        }
        
        // check if health has dropped to zero
        if(health <= 0){
            getWorld().removeObject(this);
        }
    }
    
    private double findDistanceBetween(Actor a1, Actor a2){
        return Math.sqrt(Math.pow(a1.getX() - a2.getX(), 2) + Math.pow(a1.getY() - a2.getY(), 2));
    }
    
    private double findDistanceBetween(double x, double y){
        return Math.sqrt(Math.pow(this.getX() - x, 2) + Math.pow(this.getY() - y, 2));
    }
    
}

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
    protected double radius;
    protected double movementSpeed;
    protected double attackSpeed;
    protected boolean isEnemy;
    protected boolean isAttacking;
    protected Actor target;
    protected Queue <Coordinate> path;
    protected double[][] bridges = {{127, 385}, {398, 385}, {673, 385}};
    
    public Troop(int health, int attack, double movementSpeed, double attackSpeed, double radius, boolean isEnemy){
        this.health = health;
        this.attack = attack;
        this.movementSpeed = movementSpeed;
        this.attackSpeed = attackSpeed;
        this.radius = radius;
        this.isEnemy = isEnemy;
        this.isAttacking = false;
        this.statusLength = 0;
    }
    
    protected void addedToWorld(World world){
        getPath();
    }
    
    public void getPath(){
        path = new LinkedList <Coordinate> ();
        // Find closest bridge
        double minDis = 1000;
        int closestBridge = 0;
        for (int i = 0; i < 3; i++){
            if (findDistanceBetween(bridges[i][0], bridges[i][1]) < minDis){
                minDis = findDistanceBetween(bridges[i][0], bridges[i][1]);
                closestBridge = i;
            }
        }
        path.add(new Coordinate(bridges[closestBridge][0], bridges[closestBridge][1]));
        if (closestBridge == 0){
            path.add(new Coordinate(130, 170));
        }
        else if (closestBridge == 2){
            path.add(new Coordinate(670, 170));
        }
        path.add(new Coordinate(400, 100));
    }
    
    public void act()
    {
        animate();
        findTarget();
        moveTowardsTarget();
        checkStatus();
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
        if (isEnemy && findDistanceBetween(this, ((Level)getWorld()).getMyCastle()) <= 100){
            target = ((Level)getWorld()).getMyCastle();
        }
        else if (!isEnemy && findDistanceBetween(this, ((Level)getWorld()).getEnemyCastle()) <= 100){
            target = ((Level)getWorld()).getEnemyCastle();
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
                turnTowards(path.peek().getX(), path.peek().getY());
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
            else if (target instanceof Building){
                ((Building)target).subtractHealth(10);
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

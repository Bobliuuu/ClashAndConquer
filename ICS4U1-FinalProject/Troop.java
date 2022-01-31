import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Troop superclass for all troops.
 * Contains variables and methods to control their movements in the level. 
 * 
 * @author Matthew Gong, Jerry Zhu
 * @version January 2022
 */
public abstract class Troop extends SuperSmoothMover
{
    // Instance variables
    protected int health;
    protected int attack;
    protected int statusLength;
    protected int actNumber;
    protected double radius;
    protected double radius2;
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
    
    /**
     * The most complicated constructor of Troop, with the ability to control health, attack, movement speed, attack speed, and radius. 
     * 
     * @param health         The health of the troop.
     * @param attack         The strength of attack of the troop. 
     * @param movementSpeed  The movement speed of the troop. 
     * @param attackSpeed    The attack speed of the troop. 
     */
    public Troop(int health, int attack, double movementSpeed, double attackSpeed, double radius, double radius2, boolean isEnemy){
        this.health = health;
        this.attack = attack;
        this.movementSpeed = movementSpeed;
        this.attackSpeed = attackSpeed;
        this.radius = radius;
        this.radius2 = radius2;
        this.isEnemy = isEnemy;
        this.isAttacking = false;
        this.statusLength = 0;
        healthBar = new SuperStatBar(this.health, this.health, this, 48, 4, 36, Color.GREEN, Color.RED, false);
        actNumber = 0;
    }
    
    /**
     * Check if the troop has been added to the world. 
     * 
     * @param world     The world the troop has been added to. 
     */
    protected void addedToWorld(World world){
        getPath();
        getWorld().addObject(healthBar, getX(), getY()+50);
    }
    
    /**
     * Gets the path of the troop. 
     */
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
    
    /**
     * Act method to be run each iteration. 
     */
    public void act(){
        if (((Level)getWorld()).finished){
            return;
        }
        animate();
        findTarget();
        moveTowardsTarget();
        checkStatus();
        healthBar.update(this.health);
        Troop overlap = (Troop)getOneIntersectingObject(Troop.class);
        if(overlap != null){
            if(findDistanceBetween(this, overlap) < 10 && overlap.enemy() == isEnemy){
                if(overlap.getX() < getX()){
                    setLocation(getX()+1, getY());
                }
                else{
                    setLocation(getX()-1, getY());
                }
            }
        }
    }
    
    /**
     * Finds the nearest enemy target. 
     */
    public void findTarget(){
        target = null;
        double closestDistance = radius;
        ArrayList <Actor> possibleTargets = (ArrayList <Actor>) getWorld().getObjects(Actor.class);
        for (Actor possible : possibleTargets){
            if(possible instanceof Troop){
                if (findDistanceBetween(possible, this) < closestDistance && ((Troop)possible).enemy() != isEnemy){
                    target = (Troop)possible;
                    closestDistance = findDistanceBetween(possible, this);
                    break;
                }
            }
            else if(possible instanceof Building){
                if (findDistanceBetween(possible, this) < closestDistance && ((Building)possible).enemy() != isEnemy){
                    target = (Building)possible;
                    closestDistance = findDistanceBetween(possible, this);
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
    
    /**
     * Move the troop towards the target. 
     */
    public void moveTowardsTarget(){
        //System.out.println(target);
        if (target != null){ //target exists
            turnTowards(target.getX(), target.getY());
            if (findDistanceBetween(this, target) < radius2){
                attack();
            }
            else {
                move(movementSpeed);
            }
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
    
    /**
     * Check the status of the troop. 
     */
    public void checkStatus(){
        if(statusLength != 0) {
            statusLength--;
        }
        else{
            // reset limits
        }
    }
    
    /**
     * Allow the troop to attack the enemy. 
     */
    public void attack(){
        attackAnimate();
        if (getAttackCounter() == 40){
            if (target instanceof Troop){
                ((Troop)target).subtractHealth(attack);
            }
            else if (target instanceof Building){
                if (!Level.removed){
                    ((Building)target).subtractHealth(attack);
                }
            }
        }
    }
    
    /**
     * Subtract health from the troop once it is hit. 
     */
    public void subtractHealth(int value){
        health -= value;
        if (health <= 0){
            ((Level)getWorld()).removeObject(this);
        }
    }
    
    // Abstract methods to animate the troop's movements and attack
    public abstract void animate();
    public abstract void attackAnimate();
    public abstract int getAttackCounter();
    
    /**
     * Set the movement speed of the troop. 
     * 
     * @param speed     The speed of the troop. 
     */
    public void setMovementSpeed(double speed){
        this.movementSpeed = speed;
    }
    
    /**
     * Set the movement speed of the troop. 
     * 
     * @param speed     The speed of the troop. 
     */
    public void setAttackSpeed(double speed){
        this.attackSpeed = speed;
    }
    
    /**
     * Set the attack strength of the troop. 
     * 
     * @param attack    The attack strength of the troop. 
     */
    public void setAttack(int attack){
        this.attack = attack;
    }
    
    /**
     * Set the health of the troop. 
     * 
     * @param health    The desired health of the troop. 
     */
    public void setHealth(int health){
        this.health = health;
    }
    
    /**
     * Check if the current troop is an enemy. 
     * 
     * @return boolean   If the troop is an enemy. 
     */
    public boolean enemy(){
        return isEnemy;
    }
    
    /**
     * Set the enemy variable of the troop. 
     * 
     * @param isEnemy   Whether or not the troop should be an enemy. 
     */
    public void setEnemy(boolean isEnemy){
        this.isEnemy = isEnemy;
    }
    
    /**
     * Find the next target enemy to target and attack. 
     */
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
    
    /**
     * Called when the troop is hit by a projectile 
     * 
     * @param dmg       The damage taken to the troop. 
     * @param status    The status of the projectile. 
     */
    public void getHit(int dmg, String status){
        health -= dmg;
        // check if health has dropped to zero
        if(health <= 0){
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Find the distance between two actors. 
     * 
     * @param a1     The first actor. 
     * @param a2     The second actor. 
     */
    private double findDistanceBetween(Actor a1, Actor a2){
        return Math.sqrt(Math.pow(a1.getX() - a2.getX(), 2) + Math.pow(a1.getY() - a2.getY(), 2));
    }
    
    /**
     * Find distance between another actor with a certain x and y coordinate. 
     * 
     * @param x      The x coordinate of the actor
     * @param y      The y coordinate of the actor
     */
    private double findDistanceBetween(double x, double y){
        return Math.sqrt(Math.pow(this.getX() - x, 2) + Math.pow(this.getY() - y, 2));
    }
    
    /**
     * Get the width of the troop image. 
     * 
     * @return int    The width of the desired troop's image. 
     */
    public int getWidth(){
        return getImage().getWidth();
    }
}

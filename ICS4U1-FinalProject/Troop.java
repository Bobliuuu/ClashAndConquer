import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

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
    protected int defense;
    protected int statusLength;
    protected double movementSpeed;
    protected double attackSpeed;
    protected boolean isEnemy;
    protected Actor target;
    protected ArrayList<Coordinate> path;
    protected double[][] bridges = {{127, 385}, {673, 385}, {398, 385}};
    
    public Troop(int health, int attack, int defense, double movementSpeed, double attackSpeed, boolean isEnemy){
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.movementSpeed = movementSpeed;
        this.attackSpeed = attackSpeed;
        this.isEnemy = isEnemy;
        statusLength = 0;
        getPath();
    }
    
    public void getPath(){
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
            path.add(new Coordinate(670, 170));
        }
        else if (closestBridge == 2){
            path.add(new Coordinate(670, 590));
        }
        path.add(new Coordinate(397, 170));
    }
    
    public void act()
    {
        // Check if target exists 
        if (true){ //target exists
            turnTowards(target.getX(), target.getY());
        }
        else {
            turnTowards(path.get(0).getX(), path.get(0).getY());
        }
        move(movementSpeed);
        if(statusLength != 0) statusLength--;
        else{
            // reset limits
        }
    }
    
    public abstract void attack();
    public abstract void defend();
    
    public void setMovementSpeed(double speed){
        this.movementSpeed = speed;
    }
    
    public void setAttackSpeed(double speed){
        this.attackSpeed = speed;
    }
    
    public void setAttack(int attack){
        this.attack = attack;
    }
    
    public void setDefense(int defense){
        this.defense = defense;
    }
    
    public void setHealth(int health){
        this.health = health;
    }
    
    public boolean enemy(){
        return isEnemy;
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

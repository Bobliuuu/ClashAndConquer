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
    protected double movementSpeed;
    protected double attackSpeed;
    protected Actor target;
    protected ArrayList<Coordinate> path;
    
    public Troop(int health, int attack, int defense, double movementSpeed, double attackSpeed){
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.movementSpeed = movementSpeed;
        this.attackSpeed = attackSpeed;
        getPath();
    }
    
    public void getPath(){
        
    }
    
    public void act()
    {
        turnTowards(target.getX(), target.getY());
        move(movementSpeed);
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
    
    private double findDistanceBetween(Actor a1, Actor a2){
        return Math.sqrt(Math.pow(a1.getX() - a2.getX(), 2) + Math.pow(a1.getY() - a2.getY(), 2));
    }
}

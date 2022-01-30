import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * The abstract superclass for all spells. Contains functions for attacking all targets within a radius
 * 
 * @author Matthew Gong
 * @version January 2022
 */
public abstract class Spell extends SuperSmoothMover
{
    // instance variables
    protected int attack, radius, lastX, lastY, countdown;
    protected double speed;
    protected boolean fromEnemy;
    
    /***
     * The most complicated constructor, able to customize attack, speed, attack radius, alignment, and target location
     * 
     * @param attack            The amount of damage the Spell deals
     * @param speed             How fast the Spell moves
     * @param radius            Attack radius of the Spell
     * @param isEnemy           Whether the Spell belongs to the enemy
     * @param x                 The x-coordinate of the target location
     * @param y                 The y-coordinate of the target location
     */
    public Spell(int attack, double speed, int radius, boolean fromEnemy, int x, int y){
        this.attack = attack;
        this.speed = speed;
        this.radius = radius;
        this.fromEnemy = fromEnemy;
        this.lastX = x;
        this.lastY = y;
        countdown = 0;
    }
    
    // abstract methods for animation of moving and attacking
    public abstract void animate();
    public abstract void attackAnimate();
    
    /**
     * Act - do whatever the Spell wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // if the attack countdown has not started
        if(countdown == 0){
            // move towards the target location
            animate();
            turnTowards(lastX, lastY);
            move(speed);
            checkPosition();
        }
        else{
            // animate the attack
            attackAnimate();
            countdown--;
            // countdown has been finished, remove Spell from finish
            if(countdown == 1) ((Level)getWorld()).removeObject(this);
        }
    }
    
    /**
     * Checks where the Spell is currently and if it is in the correct position, it deals damage to everything within the radius
     */
    public void checkPosition(){
        // if the spell is at the designated location
        if(findDistanceBetween(lastX, lastY) < speed){
            // get all possible target within the radius
            ArrayList<Actor> targets = (ArrayList<Actor>)getObjectsInRange(radius, Actor.class);
            
            // loop through all the targets
            for(int i = 0; i < targets.size(); i++){
                Actor current = targets.get(i);
                
                // if they can be damaged and they are not on the same side then damage them
                if(current instanceof Troop){
                    if(((Troop)current).enemy() != fromEnemy){
                        ((Troop)current).subtractHealth(attack);
                    }
                }
                else if(current instanceof Building){
                    if(((Building)current).enemy() != fromEnemy){
                        ((Building)current).subtractHealth(attack);
                    }
                }
            }
            // start the attack animation countdown
            countdown = 36;
        }
    }
    
    /**
     * Method for finding the distance between the Spell and a position on the map
     */
    private double findDistanceBetween(double x, double y){
        return Math.sqrt(Math.pow(this.getX() - x, 2) + Math.pow(this.getY() - y, 2));
    }
}

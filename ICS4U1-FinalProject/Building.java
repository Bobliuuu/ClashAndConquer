import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Building here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Building extends SuperSmoothMover
{
    // Instance variables
    protected int health;
    protected boolean isEnemy;
    /**
     * Act - do whatever the Building wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public void subtractHealth(int value){
        health -= value;
        if (health <= 0){
            ((Level)getWorld()).removeObject(this);
        }
    }
}

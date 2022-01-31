import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Building Class
 * <p>
 * Abstract superclass for buildings to be placed in the level. 
 * 
 * @author Jerry Zhu
 * @version January 2022
 */
public abstract class Building extends SuperSmoothMover
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
    
    // Abstract method to subtract health from building on attack
    public abstract void subtractHealth(int health);
    
    /**
     * Checks if the building is an enemy.
     * 
     * @return boolean   If the building is an enemy or not. 
     */
    public boolean enemy(){
        return isEnemy;
    }
}

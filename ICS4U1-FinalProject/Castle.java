import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Castle Class
 * <p>
 * A subclass of the building superclass. A castle is the primary target of every level. 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Castle extends Building
{
    // Instance variables
    private boolean isEnemy;
    
    /**
     * Default constructor for the castle class
     * 
     * @param isEnemy    Whether the castle belongs to the enemy or not
     */
    public Castle(boolean isEnemy){
        this.isEnemy = isEnemy;
        if (isEnemy){
            setImage("enemytower.png");
        }
        else {
            setImage("mytower.png");
        }
        getImage().scale(150, 150);
    }
    
    /**
     * Similar to above, but with the ability to customize width and height of castle
     * 
     * @param isEnemy    Whether the castle belongs to the enemy or not
     * @param width      The desired width of the castle
     * @param width      The desired height of the castle
     */
    public Castle(boolean isEnemy, int width, int height){
        this.isEnemy = isEnemy;
        if (isEnemy){
            setImage("enemytower.png");
        }
        else {
            setImage("mytower.png");
        }
        getImage().scale(width, height);
    }
    
    /**
     * Act - do whatever the Castle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}

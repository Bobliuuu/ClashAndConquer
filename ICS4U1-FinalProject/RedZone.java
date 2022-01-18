import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * RedZone class
 * <p>
 * Spawns a red rectangle to indicate where troops can be placed. 
 * 
 * @author Jerry Zhu
 * @version December 2022
 */
public class RedZone extends Actor
{
    // Instance variables
    private GreenfootImage redZone;
    
    /**
     * Default constructor for RedZone class.
     */
    public RedZone(){
        redZone = new GreenfootImage("redzone.png");
        setToNone();
    }
    
    /**
     * Act - do whatever the RedZone wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    }
    
    /**
     * Add the redzone on prompt.
     */
    public void setToRedZone(){
        setImage(redZone);
        redZone.scale(800, 400);
    }
    
    /**
     * Remove the redzone when prompted.
     */
    public void setToNone(){
        setImage((GreenfootImage)(null));
    }
}

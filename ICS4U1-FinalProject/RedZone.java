import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RedZone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RedZone extends Actor
{
    private GreenfootImage redZone = new GreenfootImage("redzone.png");
    
    public RedZone(){
        setToNone();
    }
    
    /**
     * Act - do whatever the RedZone wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    }
    
    public void setToRedZone(){
        setImage(redZone);
        redZone.scale(800, 400);
    }
    
    public void setToNone(){
        setImage((GreenfootImage)(null));
    }
}

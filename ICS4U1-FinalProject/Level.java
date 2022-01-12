import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level extends World
{
    private Image levelMap;
    /**
     * Constructor for objects of class Level.
     * 
     */
    public Level()
    {    
        // Create a new world with 800 x 836 cells with a cell size of 1x1 pixels.
        super(800, 836, 1); 
        levelMap = new Image(new GreenfootImage("background.png"));
        addObject(levelMap, getWidth()/2, getHeight()/2);
        
        Castle myCastle = new Castle(false);
        addObject(myCastle, 400, 720);
        
        Castle enemyCastle = new Castle(true, 130, 130);
        addObject(enemyCastle, 400, 60);
    }
}

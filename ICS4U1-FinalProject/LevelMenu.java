import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelMenu extends World
{
    private Image map;
    
    /**
     * Constructor for objects of class LevelMenu.
     * 
     */
    public LevelMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 900, 1);
        map = new Image(new GreenfootImage("mapselect.png"));
        addObject(map, getWidth()/2, getHeight()/2);
    }
}

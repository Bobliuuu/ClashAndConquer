import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelMenu extends World
{
    // Instance variables
    private Image map;
    
    /**
     * Constructor for objects of class LevelMenu.
     * 
     */
    public LevelMenu()
    {    
        // Create a new world with 1000 x 800 cells with a cell size of 1x1 pixels.
        super(1000, 650, 1);
        map = new Image(new GreenfootImage("mapselect.png"));
        addObject(map, getWidth()/2, getHeight()/2);
    }
    
    public void act(){
        checkHover();
        checkClick();
    }
    
    public void checkHover(){
        
    }
    
    public void checkClick(){
        //If the user has clicked the start buttonm bring them to the level select menu
        if (Greenfoot.mouseClicked(map)) {
            Level level = new Level();
            Greenfoot.setWorld(level);
        }
    }
}

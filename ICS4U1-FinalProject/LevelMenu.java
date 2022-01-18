import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

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
    private Circle oneCircle;
    private Rectangle oneRectangle, twoRectangle, threeRectangle;
    private SuperTextBox textBox;
    
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
        
        oneCircle = new Circle(Color.BLUE, 30, true, 1);
        addObject(oneCircle, 200, 450);
        
        oneRectangle = new Rectangle(Color.GREEN, 40, 40, 3, true, 45);
        addObject(oneRectangle, 200, 425);

        twoRectangle = new Rectangle(Color.GREEN, 40, 40, 3, true, 45);
        addObject(twoRectangle, 300, 425);
        
        threeRectangle = new Rectangle(Color.GREEN, 40, 40, 3, true, 45);
        addObject(threeRectangle, 400, 425);
        
        showText("1", 200, 425);
        showText("2", 300, 425);
        showText("3", 400, 425);
    }
    
    public void act(){
        checkClick();
    }
    
    public void checkClick(){
        if (Greenfoot.mouseClicked(oneRectangle)) {
            Level level = new Level(1);
            Greenfoot.setWorld(level);
        }
        else if (Greenfoot.mouseClicked(twoRectangle)) {
            Level level = new Level(2);
            level.setIfWeak(true);
            Greenfoot.setWorld(level);
        }
        else if (Greenfoot.mouseClicked(threeRectangle)) {
            Level level = new Level(2);
            Greenfoot.setWorld(level);
        }
    }
}

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
    private Circle oneCircle, twoCircle, threeCircle, fourCircle, fiveCircle, sixCircle;
    private Rectangle oneRectangle, twoRectangle, threeRectangle, fourRectangle, fiveRectangle;

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
        
        twoCircle = new Circle(Color.BLUE, 30, true, 1);
        addObject(twoCircle, 356, 386);
        
        //threeCircle = new Circle(Color.BLUE, 30, true, 1);
        //addObject(threeCircle, 502, 319);
        
        fourCircle = new Circle(Color.BLUE, 30, true, 1);
        addObject(fourCircle, 276, 296);
        
        sixCircle = new Circle(Color.BLUE, 30, true, 1);
        addObject(sixCircle, 26, 269);
        
        fiveCircle = new Circle(Color.BLUE, 30, true, 1);
        addObject(fiveCircle, 149, 280);
        
        oneRectangle = new Rectangle(Color.GREEN, 40, 40, 3, true, 45);
        addObject(oneRectangle, 200, 425);

        twoRectangle = new Rectangle(Color.GREEN, 40, 40, 3, true, 45);
        addObject(twoRectangle, 356, 356);
        
        threeRectangle = new Rectangle(Color.GREEN, 40, 40, 3, true, 45);
        addObject(threeRectangle, 276, 268);
        
        fourRectangle = new Rectangle(Color.GREEN, 40, 40, 3, true, 45);
        addObject(fourRectangle, 149, 255);
        
        fiveRectangle = new Rectangle(Color.GREEN, 40, 40, 3, true, 45);
        addObject(fiveRectangle, 26, 245);
        
        showText("1", 200, 425);
        showText("2", 356, 356);
        showText("3", 276, 268);
        showText("4", 149, 255);
        showText("5", 26, 245);
    }
    
    public void act(){
        //checkMousePosition();
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
        else if (Greenfoot.mouseClicked(fourRectangle)) {
            Level level = new Level(3);
            level.setIfWeak(true);
            Greenfoot.setWorld(level);
        }
        else if (Greenfoot.mouseClicked(fiveRectangle)) {
            End end = new End();
            Greenfoot.setWorld(end);
        }
    }
    
    public void checkMousePosition(){
        if (Greenfoot.mouseClicked(map)){
            System.out.println(Greenfoot.getMouseInfo().getX() + " " + Greenfoot.getMouseInfo().getY());
        }
    }
}

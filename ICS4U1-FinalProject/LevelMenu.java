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
    private Image backButton;
    private UserInfo user;
    private GreenfootSound music;
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
        
        backButton = new Image(new GreenfootImage("Buttons/backbutton.png"));
        backButton.getImage().scale(80, 50);
        addObject(backButton, 80, 50);
        
        showText("1", 200, 425);
        showText("2", 356, 356);
        showText("3", 276, 268);
        showText("4", 149, 255);
        showText("5", 26, 245);
    }
    
    public void started(){
        if (UserInfo.isStorageAvailable()){
            user = UserInfo.getMyInfo();
            if (user.getInt(3) == 0){
                if (music != null){
                    music.stop();
                }
            }
            else {
                if (music != null){
                    music.stop();
                }
                music = new GreenfootSound("mainsong" + user.getInt(3) + ".mp3");
                music.play();
            }
        }
    }
    
    public void act(){
        //checkMousePosition();
        checkClick();
    }
    
    public void checkClick(){
        if (Greenfoot.mouseClicked(oneRectangle)){
            if (music != null){
                music.stop();
            }
            Level level = new Level(1);
            level.started();
            Greenfoot.setWorld(level);
        }
        else if (Greenfoot.mouseClicked(twoRectangle)){
            if (music != null){
                music.stop();
            }
            Level level = new Level(2);
            level.setIfWeak(true);
            level.started();
            Greenfoot.setWorld(level);
        }
        else if (Greenfoot.mouseClicked(threeRectangle)){
            if (music != null){
                music.stop();
            }
            Level level = new Level(2);
            level.started();
            Greenfoot.setWorld(level);
        }
        else if (Greenfoot.mouseClicked(fourRectangle)){
            if (music != null){
                music.stop();
            }
            Level level = new Level(3);
            level.setIfWeak(true);
            level.started();
            Greenfoot.setWorld(level);
        }
        else if (Greenfoot.mouseClicked(fiveRectangle)){
            if (music != null){
                music.stop();
            }
            End end = new End();
            Greenfoot.setWorld(end);
        }
        else if (Greenfoot.mouseClicked(backButton)){
            if (music != null){
                music.stop();
            }
            Start start = new Start();
            start.started();
            Greenfoot.setWorld(start);
        }
    }
    
    public void checkMousePosition(){
        if (Greenfoot.mouseClicked(map)){
            System.out.println(Greenfoot.getMouseInfo().getX() + " " + Greenfoot.getMouseInfo().getY());
        }
    }
}

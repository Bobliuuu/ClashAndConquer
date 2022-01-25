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
    //private ArrayList <Circle> circles;
    private Rectangle oneRectangle, twoRectangle, threeRectangle, fourRectangle, fiveRectangle;
    private int[][] circleCoordinates = {{200, 450}, {307, 414}, {403, 365}, {276, 296}, {26, 269}, {179, 290}, {308, 564}, {443, 586}, 
                                        {601, 545}, {733, 556}, {829, 605}, {916, 566}, {925, 426}, {978, 315}, {904, 282}, {817, 253}, 
                                        {905, 190}, {921, 114}, {812, 91}, {691, 94}, {626, 117}, {554, 157}, {478, 225}, {403, 285}, 
                                        {507, 324}, {98, 219}};
    private Image backButton;
    private UserInfo user;
    private GreenfootSound music;
    private SuperTextBox textBox;
    private int level;
    
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
        
        for (int i = 0; i < 26; i++){
            Circle temp = new Circle(Color.BLUE, 30, true, 1);
            addObject(temp, circleCoordinates[i][0], circleCoordinates[i][1]);
        }
        
        if (UserInfo.isStorageAvailable()){
            user = UserInfo.getMyInfo();
            level = user.getScore();
            //level = 5;
        }
        else {
            level = 1;
        }
        
        oneRectangle = new Rectangle(Color.GREEN, 40, 40, 3, true, 45);
        addObject(oneRectangle, 200, 425);
        showText("1", 200, 425);
            
        if (level >= 2){
            twoRectangle = new Rectangle(Color.GREEN, 40, 40, 3, true, 45);
            addObject(twoRectangle, 356, 356);
            showText("2", 356, 356);
        }
            
        if (level >= 3){
            threeRectangle = new Rectangle(Color.GREEN, 40, 40, 3, true, 45);
            addObject(threeRectangle, 276, 268);
            showText("3", 276, 268);
        }
            
        if (level >= 4){
            fourRectangle = new Rectangle(Color.GREEN, 40, 40, 3, true, 45);
            addObject(fourRectangle, 149, 255);
            showText("4", 149, 255);
        }
        
        if (level >= 5){
            fiveRectangle = new Rectangle(Color.GREEN, 40, 40, 3, true, 45);
            addObject(fiveRectangle, 26, 245);
            showText("5", 26, 245);
        }
            
        backButton = new Image(new GreenfootImage("Buttons/backbutton.png"));
        backButton.getImage().scale(80, 50);
        addObject(backButton, 80, 50);
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
        else if (level >= 2 && Greenfoot.mouseClicked(twoRectangle)){
            if (music != null){
                music.stop();
            }
            Level level = new Level(2);
            level.setIfWeak(true);
            level.started();
            Greenfoot.setWorld(level);
        }
        else if (level >= 3 && Greenfoot.mouseClicked(threeRectangle)){
            if (music != null){
                music.stop();
            }
            Level level = new Level(2);
            level.started();
            Greenfoot.setWorld(level);
        }
        else if (level >= 4 && Greenfoot.mouseClicked(fourRectangle)){
            if (music != null){
                music.stop();
            }
            Level level = new Level(3);
            level.setIfWeak(true);
            level.started();
            Greenfoot.setWorld(level);
        }
        else if (level >= 5 && Greenfoot.mouseClicked(fiveRectangle)){
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

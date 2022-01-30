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
    private ArrayList <Rectangle> rectangles; 
    private Rectangle endButton;
    private int[][] circleCoordinates = {{200, 450}, {307, 414}, {403, 365}, {276, 296}, {26, 269}, {205, 270}, {308, 564}, {443, 586}, 
                                        {601, 545}, {733, 556}, {829, 605}, {916, 575}, {925, 426}, {970, 315}, {904, 282}, {817, 253}, 
                                        {905, 190}, {921, 114}, {812, 91}, {691, 94}, {626, 117}, {554, 157}, {478, 225}, {413, 296}, 
                                        {507, 324}, {122, 282}};
    private int[][] rectangleCoordinates = {{508, 300}, {200, 425}, {443, 560}, {733, 530}, {916, 550}, {970, 290}, {817, 226}, {905, 166}, 
                                            {812, 67}, {691, 70}, {449, 159}, {413, 250}, {276, 270}, {122, 258}, {26, 244}};
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
        map = new Image(new GreenfootImage("Worlds/mapselect.png"));
        addObject(map, getWidth()/2, getHeight()/2);
        
        for (int i = 0; i < 26; i++){
            Circle temp = new Circle(Color.BLUE, 30, true, 1);
            addObject(temp, circleCoordinates[i][0], circleCoordinates[i][1]);
        }
        
        if (UserInfo.isStorageAvailable()){
            user = UserInfo.getMyInfo();
            level = user.getScore();
            level = 20;
        }
        else {
            level = 1;
        }
        
        rectangles = new ArrayList <Rectangle> ();
        
        for (int i = 1; i <= 14; i++){
            if (level >= i){
                Rectangle temp = new Rectangle(Color.GREEN, 40, 40, 3, true, 45);
                rectangles.add(temp);
                addObject(temp, rectangleCoordinates[i-1][0], rectangleCoordinates[i-1][1]);
                showText(Integer.toString(i), rectangleCoordinates[i-1][0], rectangleCoordinates[i-1][1]);
            }
        }
        endButton = new Rectangle(Color.GREEN, 40, 40, 3, true, 45);
        rectangles.add(endButton);
        addObject(endButton, rectangleCoordinates[14][0], rectangleCoordinates[14][1]);
        showText(Integer.toString(15), rectangleCoordinates[14][0], rectangleCoordinates[14][1]);
        
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
            if (music != null){
                music.setVolume(user.getInt(2));
            }
        }
    }
    
    public void act(){
        //checkMousePosition();
        checkClick();
    }
    
    public void checkClick(){
        for (int i = 1; i <= 13; i++){
            if (level >= i){
                if (Greenfoot.mouseClicked(rectangles.get(i-1))){
                    if (music != null){
                        music.stop();
                    }
                    Level level = new Level(i);
                    level.started();
                    Greenfoot.setWorld(level);
                } 
            }
        }
        if (Greenfoot.mouseClicked(backButton)){
            if (music != null){
                music.stop();
            }
            Start start = new Start();
            start.started();
            Greenfoot.setWorld(start);
        }
        else if (Greenfoot.mouseClicked(endButton)){
            if (music != null){
                music.stop();
            }
            End end = new End();
            end.started();
            Greenfoot.setWorld(end);
        }
    }
    
    public void checkMousePosition(){
        if (Greenfoot.mouseClicked(map)){
            System.out.println(Greenfoot.getMouseInfo().getX() + " " + Greenfoot.getMouseInfo().getY());
        }
    }
}

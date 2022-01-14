import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Start 
 * 
 * @author Jerry Zhu
 * @version January 2022
 */
public class Start extends World
{
    // Instance variables
    private Image title;
    private Image startButton;
    private Image settingsButton;
    private Image shopButton;
    private Image statsButton;
    private Image instructionsButton;
    private Image background;
    private Image myKing;
    private Image enemyKing;
    
    private TextButton myCastle;
    private TextButton enemyCastle;
    
    private TextLabel textLabel;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Start()
    {    
        // Create a new world with 900 x 600 cells with a cell size of 1x1 pixels.
        super(900, 600, 1); 
        
        background = new Image(new GreenfootImage("startbackground.png"));
        addObject(background, getWidth() / 2, getHeight() / 2);
        
        myKing = new Image(new GreenfootImage("myking.png"));
        myKing.getImage().scale(150, 200);
        addObject(myKing, 100, 150);
        
        enemyKing = new Image(new GreenfootImage("enemyking.png"));
        addObject(enemyKing, 800, 150);
        
        // Initialize static images to the World
        title = new Image(new GreenfootImage("starttext.png"));
        addObject(title, getWidth() / 2, 150);
        
        settingsButton = new Image(new GreenfootImage("Buttons/settings.png"));
        settingsButton.getImage().scale(250, 100);
        addObject(settingsButton, 150, 350);
        
        shopButton = new Image(new GreenfootImage("Buttons/shop.png"));
        shopButton.getImage().scale(250, 100);
        addObject(shopButton, 450, 350);
        
        statsButton = new Image(new GreenfootImage("Buttons/stats.png"));
        statsButton.getImage().scale(250, 100);
        addObject(statsButton, 750, 350);
        
        // Initialize the image of the start button and add it to the world
        instructionsButton = new Image(new GreenfootImage("Buttons/instructions.png"));
        instructionsButton.getImage().scale(320, 100);
        addObject(instructionsButton, 280, 500);
        
        // Initialize the image of the start button and add it to the world
        startButton = new Image(new GreenfootImage("Buttons/play.png"));
        startButton.getImage().scale(200, 100);
        addObject(startButton, 600, 500);
        
        //myCastle = new TextButton("Castle", 15, false, new Font ("Verdana", false , false , 30));
        //enemyCastle = new TextButton("Enemy", 15, false, new Font ("Verdana", false , false , 30));
        
        //addObject(myCastle, 305, 386);
        //addObject(enemyCastle, 305, 34);
    }
    
    public void act(){
        checkHover();
        checkClick();
    }
    
    public void checkHover(){
        
    }
    
    public void checkClick(){
        //If the user has clicked the start buttonm bring them to the level select menu
        if (Greenfoot.mouseClicked(startButton)) {
            LevelMenu levelMenu = new LevelMenu();
            Greenfoot.setWorld(levelMenu);
        }
        if (Greenfoot.mouseClicked(settingsButton)){
            Settings settings = new Settings();
            Greenfoot.setWorld(settings);
        }
    }
}

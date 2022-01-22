import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Start class 
 * <p>
 * Starting World that is displayed on run. Displays buttons to play the game, and edit settings. 
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
    private UserInfo user;
    private GreenfootSound music;
    
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
        
        if (UserInfo.isStorageAvailable()){
            user = UserInfo.getMyInfo();
            String check = user.getString(0);
            if (check.equals("") || check == null){
                user.setScore(1); // Level
                user.setInt(0, 0); // Gems
                user.setInt(1, 1); // Difficulty
                user.setInt(2, 0); // Volume
                user.setInt(3, 0); // Music type
                user.setString(0, "120 12 1 3 80 100 10 1 3 80");
                user.store();
            }
        }
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
        checkHover();
        checkClick();
    }
    
    public void checkHover(){
        
    }
    
    public void checkClick(){
        //If the user has clicked the start buttonm bring them to the level select menu
        if (Greenfoot.mouseClicked(startButton)) {
            LevelMenu levelMenu = new LevelMenu();
            if (music != null){
                music.stop();
            }
            levelMenu.started();
            Greenfoot.setWorld(levelMenu);
        }
        else if (Greenfoot.mouseClicked(settingsButton)){
            Settings settings = new Settings();
            if (music != null){
                music.stop();
            }
            settings.started();
            Greenfoot.setWorld(settings);
        }
        else if (Greenfoot.mouseClicked(shopButton)){
            Shop shop = new Shop();
            if (music != null){
                music.stop();
            }
            shop.started();
            Greenfoot.setWorld(shop);
        }
    }
}

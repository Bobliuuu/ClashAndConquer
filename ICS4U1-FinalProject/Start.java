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
    
    private TextButton myCastle;
    private TextButton enemyCastle;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Start()
    {    
        // Create a new world with 900 x 600 cells with a cell size of 1x1 pixels.
        super(900, 600, 1); 
        
        // Initialize the image of the title and add it to the world
        title = new Image(new GreenfootImage("testtitle.png"));
        addObject(title, getWidth() / 2, 200);
        
        // Initialize the image of the start button and add it to the world
        startButton = new Image(new GreenfootImage("teststart.png"));
        startButton.getImage().scale(200, 100);
        addObject(startButton, getWidth() / 2, 500);
        
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
    }
}

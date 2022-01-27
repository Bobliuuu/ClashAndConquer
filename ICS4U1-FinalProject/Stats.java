import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stats here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stats extends World
{
    private Image backButton;
    private TextButton playerStats;
    
    /**
     * Constructor for objects of class Stats.
     * 
     */
    public Stats()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1);
        
        backButton = new Image(new GreenfootImage("Buttons/backbutton.png"));
        backButton.getImage().scale(80, 50);
        addObject(backButton, 80, 50);
        
        
        TextButton playerStats = new TextButton("Player Stats", 5, 190, true, Color.WHITE, Color.BLACK, Color.BLUE, Color.WHITE, Color.BLACK, new Font ("Verdana",true ,false ,14));
        addObject(playerStats, 450, 300);
        
    }
    public void act(){
        checkClick();
    }
    public void checkClick(){
        if (Greenfoot.mouseClicked(backButton)){
            Start start = new Start();
            start.started();
            Greenfoot.setWorld(start);
        }
        else if (Greenfoot.mouseClicked(playerStats)){
            PlayerStats playerStats = new PlayerStats();
            playerStats.started();
            Greenfoot.setWorld(playerStats);
        }
    }
}

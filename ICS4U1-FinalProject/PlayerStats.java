import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerStats here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerStats extends World
{
    private Image backButton;
    private SuperTextBox level;

    /**
     * Constructor for objects of class PlayerStats.
     * 
     */
    public PlayerStats()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1); 

        backButton = new Image(new GreenfootImage("Buttons/backbutton.png"));
        backButton.getImage().scale(80, 50);
        addObject(backButton, 80, 50);

        level = new SuperTextBox(new String[]{"0"}, Color.BLUE, Color.BLACK, new Font("Open Sans", false, false, 13), true,300, 0, Color.WHITE);
        //level = new SuperTextBox(new String[]{"Colour-ify"}, Color.DARK_GRAY, Color.WHITE, new Font("Comic Sans MS", false, false, 13), true, 74, 1, Color.WHITE);
        addObject(level, 680, 175);

    }
    public void act(){
        checkClick();
    }
    public void checkClick(){
        if (Greenfoot.mouseClicked(backButton)){
            Stats stats = new Stats();
            stats.started();
            Greenfoot.setWorld(stats);
        }
    }
}
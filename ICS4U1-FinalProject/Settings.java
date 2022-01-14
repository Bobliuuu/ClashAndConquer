import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Settings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Settings extends World
{
    private Image background;
    private Image backButton;
    private Image settingsText;
    private Image lowVolumeButton;
    private Image medVolumeButton;
    private Image highVolumeButton;
    private Image changeMusic;
    private Image easy;
    private Image medium;
    private Image hard;
    private Slider slider;
    private TextLabel textLabel;
    
    /**
     * Constructor for objects of class Settings.
     * 
     */
    public Settings()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1); 
        
        background = new Image(new GreenfootImage("settingsbackground.png"));
        addObject(background, 200, 300);
        
        backButton = new Image(new GreenfootImage("Buttons/backbutton.png"));
        backButton.getImage().scale(80, 50);
        addObject(backButton, 80, 50);
        
        slider = new Slider(300, 30, false);
        addObject(slider, 600, 100);
        
        Font font = new Font("Courier New", true, false, 20);
        textLabel = new TextLabel("0", font);
        addObject(textLabel, 780, 140);
    }
    
    public void act(){
        checkClick();
    }
    
    public void checkClick(){
        if (Greenfoot.mouseClicked(backButton)){
            Start start = new Start();
            Greenfoot.setWorld(start);
        }
    }
    
    public void updateVolumeText(String value){
        if (textLabel != null){
            textLabel.update(value);
        }
    }
}

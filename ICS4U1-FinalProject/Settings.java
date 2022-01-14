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
        
        easy = new Image(new GreenfootImage("Buttons/easy.png"));
        easy.getImage().scale(200, 100);
        addObject(easy, 250, 250);
        
        medium = new Image(new GreenfootImage("Buttons/medium.png"));
        medium.getImage().scale(250, 100);
        addObject(medium, 250, 385);
        
        hard = new Image(new GreenfootImage("Buttons/hard.png"));
        hard.getImage().scale(200, 100);
        addObject(hard, 250, 520);
        
        changeMusic = new Image(new GreenfootImage("Buttons/changemusic.png"));
        changeMusic.getImage().scale(220, 100);
        addObject(changeMusic, 650, 450);
        
        slider = new Slider(300, 30, false);
        addObject(slider, 650, 280);
        
        Font font = new Font("Courier New", true, false, 20);
        textLabel = new TextLabel("0", font);
        addObject(textLabel, 830, 320);
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

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
    private Image difficultyText;
    private Image volumeText;
    private Image changeMusic;
    private Image easy;
    private Image medium;
    private Image hard;
    private Image gems; 
    private Slider slider;
    private TextLabel sliderLabel;
    private TextLabel gemsLabel;
    
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
        
        settingsText = new Image(new GreenfootImage("settingstext.png"));
        addObject(settingsText, 440, 100);
        
        difficultyText = new Image(new GreenfootImage("difficultytext.png"));
        difficultyText.getImage().scale(400, 130);
        addObject(difficultyText, 250, 200);
        
        volumeText = new Image(new GreenfootImage("volumetext.png"));
        volumeText.getImage().scale(350, 90);
        addObject(volumeText, 630, 270);
        
        backButton = new Image(new GreenfootImage("Buttons/backbutton.png"));
        backButton.getImage().scale(80, 50);
        addObject(backButton, 80, 50);
        
        easy = new Image(new GreenfootImage("Buttons/easy.png"));
        easy.getImage().scale(150, 75);
        addObject(easy, 250, 300);
        
        medium = new Image(new GreenfootImage("Buttons/medium.png"));
        medium.getImage().scale(220, 80);
        addObject(medium, 250, 410);
        
        hard = new Image(new GreenfootImage("Buttons/hard.png"));
        hard.getImage().scale(150, 75);
        addObject(hard, 250, 520);
        
        changeMusic = new Image(new GreenfootImage("Buttons/changemusic.png"));
        changeMusic.getImage().scale(220, 100);
        addObject(changeMusic, 650, 480);
        
        slider = new Slider(300, 30, false);
        addObject(slider, 650, 360);
        
        Font font = new Font("Courier New", true, false, 20);
        sliderLabel = new TextLabel("0", font);
        addObject(sliderLabel, 830, 400);
        
        gems = new Image(new GreenfootImage("gem.png"));
        gems.getImage().scale(100, 50);
        addObject(gems, 810, 50);
        
        Font font2 = new Font("Verdana", true, false, 20);
        gemsLabel = new TextLabel("0", font2);
        addObject(gemsLabel, 840, 90);
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
        if (sliderLabel != null){
            sliderLabel.update(value);
        }
    }
}

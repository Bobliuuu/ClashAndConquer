import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Settings here.
 * 
 * @author Jerry Zhu
 * @version January 2022
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
    private SuperTextBox sliderLabel;
    private SuperTextBox gemsLabel;
    private UserInfo user;
    private GreenfootSound music;
    private static final Color transparent = new Color(0, 0, 0, 0);
    
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
        addObject(settingsText, getWidth()/2, 150);
        
        volumeText = new Image(new GreenfootImage("volumetext.png"));
        volumeText.getImage().scale(350, 90);
        addObject(volumeText, getWidth()/2, 270);
        
        backButton = new Image(new GreenfootImage("Buttons/backbutton.png"));
        backButton.getImage().scale(80, 50);
        addObject(backButton, 60, 50);
        
        changeMusic = new Image(new GreenfootImage("Buttons/changemusic.png"));
        changeMusic.getImage().scale(220, 100);
        addObject(changeMusic, getWidth()/2, 480);
        
        slider = new Slider(300, 30, false);
        addObject(slider, getWidth()/2, 360);
        
        Font font = new Font("Courier New", true, false, 20);
        sliderLabel = new SuperTextBox("0", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(sliderLabel, getWidth()/2 + 180, 360);
        
        if (UserInfo.isStorageAvailable()){
            user = UserInfo.getMyInfo();
            sliderLabel.update(Integer.toString(user.getInt(2)));
            slider.setValue(user.getInt(2));
        }
        
        gems = new Image(new GreenfootImage("gem.png"));
        gems.getImage().scale(140, 50);
        addObject(gems, 810, 50);
        
        Font font2 = new Font("Verdana", true, false, 20);
        gemsLabel = new SuperTextBox("0", transparent, Color.BLACK, font2, false, 18*6, 0, transparent);
        addObject(gemsLabel, 800, 53);
        
        if (UserInfo.isStorageAvailable()){
            user = UserInfo.getMyInfo();
            gemsLabel.getImage().clear();
            gemsLabel.update(Integer.toString(user.getInt(0)));
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
        // Set volume 
        if (music != null){
            music.setVolume(user.getInt(2));
        }
    }
    
    public void act(){
        checkClick();
    }
    
    public void checkClick(){
        if (Greenfoot.mouseClicked(backButton)){
            Start start = new Start();
            if (music != null){
                music.stop();
            }
            start.started();
            Greenfoot.setWorld(start);
            if (music != null){
                music.stop();
            }   
        }
        if (Greenfoot.mouseClicked(changeMusic)){
            if (UserInfo.isStorageAvailable()){
                user = UserInfo.getMyInfo();
                int musicType = user.getInt(3);
                musicType++;
                if (musicType > 3){
                    musicType = 0;
                }
                user.setInt(3, musicType);
                user.store();
                // Play new music
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
        if (Greenfoot.mouseClicked(easy)){
            if (UserInfo.isStorageAvailable()){
                user = UserInfo.getMyInfo();
                user.setInt(1, 1);
                user.store();
            }
        }
        if (Greenfoot.mouseClicked(medium)){
            if (UserInfo.isStorageAvailable()){
                user = UserInfo.getMyInfo();
                user.setInt(1, 2);
                user.store();
            }
        }
        if (Greenfoot.mouseClicked(hard)){
            if (UserInfo.isStorageAvailable()){
                user = UserInfo.getMyInfo();
                user.setInt(1, 3);
                user.store();
            }
        }
    }
    
    public void updateVolumeText(String value){
        if (sliderLabel != null){
            sliderLabel.getImage().clear();
            sliderLabel.update(value);
            if (music != null){
                music.setVolume(Integer.valueOf(value));
            }
            if (UserInfo.isStorageAvailable()){
                user = UserInfo.getMyInfo();
                user.setInt(2, Integer.valueOf(value));
                user.store();
            }
        }
    }
}

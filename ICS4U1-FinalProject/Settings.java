import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Settings Class
 * <p>
 * Settings screen to allow any modifications to the volume, or reset progress. 
 * 
 * @author Jerry Zhu
 * @version January 2022
 */
public class Settings extends World
{
    // Instance variables
    private Image background;
    private Image backButton;
    private Image settingsText;
    private Image volumeText;
    private Image changeMusic;
    private Image resetButton;
    private Image gems; 
    private Slider slider;
    private SuperTextBox sliderLabel;
    private SuperTextBox gemsLabel;
    private UserInfo user;
    private GreenfootSound music;
    private static final Color transparent = new Color(0, 0, 0, 0);
    
    /**
     * Constructor for objects of class Settings.
     */
    public Settings()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1); 
        
        background = new Image(new GreenfootImage("Worlds/settingsbackground.png"));
        addObject(background, 200, 300);
        
        settingsText = new Image(new GreenfootImage("settingstext.png"));
        addObject(settingsText, getWidth()/2, 80);
        
        volumeText = new Image(new GreenfootImage("volumetext.png"));
        volumeText.getImage().scale(350, 90);
        addObject(volumeText, getWidth()/2, 290);
        
        backButton = new Image(new GreenfootImage("Buttons/backbutton.png"));
        backButton.getImage().scale(80, 50);
        addObject(backButton, 60, 50);
        
        resetButton = new Image(new GreenfootImage("Buttons/resetbutton.png"));
        resetButton.getImage().scale(150, 60);
        addObject(resetButton, getWidth()/2, 180);
        
        changeMusic = new Image(new GreenfootImage("Buttons/changemusic.png"));
        changeMusic.getImage().scale(220, 100);
        addObject(changeMusic, getWidth()/2, 490);
        
        slider = new Slider(300, 30, false);
        addObject(slider, getWidth()/2, 380);
        
        Font font = new Font("Courier New", true, false, 20);
        sliderLabel = new SuperTextBox("0", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(sliderLabel, getWidth()/2 + 180, 380);
        
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
    
    /**
     * When World is created, start the music and set the volume. 
     */
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
                music.playLoop();
            }
            if (music != null){
                music.setVolume(user.getInt(2));
            }
        }
    }
    
    /**
     * Act method to be run every iteration. 
     */
    public void act(){
        checkClick();
    }
    
    /**
     * Check for any buttons being clicked, and load the corresponding World if necessary. 
     */
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
        else if (Greenfoot.mouseClicked(changeMusic)){
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
                    music.setVolume(user.getInt(2));
                }
            }
        }
        else if (Greenfoot.mouseClicked(resetButton)){
            if (UserInfo.isStorageAvailable()){
                user = UserInfo.getMyInfo();
                user.setScore(1); // Level
                user.setInt(0, 0); // Gems
                user.setInt(1, 1); // Difficulty
                user.setInt(2, 50); // Volume
                user.setInt(3, 0); // Music type
                user.setInt(4, 0); // Elixir speed
                user.setInt(5, 0); // Castle health
                user.setInt(6, 0); // Castle projectile attack
                user.setInt(7, 0); // Wins
                user.setInt(8, 0); // Losses
                user.setInt(9, 0); // Time elapsed (seconds)
                user.setString(0, "0 0 0 0 0 0 0 0 "); // Knight, archer, giant, skeleton (health, attack)
                user.setString(1, "0 0 0 0 "); // Towers (elixir, tombstone)
                user.setString(2, "0 0 0 0 "); // Spells (fireball, poison)
                user.setString(3, "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 "); // 15 levels
                user.store();
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
                gemsLabel.getImage().clear();
                gemsLabel.update(Integer.toString(user.getInt(0)));
                sliderLabel.getImage().clear();
                sliderLabel.update(Integer.toString(user.getInt(2)));
                slider.setValue(user.getInt(2));
                slider.updateImage();
                if (music != null){
                    music.setVolume(user.getInt(2));
                }
            }
        }
    }
    
    /**
     * Update the text on the slider object. 
     */
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

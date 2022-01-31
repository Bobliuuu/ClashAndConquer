import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Iinstructions Class
 * 
 * @author Ibrahim Rahman, Jerry Zhu
 * @version January 2022
 */
public class Instructions extends World
{
    private Image backButton;
    private GreenfootImage instructions;
    private GreenfootSound music;
    private UserInfo user;
    
    /**
     * Constructor for objects of class Instructions.
     * 
     */
    public Instructions()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 838, 1); 
        
        instructions = new GreenfootImage("Worlds/instructions.png");
        instructions.scale(getWidth(), getHeight());
        setBackground(instructions);
        
        backButton = new Image(new GreenfootImage("Buttons/backbutton.png"));
        backButton.getImage().scale(80, 50);
        addObject(backButton, 65, 45);
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
                music.play();
            }
            if (music != null){
                music.setVolume(user.getInt(2));
            }
        }
    }
    
    /**
     * Act method to be run each iteration.
     */
    public void act(){
        checkClick();
    }
    
    /**
     * Check if any buttons are clicked. 
     */
    public void checkClick(){
        if (Greenfoot.mouseClicked(backButton)){
            Start start = new Start();
            start.started();
            Greenfoot.setWorld(start);
        }
    }
}

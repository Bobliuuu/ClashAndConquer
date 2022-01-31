import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * End Class 
 * <p>
 * End screen to display credits and acknowledgements.
 * 
 * @author Jerry Zhu
 * @version January 2022
 */
public class End extends World
{
    // Instance variables
    private GreenfootImage background;
    private GreenfootImage stars;
    private Image credits;
    private GreenfootSound music;
    private UserInfo user;
    
    /**
     * Constructor for objects of class End.
     */
    public End()
    {    
        super(900, 600, 1); 
        background = new GreenfootImage(600, 400);
        background.setColor(Color.BLACK);
        background.fill();
        stars = new GreenfootImage(background);
        stars.drawImage(drawStars(Color.WHITE, 600, 400, 8, false), 0, 0);
        setBackground(stars);
        credits = new Image(new GreenfootImage("creditstext.png"));
        addObject(credits, getWidth() / 2, getHeight() / 2);
    }
    
    /**
     * When World is created, start the music and set the volume. 
     */
    public void started(){
        if (UserInfo.isStorageAvailable()){
            user = UserInfo.getMyInfo();
            if (music != null){
                music.stop();
            }
            music = new GreenfootSound("endsong.mp3");
            music.playLoop();
            if (music != null){
                music.setVolume(user.getInt(2));
            }
        }
    }
    
    /**
     * Draw a starry background to the end scene.
     * 
     * @param color                The color of the background.
     * @param width                The width of the background.
     * @param height               The height of the background. 
     * @param density              The density of the stars on the background. 
     * @param fill                 Fill the background. 
     * @return GreenfootImage      The desired background image of the World.
     */
    private static GreenfootImage drawStars(Color color, int width, int height, int density, boolean fill){
        GreenfootImage temp = new GreenfootImage(width, height);
        temp.setColor(color);
        if (fill){
            temp.fill();
        }
        for (int i = 0; i < density; i++){
            for (int j = 0; j < 100; j++){
                int randSize = Greenfoot.getRandomNumber(4) + 1;
                int randX = Greenfoot.getRandomNumber(width);
                int randY = Greenfoot.getRandomNumber(height);
                temp.fillOval(randX, randY, randSize, randSize);
            }
        }
        return temp;
    }
}

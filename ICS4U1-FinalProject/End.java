import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class End here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class End extends World
{
    private GreenfootImage background;
    private GreenfootImage stars;
    private Image credits;
    
    /**
     * Constructor for objects of class End.
     * 
     */
    public End()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
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

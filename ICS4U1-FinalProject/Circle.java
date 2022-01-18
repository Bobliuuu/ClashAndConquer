import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Circle Class
 * <p>
 * Used to add a marker for each new level. 
 * 
 * @author Daniel Qian
 * @version January 2022
 */
public class Circle extends Control
{
    // Instance variables
    private GreenfootImage image;
    private Color color;
    private int radius;
    private int level;
    
    /**
     * Default constructor of class circle
     */
    public Circle(Color color, int radius, boolean clickable, int level){
        this.color = color;
        this.radius = radius;
        this.clickable = clickable;
        image = new GreenfootImage(radius, radius);
        image.setColor(color);
        image.fillOval(1, 1, radius, radius);
        setImage(image);
    }
    
    /**
     * Circle does not need highlighting
     */
    public void update() {
        
    }
}

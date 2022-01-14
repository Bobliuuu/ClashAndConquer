import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Circle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Circle extends Control
{
    private GreenfootImage image;
    private Color color;
    private int radius;
    private int level;
    
    public Circle(Color color, int radius, boolean clickable, int level){
        this.color = color;
        this.radius = radius;
        this.clickable = clickable;
        image = new GreenfootImage(radius, radius);
        image.setColor(color);
        image.fillOval(1, 1, radius, radius);
        setImage(image);
    }
    
    public void update() {
        
    }
}

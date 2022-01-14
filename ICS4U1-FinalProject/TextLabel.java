import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TextLabel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextLabel extends Actor
{
    private GreenfootImage image;
    private String string;
    
    public TextLabel(){
        image = new GreenfootImage(100, 100);
        image.drawString("Value", 1, 10);
        setImage(image);
    }
    
    public TextLabel(String string){
        this.string = string;
        image = new GreenfootImage(100, 100);
        image.drawString(string, 1, 10);
        setImage(image);
    }
    
    public TextLabel(String string, Font font){
        this.string = string;
        image = new GreenfootImage(100, 100);
        image.setFont(font);
        image.drawString(string, 1, 20);
        setImage(image);
    }
    
    /**
     * Act - do whatever the TextLabel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public void update(String newString){
        string = newString;
        image.clear();
        image.drawString(newString, 1, 20);
        setImage(image);
    }
}

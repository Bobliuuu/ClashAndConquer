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
        string = "";
        image.drawString(string, 0, 0);
    }
    
    public TextLabel(String string){
        this.string = string;
        image.drawString(string, 0, 0);
    }
    
    /**
     * Act - do whatever the TextLabel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * TextLabel Class
 * <p>
 * Displays a text label to the World
 * 
 * @author Jerry Zhu
 * @version January 2022
 */
public class TextLabel extends Actor
{
    // Instance variables
    private GreenfootImage image;
    private String string;
    
    /**
     * Basic constructor for testing
     */
    public TextLabel(){
        image = new GreenfootImage(100, 100);
        image.drawString("Value", 1, 10);
        setImage(image);
    }
    
    /**
     * Constructor of TextLabel with the ability to customize the string output. 
     * @param string     The string that is to be outputted. 
     */
    public TextLabel(String string){
        this.string = string;
        image = new GreenfootImage(100, 100);
        image.drawString(string, 1, 10);
        setImage(image);
    }
    
    /**
     * Similar to above, but with the ability to customize the Font (type and size). 
     * @param string     The string that is to be outputted. 
     * @param font       The desired font of the text label. 
     */
    public TextLabel(String string, Font font){
        this.string = string;
        image = new GreenfootImage(100, 100);
        image.setFont(font);
        image.drawString(string, 1, font.getSize());
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
    
    /**
     * Update the text label with a new string. 
     * @param newString     The new string output to be changed on the text label. 
     */
    public void update(String newString){
        string = newString;
        image.clear();
        image.drawString(newString, 1, 20);
        setImage(image);
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Image Class
 * <p>
 * An class that holds a static image that can be added and removed. 
 * It is also used to set the paint order and transparency. 
 * 
 * @author Jerry Zhu
 * @version January 2022
 */
public class Image extends Actor
{
    private int transparency;
    /**
     * Default constructor of class Image
     * 
     * @param image         The desired image to add. 
     */
    public Image(GreenfootImage image) {
        //Set the image
        setImage(image);
    }
    
    /**
     * Set the transparency of the image.
     * 
     * @param transparency   The transparency of the image. 
     */
    public void setTransparency(int transparency){
        this.transparency = transparency;
        getImage().setTransparency(transparency);
    }
    
    /**
     * Get the transparency of the image.
     * 
     * return int            The transparency of the image. 
     */
    public int getTransparency(){
        return transparency;
    }
}
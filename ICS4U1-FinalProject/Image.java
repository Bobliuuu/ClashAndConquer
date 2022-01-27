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
    // Instance variables
    private int transparency;
    private GreenfootImage image;
    private boolean isEmpty;
    
    /**
     * Empty constructor of class image
     */
    public Image(){
        isEmpty = true;
        setImage((GreenfootImage)null);
    }
    
    /**
     * Default constructor of class Image
     * 
     * @param image         The desired image to add. 
     */
    public Image(GreenfootImage image) {
        isEmpty = false;
        this.image = image;
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
    
    /**
     * Get the associated GreenfootImage associated with the Image object. 
     * @return GreenfootImage    The image to be returned. 
     */
    public GreenfootImage getImage(){
        return image;
    }
    
    /**
     * Return if the Image object is empty. 
     * @return boolean     Whether the Image is empty or not. 
     */
    public boolean getEmpty(){
        return isEmpty;
    }
    
    /**
     * Return if an Image object intersects a card. 
     * @return boolean     Whether the object intersects a card. 
     */
    public boolean intersectsCard(){
        return getOneObjectAtOffset(0, 0, Card.class) != null;
    }
    
    /**
     * Returns the name of the card intersecting with the image
     * @return String     The name of the image. 
     */
    public String getCardName(){
        if (intersectsCard()){
            return ((Card)getOneObjectAtOffset(0, 0, Card.class)).getCardName();
        }
        return "";
    }
    
    /**
     * Check if the current image is touching a castle
     * @return boolean     If the image is touching the castle or not. 
     */
    public boolean checkTouchingCastle(){
        return this.isTouching(Castle.class);
    }
}

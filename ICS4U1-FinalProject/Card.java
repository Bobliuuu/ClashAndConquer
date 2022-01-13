import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card extends Actor
{
    private GreenfootImage image;
    private String cardName;
    
    public Card(String imageName){
        image = new GreenfootImage("Cards/" + imageName + "card.png");
        setImage(image);
    }
    
    /**
     * Act - do whatever the Card wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkClick();
    }
    
    public void changeImage(String imageName){
        setImage("Cards/" + imageName + "card.png");
    }
    
    public void checkClick(){
        Level level = (Level)getWorld();
        if (Greenfoot.mouseClicked(this)){
            level.changeRedZone(true);
        }
    }
    
    public String getCardName(){
        return cardName;
    }
}

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
    
    public Card(String cardName){
        image = new GreenfootImage("Cards/" + cardName + "card.png");
        setImage(image);
        this.cardName = cardName;
    }
    
    /**
     * Act - do whatever the Card wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkClick();
    }
    
    public void checkClick(){
        Level level = (Level)getWorld();
        if (Greenfoot.mouseClicked(this) && cardName != "blank"){
            level.changeRedZone(true);
            level.setTroopSelected(cardName);
        }
    }
    
    public void changeImage(String imageName){
        setImage("Cards/" + imageName + "card.png");
    }
    
    public String getCardName(){
        return cardName;
    }
}

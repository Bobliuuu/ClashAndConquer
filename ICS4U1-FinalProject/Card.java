import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Card Class
 * <p>
 * Class that contains a card that can be clicked to spawn a troop or building. 
 * Can be bundled in a CardDeck. 
 * 
 * @author Jerry Zhu
 * @version January 2022
 */
public class Card extends Actor
{
    // Instance variables
    private GreenfootImage image;
    private String cardName;
    
    /**
     * Default constructor of card. 
     * 
     * @param cardName     The name of the card object. 
     */
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
    
    /**
     * Check if the card has been clicked. 
     */
    public void checkClick(){
        Level level = (Level)getWorld();
        if (Greenfoot.mouseClicked(this) && cardName != "Blank"){
            level.changeRedZone(true);
            level.setTroopSelected(cardName);
            level.setCardIndex();
        }
    }
    
    /**
     * Change the image of the card.
     * @param cardName     The new name of the card object. 
     */
    public void changeImage(String imageName){
        setImage("Cards/" + imageName + "card.png");
    }
    
    /**
     * Returns the name of the card. 
     * @return String      The name of the desired card. 
     */
    public String getCardName(){
        return cardName;
    }
}

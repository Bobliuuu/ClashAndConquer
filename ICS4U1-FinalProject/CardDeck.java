import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class CardDeck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CardDeck extends Actor
{
    // Instance variables
    private Card tempCard;
    private ArrayList <Card> cards; 
    
    public CardDeck(){
        cards = new ArrayList <Card> ();
        tempCard = new Card("knight");
        cards.add(tempCard);
        for (int i = 0; i < 2; i++){
            tempCard = new Card("blank");
            cards.add(tempCard);
        }
        tempCard = new Card("knight");
        cards.add(tempCard);
    }
    
    /**
     * Act - do whatever the CardDeck wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    }
    
    public Card getCardAtIndex(int idx){
        return cards.get(idx);
    }
}

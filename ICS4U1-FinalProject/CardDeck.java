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
    
    /**
     * Basic constructor for CardDeck
     */
    public CardDeck(){
        cards = new ArrayList <Card> ();
        tempCard = new Card("Knight");
        cards.add(tempCard);
        for (int i = 0; i < 2; i++){
            tempCard = new Card("Blank");
            cards.add(tempCard);
        }
        tempCard = new Card("Knight");
        cards.add(tempCard);
    }
    
    /**
     * Get the card at a certain index of the card deck.
     * @param idx      The desired index of card deck.
     */
    public Card getCardAtIndex(int idx){
        return cards.get(idx);
    }
}

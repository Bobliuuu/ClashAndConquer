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
     * Basic constructor for CardDeck for testing.
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
     * Similar to above, but with the ability to customize hard names. 
     * 
     * @param cardNames     The names of the CardDeck cards. 
     */
    public CardDeck(String[] cardNames){
        cards = new ArrayList <Card> ();
        for (String card : cardNames){
            tempCard = new Card(card);
            cards.add(tempCard);
        }
    }
    
    /**
     * Get the card at a certain index of the card deck.
     * @param idx      The desired index of card deck.
     */
    public Card getCardAtIndex(int idx){
        return cards.get(idx);
    }
}

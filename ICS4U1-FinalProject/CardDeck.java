import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;

/**
 * Card Class
 * <p>
 * A deck of cards that contains multiple cards that can be clicked and cycled. 
 * Uses a Queue and ArrayList to keep track of which cards have been clicked. 
 * 
 * @author Jerry Zhu
 * @version January 2022
 */
public class CardDeck extends Actor
{
    // Instance variables
    private Card tempCard;
    private ArrayList <Card> cards; 
    private Queue <Card> otherCards; 
    private String[] allCards = {"Knight", "Archer", "Giant", "Skeleton", "Elixirtower", "Tombstone", "Fireball", "Poison"};
    
    /**
     * Basic constructor for CardDeck for testing.
     */
    public CardDeck(){
        cards = new ArrayList <Card> ();
        otherCards = new LinkedList <Card> ();
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
        otherCards = new LinkedList <Card> ();
        for (String card : cardNames){
            tempCard = new Card(card);
            cards.add(tempCard);
        }
        List <String> cardList = Arrays.asList(cardNames);
        for (String card : allCards){
            if (!cardList.contains(card)){
                otherCards.add(new Card(card));
            }
        }
    }
    
    /**
     * Get the card at a certain index of the card deck.
     * 
     * @param idx      The desired index of card deck.
     */
    public Card getCardAtIndex(int idx){
        return cards.get(idx);
    }
    
    /**
     * Get the index of a desired card. 
     * 
     * @parm name       The name of the desired card. 
     */
    public int getCardIndex(String name){
        int idx = 0;
        for (Card card : cards){
            if (cards.get(idx).getCardName() == name){
                return idx;
            }
            idx++;
        }
        return idx;
    }
    
    /**
     * Switch a card with another in the card deck. 
     * 
     * @param idx     The index to switch the card. 
     */
    public void switchCard(int idx){
        otherCards.add(cards.get(idx));
        cards.remove(idx); 
        cards.add(idx, otherCards.poll());
    }
}

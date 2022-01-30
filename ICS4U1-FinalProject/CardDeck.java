import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;

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
     * @param idx      The desired index of card deck.
     */
    public Card getCardAtIndex(int idx){
        return cards.get(idx);
    }
    
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
    
    public void switchCard(int idx){
        otherCards.add(cards.get(idx));
        cards.remove(idx); 
        cards.add(idx, otherCards.poll());
    }
}

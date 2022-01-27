import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class ShopItems here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShopItem extends Actor
{
    private int cost;
    private int type;
    private GreenfootImage image;
     
    /**
     * Default constructor of shop item
     * Type 1 = Elixir Speed
     * Type 2 = Castle Health
     * Type 3 = Knight Attack
     * Type 4 = Knight Health
     */
    public ShopItem(int cost, int type){
        this.cost = cost;
        this.type = type;
        if (type == 1){
            image = new GreenfootImage("images/ShopItems/ElexirSpeed.PNG");
        }
        else if (type == 2){
            image = new GreenfootImage("images/ShopItems/CastleHealth.PNG");
        }
        else if (type == 3){
            image = new GreenfootImage("images/ShopItems/KnightAttack.PNG");
        }
        else {
            image = new GreenfootImage("images/ShopItems/KnightSpeed.PNG");
        }
        setImage(image);
    }
    
    /**
     * Act - do whatever the ShopItems wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }

    protected int getCost() {
        return this.cost;
    }
    
    protected void setCost(int cost) {
        this.cost = cost;
    }
    
    protected int getType(){
        return this.type;
    }
    
    protected void setType(){
        this.type = type;
    }
}

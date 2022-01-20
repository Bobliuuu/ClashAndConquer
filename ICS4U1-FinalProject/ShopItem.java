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
    private GreenfootImage image;
     
    /**
     * Default constructor of shop item
     * Type 1 = Speed Potion
     * Type 2 = InvisibilityPotion
     * Type 3 = HealthPotion
     * Type 4 = SpeedPotionOld
     */
    public ShopItem(int cost, int type){
        this.cost = cost;
        if (type == 1){
            image = new GreenfootImage("SpeedPotion.PNG");
        }
        else if (type == 2){
            image = new GreenfootImage("InvisibilityPotion.PNG");
        }
        else if (type == 3){
            image = new GreenfootImage("HealthPotion.PNG");
        }
        else {
            image = new GreenfootImage("SpeedPotionOld.PNG");
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

}

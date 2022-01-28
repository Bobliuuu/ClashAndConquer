import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class ShopItems here.
 * 
 * @author Ibrahim Rahman
 * @version January 2022
 */
public class ShopItem extends Actor
{
    private int cost;
    private int type;
    private int level;
    private GreenfootImage image;
     
    /**
     * Default constructor of shop item
     * Type 1 = Speed Potion
     * Type 2 = InvisibilityPotion
     * Type 3 = HealthPotion
     * Type 4 = SpeedPotionOld
     */
    public ShopItem(int cost, int type, int level){
        this.cost = cost;
        this.level = level;
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

    public int getCost() {
        return this.cost;
    }
    
    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getType(){
        return this.type;
    }

    public void setType(){
        this.type = type;
    }
    
    protected int getLevel() {
        return this.level;
    }
    
    protected void setLevel(int level) {
        this.level = level;
    }
}

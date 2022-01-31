import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * ShopItem Class
 * <p>
 * 
 * 
 * @author Ibrahim Rahman
 * @version January 2022
 */
public class ShopItem extends Actor
{
    // Instance variables
    private int cost;
    private int level;
    private String type;
    private GreenfootImage image;
     
    /**
     * Default constructor of shop item
     */
        public ShopItem(int cost, String type, int level){
        this.cost = cost;
        this.level = level;
        this.type = type;
        if (type.equals("CastleHealth")){
            image = new GreenfootImage("images/ShopItems/CastleHealth.PNG");
        }
        else if (type.equals("ElixirBarSpeed")){
            image = new GreenfootImage("images/ShopItems/ElixirBarSpeed.PNG");
        }
        else if (type.equals("ElixirHealth")){
            image = new GreenfootImage("images/ShopItems/ElixirHealth.PNG");
        }
        else if (type.equals("ElixirSpeed")){
            image = new GreenfootImage("images/ShopItems/ElixirSpeed.PNG");
        }
        else if (type.equals("PoisonAttack")){
            image = new GreenfootImage("images/ShopItems/PoisonAttack.PNG");
        }
        else if (type.equals("PoisonDuration")){
            image = new GreenfootImage("images/ShopItems/PoisonDuration.PNG");
        }
        else if (type.equals("FireballAttack")){
            image = new GreenfootImage("images/ShopItems/FireballAttack.PNG");
        }
        else if (type.equals("FireballRadius")){
            image = new GreenfootImage("images/ShopItems/FireballRadius.PNG");
        }
        else if (type.equals("ArrowAttack")){
            image = new GreenfootImage("images/ShopItems/ArrowAttack.PNG");
        }
        else if (type.equals("GiantAttack")){
            image = new GreenfootImage("images/ShopItems/GiantAttack.PNG");
        }
        else if (type.equals("GiantHealth")){
            image = new GreenfootImage("images/ShopItems/GiantHealth.PNG");
        }
        else if (type.equals("KnightAttack")){
            image = new GreenfootImage("images/ShopItems/KnightAttack.PNG");
        }
        else if (type.equals("KnightHealth")){
            image = new GreenfootImage("images/ShopItems/KnightHealth.PNG");
        }
        else if (type.equals("ArcherAttack")){
            image = new GreenfootImage("images/ShopItems/ArcherAttack.PNG");
        }
        else if (type.equals("ArcherHealth")){
            image = new GreenfootImage("images/ShopItems/ArcherHealth.PNG");
        }
        else if (type.equals("SkeletonAttack")){
            image = new GreenfootImage("images/ShopItems/SkeletonAttack.PNG");
        }
        else if (type.equals("SkeletonHealth")){
            image = new GreenfootImage("images/ShopItems/SkeletonHealth.PNG");
        }
        else if (type.equals("TombstoneCooldown")){
            image = new GreenfootImage("images/ShopItems/TombstoneCooldown.PNG");
        }
        else if (type.equals("TombstoneHealth")){
            image = new GreenfootImage("images/ShopItems/TombstoneHealth.PNG");
        }
        setImage(image);
    }

    /**
     * Get the cost of the shop item. 
     * 
     * @return int The cost of the desired shop item. 
     */
    public int getCost() {
        return this.cost;
    }
    
    /**
     * Set the cost of the shop item
     * 
     * @param cost The cost of the item. 
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Get the type of the shop item.
     * 
     * @return String The type of the shop item. 
     */
    public String getType(){
        return this.type;
    }

    /**
     * Set the type of shop item. 
     * 
     * @param type The desired type of the shop item. 
     */
    public void setType(String type){
        this.type = type;
    }
    
    /**
     * Get the level of the shop item
     * 
     * @return int  The desired level of the shop item. 
     */
    public int getLevel() {
        return this.level;
    }
    
    /**
     * Set the level of the shop item. 
     * 
     * @return int  The desired level of the shop item. 
     */
    public void setLevel(int level) {
        this.level = level;
    }
}

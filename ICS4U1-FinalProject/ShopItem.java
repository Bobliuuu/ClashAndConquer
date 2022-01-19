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
    protected int cost;
     
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

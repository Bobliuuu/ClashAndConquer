import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GoldMine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ElixirTower extends Building
{
    private double elixirSpeed;
    private boolean increasedSpeed;
    
    public ElixirTower(double speed, boolean isEnemy){
        this.isEnemy = isEnemy;
        elixirSpeed = speed;
        this.health = 175;
        setImage("elixircollectorlevel1.png");
        getImage().scale(75, 75);
        increasedSpeed = false;
    }
    
    /**
     * Act - do whatever the ElixirTower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(!increasedSpeed){
            getWorld().getObjects(ElixirBar.class).get(0).increaseElixirSpeed(elixirSpeed);
            increasedSpeed = true;
        }
    }
    
    public void subtractHealth(int value){
        health -= value;
        if (health <= 0){
            getWorld().getObjects(ElixirBar.class).get(0).decreaseElixirSpeed(elixirSpeed);
            ((Level)getWorld()).removeObject(this);
        }
    }
}

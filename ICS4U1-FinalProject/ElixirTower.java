import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GoldMine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ElixirTower extends Building
{
    private int actCount;
    private double elixirSpeed;
    private boolean increasedSpeed;
    private SuperStatBar healthBar;
    
    public ElixirTower(double speed, boolean isEnemy){
        this.isEnemy = isEnemy;
        elixirSpeed = speed;
        this.health = 175;
        setImage("elixircollectorlevel1.png");
        getImage().scale(75, 75);
        increasedSpeed = false;
        actCount = 0;
        healthBar = new SuperStatBar(health, health, this, 48, 4, 36, Color.GREEN, Color.RED, false);
    }
    
    protected void addedToWorld(World world){
        getWorld().addObject(healthBar, getX(), getY()+20);
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
        if(actCount % 30 == 0) subtractHealth(1);
        actCount++;
        if(actCount > 3000) actCount = 0;
        healthBar.update(this.health);
    }
    
    public void subtractHealth(int value){
        health -= value;
        if (health <= 0){
            getWorld().getObjects(ElixirBar.class).get(0).decreaseElixirSpeed(elixirSpeed);
            ((Level)getWorld()).removeObject(this);
        }
    }
}

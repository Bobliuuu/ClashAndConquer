import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A building that increases the speed that Elixir is gained by the player
 * 
 * @author Matthew Gong
 * @version January 2022
 */
public class ElixirTower extends Building
{
    // counting the amount of acts that have passed
    private int actCount;
    // the speed increase and whether it has been already increased
    private double elixirSpeed;
    private boolean increasedSpeed;
    // the health bar
    private SuperStatBar healthBar;
    
    /***
     * The most basic constructor for the ElixirTower class
     * 
     * @param speed      How much does the ElixirTower increase the speed of Elixir gain
     * @param health     The amount of health the ElixirTower has
     */
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
    
    /**
     * Upon being added to the World, the health bar is added
     */
    protected void addedToWorld(World world){
        getWorld().addObject(healthBar, getX(), getY()+20);
    }
    
    /**
     * Act - do whatever the ElixirTower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // increases the Elixir gain speed once
        if(!increasedSpeed){
            getWorld().getObjects(ElixirBar.class).get(0).increaseElixirSpeed(elixirSpeed); // increases the speed
            increasedSpeed = true;
        }
        // every 30 acts the ElixirTower loses health
        if(actCount % 30 == 0) subtractHealth(1);
        actCount++;
        // if act count is too big, reset it back to zero
        if(actCount > 3000) actCount = 0;
        healthBar.update(this.health);
    }
    
    /**
     * Removes health from the Tombstone and if the health reaches zero then it removes itself
     */
    public void subtractHealth(int value){
        health -= value;
        // if the health has reached zero or below
        if (health <= 0){
            getWorld().getObjects(ElixirBar.class).get(0).decreaseElixirSpeed(elixirSpeed); // remove the elixir gain speed increase
            ((Level)getWorld()).removeObject(this); // removed from the world
        }
    }
}

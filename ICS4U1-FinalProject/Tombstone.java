import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A building that spawns a number of Skeletons over time until it is destroyed
 * 
 * @author Matthew Gong
 * @version January 2022
 */
public class Tombstone extends Building
{
    // Instance variables
    private int cooldown, timer;    // tracking the cooldown and the current number of acts until it ends
    private SuperStatBar healthBar; // the health bar
    private UserInfo user;
    
    /**
     * The most basic constructor for the Tombstone class
     * 
     * @param health     The amount of health the Tombstone has
     * @param isEnemy    Whether the Tombstone and its Skeleton belongs to the enemy
     */
    public Tombstone(int health, boolean isEnemy){
        this.isEnemy = isEnemy;
        this.health = health;
        cooldown = 150;
        timer = cooldown;
        setImage("tombstone.jpg");
        getImage().scale(50, 75);
        healthBar = new SuperStatBar(health, health, this, 48, 4, 36, Color.GREEN, Color.RED, false);
    }
    
    /***
     * Similar to above but the cooldown between Skeleton summonings is customizable
     * 
     * @param health     The amount of health the Tombstone has
     * @param isEnemy    Whether the Tombstone belongs to the enemy
     * @param cooldown   How many acts between each Skeleton summoning
     */
    public Tombstone(int health, boolean isEnemy, int cooldown){
        this.isEnemy = isEnemy;
        this.health = health;
        this.cooldown = cooldown;
        timer = cooldown;
        setImage("tombstone.jpg");
        getImage().scale(50, 75);
        healthBar = new SuperStatBar(health, health, this, 48, 4, 36, Color.GREEN, Color.RED, false);
    }
    
    /**
     * Upon being added to the World, the health bar is added
     */
    protected void addedToWorld(World world){
        getWorld().addObject(healthBar, getX(), getY()+50);
    }
    
    /**
     * Act - do whatever the Tombstone wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // checking if the cooldown has finished
        if(timer != 0){
            if(timer % 30 == 0){ 
                subtractHealth(1); // removing one health every 30 acts
            }
            timer--;
        }
        else{
            // summoning a skeleton and resetting the cooldown
            timer = cooldown;
            Skeleton skel;
            if (UserInfo.isStorageAvailable()){
                user = UserInfo.getMyInfo();
                String[] parsed0 = user.getString(0).split(" ");
                String[] parsed1 = user.getString(1).split(" ");
                skel = new Skeleton(25 + 14 * Integer.valueOf(parsed1[2]), 8 + 14 * Integer.valueOf(parsed0[7]), 1, 6, isEnemy);
            }
            else {
                skel = new Skeleton(25, 8, 1, 6, isEnemy);
            }
            ((Level)getWorld()).addObject(skel, getX(), getY());
        }
        healthBar.update(this.health);
    }
    
    /**
     * Removes health from the Tombstone and if the health reaches zero then it removes itself
     */
    public void subtractHealth(int value){
        this.health -= value;
        // if the health has reached zero or below
        if (this.health <= 0){
            ((Level)getWorld()).removeObject(this); // removed from the world
        }
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GoblinHut here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tombstone extends Building
{
    private int cooldown, timer;
    private SuperStatBar healthBar;
    
    public Tombstone(int health, boolean isEnemy){
        this.isEnemy = isEnemy;
        this.health = health;
        cooldown = 150;
        timer = cooldown;
        setImage("tombstone.jpg");
        getImage().scale(50, 75);
        healthBar = new SuperStatBar(health, health, this, 48, 4, 36, Color.GREEN, Color.RED, false);
    }
    
    protected void addedToWorld(World world){
        getWorld().addObject(healthBar, getX(), getY()+50);
    }
    
    /**
     * Act - do whatever the GoblinHut wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(timer != 0){
            if(timer % 30 == 0){
                subtractHealth(1);
            }
            timer--;
        }
        else{
            timer = cooldown;
            Skeleton goblin = new Skeleton(100, 10, 1, 3, 50, isEnemy);
            ((Level)getWorld()).addObject(goblin, getX(), getY());
        }
        healthBar.update(this.health);
    }
    
    public void setCooldown(int cd){
        cooldown = cd;
    }
    
    public void subtractHealth(int value){
        this.health -= value;
        if (this.health <= 0){
            ((Level)getWorld()).removeObject(this);
        }
    }
}

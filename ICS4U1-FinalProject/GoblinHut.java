import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GoblinHut here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GoblinHut extends Building
{
    private int cooldown, timer;
    
    public GoblinHut(int health, boolean isEnemy){
        this.isEnemy = isEnemy;
        this.health = health;
        cooldown = 150;
        timer = cooldown;
        setImage("castledestroyed.png");
    }
    
    /**
     * Act - do whatever the GoblinHut wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(timer != 0) timer--;
        else{
            timer = cooldown;
            SpearGoblin goblin = new SpearGoblin(75, 10, 1, 10, 100, isEnemy);
            ((Level)getWorld()).addObject(goblin, getX(), getY());
        }
    }
    
    public void setCooldown(int cd){
        cooldown = cd;
    }
    
    public void subtractHealth(int value){
        health -= value;
        if (health <= 0){
            ((Level)getWorld()).removeObject(this);
        }
    }
}

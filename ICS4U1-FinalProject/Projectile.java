import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends SuperSmoothMover
{
    //Instance variables
    protected int damage;
    protected double speed;
    protected Actor target;
    
    public Projectile(int dmg, double spd, Actor tgt){
        this.damage = dmg;
        this.speed = spd;
        this.target = tgt;
        setImage("arrow.png");
    }
    
    /**
     * Act - do whatever the Projectile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        turnTowards(target.getX(), target.getY());
        move(speed);
        checkForHit();
    }
    
    private void checkForHit(){
        if(this.intersects(target)){
            // deal damage to target here
            getWorld().removeObject(this);
        }
    }
}

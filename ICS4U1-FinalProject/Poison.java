import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * A spell that deals damage over time to a set area
 * 
 * @author Matthew Gong
 * @version January 2022
 */
public class Poison extends Spell
{
    // the array to contain the images for the attack animation
    private GreenfootImage[] animations;
    // integers for tracking the current act/frame count and how long Poison lasts
    private int animateCount, duration = 204;
    private int spd;
    
    /***
     * The most complicated constructor, able to customize attack, speed, attack radius, and alignment
     * 
     * @param attack            The amount of damage the Fireball deals
     * @param speed             How fast the Fireball moves
     * @param radius            Attack radius of the Fireball
     * @param isEnemy           Whether the Fireball belongs to the enemy
     */
    public Poison(int atk, double spd, int rds, boolean enemy){
        super(atk, spd, rds, enemy, 0, 0);
        this.spd = (int)spd;
        animateCount = -1;
        animations = new GreenfootImage[51];
        for(int i = 0; i < 51; i++) animations[i] = new GreenfootImage("Spells/Poison/PoisonAttack" + i + ".png");
    }
    
    /**
     * Act - do whatever the Poison wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // checks if the poison is still going
        if(duration != 0){
            animate();
            // every 28 acts, it deals damage
            if(duration % (25 - spd) == 0) attack();
            duration--;
        }
        else{
            // finished the attack so it gets removed
            getWorld().removeObject(this);
        }
    }
    
    /***
     * Animates the movement of Fireball by switching to the next image every 4 acts
     */
    public void animate(){
        animateCount++;
        // if the count reaches the end, reset it back to 0
        if(animateCount == 204) animateCount = 0;
        // change the images every 4 acts
        setImage(animations[animateCount/4]);
    }
    
    /***
     * Animates the Poison's attack by switching to the next image every 4 acts
     */
    public void attackAnimate(){
    
    }
    
    /**
     * Deals damage to everything that it can within the radius
     */
    public void attack(){
        // get all possible target within the radius
        ArrayList<Actor> targets = (ArrayList<Actor>)getObjectsInRange(radius, Actor.class);
            
        // loop through all the targets
        for(int i = 0; i < targets.size(); i++){
            Actor current = targets.get(i);
            
            // if they can be damaged and they are not on the same side then damage them
            if(current instanceof Troop){
                if(((Troop)current).enemy() != fromEnemy){
                    ((Troop)current).subtractHealth(attack);
                }
            }
            else if(current instanceof Building){
                if(((Building)current).enemy() != fromEnemy){
                    ((Building)current).subtractHealth(attack);
                }
            }
        }
    }
}


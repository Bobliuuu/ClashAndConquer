import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * The Giant Troop. It's a slow and powerful troop that only attacks Buildings
 * 
 * @author Matthew Gong 
 * @version January 2022
 */
public class Giant extends Troop
{
    // the arrays to contain the images for the walking and attack animations
    private GreenfootImage[] animations;
    private GreenfootImage[] attackAnimation;
    // integers for tracking the current act/frame count
    private int animationCount;
    private int attackCount;
    
    /***
     * The most complicated constructor, able to customize health, attack, movement speed, attack speed, attack radius, and alignment
     * 
     * @param health            The amount of health the Giant has
     * @param attk              The amount of attack points the Giant has
     * @param movementSpeed     How fast the Giant moves
     * @param attackSpeed       How fast the Giant attacks
     * @param isEnemy           Whether the Giant belongs to the enemy
     */
    public Giant(int health, int attack, double movementSpeed, double attackSpeed, boolean isEnemy){
        super(health, attack, movementSpeed, attackSpeed, 200, 150, isEnemy);
        animationCount = 0;
        attackCount = -1;
        animations = new GreenfootImage[19];
        attackAnimation = new GreenfootImage[19];
        for(int i = 0; i < 19; i++){
            animations[i] = new GreenfootImage("Troops/Giant/GiantMove" + i + ".png");
            attackAnimation[i] = new GreenfootImage("Troops/Giant/GiantAttack" + i + ".png");
        }
    }
    
    /**
     * Finds the nearest enemy target. 
     */
    public void findTarget(){
        target = null;
        double closestDistance = radius;
        ArrayList <Building> possibleTargets = (ArrayList <Building>) getWorld().getObjects(Building.class);
        for (Building possible : possibleTargets){    
            if (findDistanceBetween(possible, this) < closestDistance && ((Building)possible).enemy() != isEnemy){
                target = (Building)possible;
                closestDistance = findDistanceBetween(possible, this);
                break;
            }
        }
        
        if (((Level)getWorld()).getMyCastle().getWorld() != null && getWorld() != null){
            if (isEnemy && findDistanceBetween(this, ((Level)getWorld()).getMyCastle()) <= closestDistance){
                target = ((Level)getWorld()).getMyCastle();
            }
        }
        if (((Level)getWorld()).getEnemyCastle().getWorld() != null && getWorld() != null){
            if (!isEnemy && findDistanceBetween(this, ((Level)getWorld()).getEnemyCastle()) <= closestDistance){
                target = ((Level)getWorld()).getEnemyCastle();
            }
        }
    }
    
    /***
     * Animates the movement of Giant by switching to the next image every 4 acts
     */
    public void animate(){
        animationCount++;
        // if the count reaches the end, reset it back to 0
        if (animationCount == 76){
            animationCount = 0;
        }
        // change the images every 4 acts
        setImage(animations[animationCount / 4]);
    }
    
    /***
     * Animates the Giant's attack by switching to the next image every 4 acts
     */
    public void attackAnimate(){
        attackCount++;
        // if the count reaches the end, reset it back to 0
        if (attackCount == 76){
            attackCount = 0;
        }
        // change the images every 4 acts
        setImage(attackAnimation[attackCount / 4]);
    }
    
    /***
     * Returns the current act count for the attack animation
     */
    public int getAttackCounter(){
        return attackCount;
    }
    
    /**
     * Find the distance between two actors. 
     * 
     * @param a1     The first actor. 
     * @param a2     The second actor. 
     */
    private double findDistanceBetween(Actor a1, Actor a2){
        return Math.sqrt(Math.pow(a1.getX() - a2.getX(), 2) + Math.pow(a1.getY() - a2.getY(), 2));
    }
}

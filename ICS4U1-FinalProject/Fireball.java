import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A spells that sends a ball that deals massive damage to anything within it radius
 * 
 * @author Matthew Gong
 * @version January 2022
 */
public class Fireball extends Spell
{
    // the arrays to contain the images for the walking and attack animations
    private GreenfootImage[] animations, attackAnimations;
    // integers for tracking the current act/frame count
    private int animateCount, attackCount;
    
    /***
     * The most complicated constructor, able to customize attack, speed, attack radius, alignment, and target location
     * 
     * @param attack            The amount of damage the Fireball deals
     * @param speed             How fast the Fireball moves
     * @param radius            Attack radius of the Fireball
     * @param isEnemy           Whether the Fireball belongs to the enemy
     * @param x                 The x-coordinate of the target location
     * @param y                 The y-coordinate of the target location
     */
    public Fireball(int atk, double spd, int rds, boolean enemy, int x, int y){
        super(atk, spd, rds, enemy, x, y);
        animateCount = 0;
        attackCount = -1;
        animations = new GreenfootImage[12];
        attackAnimations = new GreenfootImage[9];
        for(int i = 0; i < 12; i++){
            if(i < 9) attackAnimations[i] = new GreenfootImage("Spells/Fireball/FireballAttack" + i + ".png");
            animations[i] = new GreenfootImage("Spells/Fireball/FireballMove" + i + ".png");
        }
    }
 
    /***
     * Animates the movement of Fireball by switching to the next image every 4 acts
     */
    public void animate(){
        animateCount++;
        // if the count reaches the end, reset it back to 0
        if(animateCount == 48) animateCount = 0;
        // change the images every 4 acts
        setImage(animations[animateCount/4]);
    }
    
    /***
     * Animates the Fireball's attack by switching to the next image every 4 acts
     */
    public void attackAnimate(){
        attackCount++;
        // if the count reaches the end, reset it back to 0
        if(attackCount == 36) attackCount = 0;
        // change the images every 4 acts
        setImage(attackAnimations[attackCount/4]);
    }
}

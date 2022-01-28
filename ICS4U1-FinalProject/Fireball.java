import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends Spell
{
    private GreenfootImage[] animations, attackAnimations;
    private int animateCount, attackCount;
    
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
 
    public void animate(){
        animateCount++;
        if(animateCount == 48) animateCount = 0;
        setImage(animations[animateCount/4]);
    }
    
    public void attackAnimate(){
        attackCount++;
        if(attackCount == 36) attackCount = 0;
        setImage(attackAnimations[attackCount/4]);
    }
}

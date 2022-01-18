import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Knight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knight extends Troop
{
    private GreenfootImage[] animations;
    private GreenfootImage[] attack;
    private int animationCount;
    private int attackCount;
    
    public Knight(boolean isEnemy){
        super(100, 10, 1, 3, 50, isEnemy);
        animationCount = 0;
        attackCount = -1;
        animations = new GreenfootImage[12];
        attack = new GreenfootImage[12];
        for (int i = 0; i < 12; i++){
            animations[i] = new GreenfootImage("Troops/Knight/KnightMove" + i + ".png");
        }
        for (int i = 0; i < 12; i++){
            attack[i] = new GreenfootImage("Troops/Knight/KnightAttack" + i + ".png");
        }
    }
    
    public Knight(int health, int attk, double movementSpeed, double attackSpeed, double radius, boolean isEnemy){
        super(health, attk, movementSpeed, attackSpeed, radius, isEnemy);
        animationCount = 0;
        attackCount = -1;
        animations = new GreenfootImage[12];
        attack = new GreenfootImage[12];
        for (int i = 0; i < 12; i++){
            animations[i] = new GreenfootImage("Troops/Knight/KnightMove" + i + ".png");
        }
        for (int i = 0; i < 12; i++){
            attack[i] = new GreenfootImage("Troops/Knight/KnightAttack" + i + ".png");
        }
    }
    
    public void animate(){
        animationCount++;
        if (animationCount == 48){
            animationCount = 0;
        }
        setImage(animations[animationCount / 4]);
    }
    
    public void attackAnimate(){
        attackCount++;
        if (attackCount == 48){
            attackCount = 0;
        }
        setImage(attack[attackCount / 4]);
    }
    
    public int getAttackCounter(){
        return attackCount;
    }
}

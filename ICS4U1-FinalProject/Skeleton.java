import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Skeleton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Skeleton extends Troop
{
    private GreenfootImage[] animations;
    private GreenfootImage[] attack;
    private int animationCount;
    private int attackCount;
    
    public Skeleton(boolean isEnemy){
        super(100, 10, 1, 3, 50, isEnemy);
        animationCount = 0;
        attackCount = -1;
        animations = new GreenfootImage[14];
        attack = new GreenfootImage[14];
        for (int i = 0; i < 14; i++){
            animations[i] = new GreenfootImage("Troops/Skeleton/SkeletonMove" + i + ".png");
            attack[i] = new GreenfootImage("Troops/Skeleton/SkeletonAttack" + i + ".png");
        }
    }
    
    public Skeleton(int health, int attk, double movementSpeed, double attackSpeed, double radius, boolean isEnemy){
        super(health, attk, movementSpeed, attackSpeed, radius, isEnemy);
        animationCount = 0;
        attackCount = -1;
        animations = new GreenfootImage[14];
        attack = new GreenfootImage[14];
        for (int i = 0; i < 14; i++){
            animations[i] = new GreenfootImage("Troops/Skeleton/SkeletonMove" + i + ".png");
            attack[i] = new GreenfootImage("Troops/Skeleton/SkeletonAttack" + i + ".png");
        }
    }
    
    public void animate(){
        animationCount++;
        if (animationCount == 56){
            animationCount = 0;
        }
        setImage(animations[animationCount / 4]);
    }
    
    public void attackAnimate(){
        attackCount++;
        if (attackCount == 56){
            attackCount = 0;
        }
        setImage(attack[attackCount / 4]);
    }
    
    public int getAttackCounter(){
        return attackCount;
    }
}

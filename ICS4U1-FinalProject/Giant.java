import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Giant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Giant extends Troop
{
    private GreenfootImage[] animations;
    private GreenfootImage[] attackAnimation;
    private int animationCount;
    private int attackCount;
    
    public Giant(int health, int attack, double movementSpeed, double attackSpeed, double radius, boolean isEnemy){
        super(health, attack, movementSpeed, attackSpeed, radius, isEnemy);
        animationCount = 0;
        attackCount = -1;
        animations = new GreenfootImage[19];
        attackAnimation = new GreenfootImage[19];
        for(int i = 0; i < 19; i++){
            animations[i] = new GreenfootImage("Troops/Giant/GiantMove" + i + ".png");
            attackAnimation[i] = new GreenfootImage("Troops/Giant/GiantAttack" + i + ".png");
        }
    }
    
    public void findTarget(){
        if (((Level)getWorld()).getMyCastle().getWorld() != null && getWorld() != null){
            if (isEnemy && findDistanceBetween(this, ((Level)getWorld()).getMyCastle()) <= 100){
                target = ((Level)getWorld()).getMyCastle();
            }
        }
        if (((Level)getWorld()).getEnemyCastle().getWorld() != null && getWorld() != null){
            if (!isEnemy && findDistanceBetween(this, ((Level)getWorld()).getEnemyCastle()) <= 100){
                target = ((Level)getWorld()).getEnemyCastle();
            }
        }
    }
    
    public void animate(){
        animationCount++;
        if (animationCount == 76){
            animationCount = 0;
        }
        setImage(animations[animationCount / 4]);
    }
    
    public void attackAnimate(){
        attackCount++;
        if (attackCount == 76){
            attackCount = 0;
        }
        setImage(attackAnimation[attackCount / 4]);
    }
    
    public int getAttackCounter(){
        return attackCount;
    }
    
    private double findDistanceBetween(Actor a1, Actor a2){
        return Math.sqrt(Math.pow(a1.getX() - a2.getX(), 2) + Math.pow(a1.getY() - a2.getY(), 2));
    }
    
    private double findDistanceBetween(double x, double y){
        return Math.sqrt(Math.pow(this.getX() - x, 2) + Math.pow(this.getY() - y, 2));
    }
}

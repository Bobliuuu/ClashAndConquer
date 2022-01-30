import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Skeleton Troop. It's weaker than the average Knight but appear in more numbers to make up the difference.
 * 
 * @author Matthew Gong
 * @version January 2022
 */
public class Skeleton extends Troop
{
    // the arrays to contain the images for the walking and attack animations
    private GreenfootImage[] animations, attack;
    // integers for tracking the current act/frame count
    private int animationCount, attackCount;
    
    /***
     * The most basic constructor for the Skeleton class
     * 
     * @param isEnemy    Whether the Skeleton belongs to the enemy
     */
    public Skeleton(boolean isEnemy){
        super(100, 10, 1, 3, 50, isEnemy);
        // setting the inital values for the counters
        animationCount = 0;
        attackCount = -1;
        // initalizing all the images into the arrays
        animations = new GreenfootImage[14];
        attack = new GreenfootImage[14];
        for (int i = 0; i < 14; i++){
            animations[i] = new GreenfootImage("Troops/Skeleton/SkeletonMove" + i + ".png");
            attack[i] = new GreenfootImage("Troops/Skeleton/SkeletonAttack" + i + ".png");
        }
    }
    
    /***
     * The most complicated constructor, able to customize health, attack, movement speed, attack speed, attack radius, and alignment
     * 
     * @param health            The amount of health the Skeleton has
     * @param attk              The amount of attack points the Skeleton has
     * @param movementSpeed     How fast the Skeleton moves
     * @param attackSpeed       How fast the Skeleton attacks
     * @param radius            Attack radius of the Skeleton
     * @param isEnemy           Whether the Skeleton belongs to the enemy
     */
    public Skeleton(int health, int attk, double movementSpeed, double attackSpeed, double radius, boolean isEnemy){
        super(health, attk, movementSpeed, attackSpeed, radius, isEnemy);
        // setting the inital values for the counters
        animationCount = 0;
        attackCount = -1;
        // initalizing all the images into the arrays
        animations = new GreenfootImage[14];
        attack = new GreenfootImage[14];
        for (int i = 0; i < 14; i++){
            animations[i] = new GreenfootImage("Troops/Skeleton/SkeletonMove" + i + ".png");
            attack[i] = new GreenfootImage("Troops/Skeleton/SkeletonAttack" + i + ".png");
        }
    }
    
    /***
     * Animates the movement of Skeleton by switching to the next image every 4 acts
     */
    public void animate(){
        animationCount++;
        // if the count reaches the end, reset it back to 0
        if (animationCount == 56){
            animationCount = 0;
        }
        // change the images every 4 acts
        setImage(animations[animationCount / 4]);
    }
    
    /***
     * Animates the Skeleton's attack by switching to the next image every 4 acts
     */
    public void attackAnimate(){
        attackCount++;
        // if the count reaches the end, reset it back to 0
        if (attackCount == 56){
            attackCount = 0;
        }
        // change the images every 4 acts
        setImage(attack[attackCount / 4]);
    }

    /***
     * Returns the current act count for the attack animation
     */
    public int getAttackCounter(){
        return attackCount;
    }
}

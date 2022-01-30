import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Knight Troop. It's the most basic troop that attacks with a sword.
 * 
 * @author Matthew Gong, Jerry Zhu
 * @version January 2022
 */
public class Knight extends Troop
{
    // the arrays to contain the images for the walking and attack animations
    private GreenfootImage[] animations;
    private GreenfootImage[] attack;
    // integers for tracking the current act/frame count
    private int animationCount;
    private int attackCount;
    
    /***
     * The most basic constructor for the Knight class
     * 
     * @param isEnemy    Whether the Knight belongs to the enemy
     */
    public Knight(boolean isEnemy){
        super(100, 10, 1, 3, 50, isEnemy);
        // setting the inital values for the counters
        animationCount = 0;
        attackCount = -1;
        // initalizing all the images into the arrays
        animations = new GreenfootImage[12];
        attack = new GreenfootImage[12];
        for (int i = 0; i < 12; i++){
            animations[i] = new GreenfootImage("Troops/Knight/KnightMove" + i + ".png");
        }
        for (int i = 0; i < 12; i++){
            attack[i] = new GreenfootImage("Troops/Knight/KnightAttack" + i + ".png");
        }
    }
    
    /***
     * The most complicated constructor, able to customize health, attack, movement speed, attack speed, attack radius, and alignment
     * 
     * @param health            The amount of health the Knight has
     * @param attk              The amount of attack points the Knight has
     * @param movementSpeed     How fast the Knight moves
     * @param attackSpeed       How fast the Knight attacks
     * @param radius            Attack radius of the Knight
     * @param isEnemy           Whether the Knight belongs to the enemy
     */
    public Knight(int health, int attk, double movementSpeed, double attackSpeed, double radius, boolean isEnemy){
        super(health, attk, movementSpeed, attackSpeed, radius, isEnemy);
        // setting the inital values for the counters
        animationCount = 0;
        attackCount = -1;
        // initalizing all the images into the arrays
        animations = new GreenfootImage[12];
        attack = new GreenfootImage[12];
        for (int i = 0; i < 12; i++){
            animations[i] = new GreenfootImage("Troops/Knight/KnightMove" + i + ".png");
        }
        for (int i = 0; i < 12; i++){
            attack[i] = new GreenfootImage("Troops/Knight/KnightAttack" + i + ".png");
        }
    }
    
    /***
     * Animates the movement of Knight by switching to the next image every 4 acts
     */
    public void animate(){
        animationCount++;
        // if the count reaches the end, reset it back to 0
        if (animationCount == 48){
            animationCount = 0;
        }
        // change the images every 4 acts
        setImage(animations[animationCount / 4]);
    }
    
    /***
     * Animates the Knight's attack by switching to the next image every 4 acts
     */
    public void attackAnimate(){
        attackCount++;
        // if the count reaches the end, reset it back to 0
        if (attackCount == 48){
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

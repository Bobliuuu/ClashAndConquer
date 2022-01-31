import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Archer Troop. A ranged fighter that shoots arrows from a distance.
 * 
 * @author Matthew Gong 
 * @version January 2022
 */
public class Archer extends Troop
{
    // the arrays to contain the images for the walking and attack animations
    private GreenfootImage[] walkAnimations, attackAnimations;
    // integers for tracking the current act/frame count and the attack's cooldown
    private int walkCount, attackCount, cooldown;
    
    /***
     * The most complicated constructor, able to customize health, attack, movement speed, attack speed, attack radius, and alignment
     * 
     * @param health            The amount of health the Archer has
     * @param attk              The amount of attack points the Archer has
     * @param movementSpeed     How fast the Archer moves
     * @param attackSpeed       How fast the Archer attacks
     * @param isEnemy           Whether the Archer belongs to the enemy
     */
    public Archer(int health, int attack, double movementSpeed, double attackSpeed, boolean isEnemy){
        super(health, attack, movementSpeed, attackSpeed, 150, 90, isEnemy);
        // setting the inital values for the counters and cooldown
        walkCount = 0;
        attackCount = -1;
        cooldown = (int)(this.attackSpeed * 4);
        // initalizing all the images into the arrays
        walkAnimations = new GreenfootImage[12];
        attackAnimations = new GreenfootImage[12];
        for(int i = 0; i < 12; i++){
            walkAnimations[i] = new GreenfootImage("Troops/Archer/ArcherMove" + i + ".png");
            attackAnimations[i] = new GreenfootImage("Troops/Archer/ArcherAttack" + i + ".png");
        }
    }

    /***
     * Summons a projectile to attack the nearest target then restarts the cooldown for the next attack
     */
    public void attack()
    {
        // check if there is a target and that the cooldown has finished
        if(this.target != null && cooldown == 0){
            // summons a projectile to attack the target
            getWorld().addObject(new Projectile(this.attack, 10, this.target), getX(), getY());
            attackAnimate();
            // resets the cooldown
            cooldown = (int)(this.attackSpeed * 4);
        }
        else{
            // decreases the cooldown
            cooldown--;
        }
    }
    
    /***
     * Animates the movement of Archer by switching to the next image every 4 acts
     */
    public void animate(){
        walkCount++;
        // if the count reaches the end, reset it back to 0
        if(walkCount == 48) walkCount = 0;
        // change the images every 4 acts
        setImage(walkAnimations[walkCount/4]);
    }
    
    /***
     * Animates the Archer's attack by switching to the next image every 4 acts
     */
    public void attackAnimate(){
        attackCount++;
        // if the count reaches the end, reset it back to 0
        if(attackCount == 48) attackCount = 0;
        // change the images every 4 acts
        setImage(attackAnimations[attackCount/4]);
    }
    
    /***
     * Returns the current act count for the attack animation
     */
    public int getAttackCounter(){
        return attackCount;
    }
}

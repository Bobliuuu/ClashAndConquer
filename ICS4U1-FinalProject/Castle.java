import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Castle Class
 * <p>
 * A subclass of the building superclass. A castle is the primary target of every level. 
 * 
 * @author Jerry Zhu, Matthew Gong
 * @version January 2022
 */
public class Castle extends Building
{   
    // Instance variables
    private int attack;
    private int actNumber = 0;
    private Troop currentTarget;
    private double attackRadius;
    private GreenfootImage image;
    private int cooldown;
    private SuperStatBar healthBar;
    private boolean isDead;
    
    /**
     * Default constructor for the castle class
     * 
     * @param isEnemy    Whether the castle belongs to the enemy or not
     */
    public Castle(boolean isEnemy){
        this.isEnemy = isEnemy;
        this.health = 250;
        if (isEnemy){
            setImage("enemytower.png");
            image = new GreenfootImage("enemytower.png");
        }
        else {
            setImage("mytower.png");
            image = new GreenfootImage("mytower.png");
        }
        getImage().scale(150, 150);
        attackRadius = 200;
        cooldown = 0;
        this.isDead = false;
        healthBar = new SuperStatBar(this.health, this.health, this, 48, 4, 36, Color.GREEN, Color.RED, false);
    }
    
    /**
     * Similar to above, but with the ability to customize width and height of castle and radius of attack
     * 
     * @param isEnemy    Whether the castle belongs to the enemy or not
     * @param length     The desired width of the castle
     * @param width      The desired height of the castle
     * @param radius     Attack radius of the castle
     */
    public Castle(boolean isEnemy, int width, int height, double radius){
        this.isEnemy = isEnemy;
        this.attackRadius = radius;
        this.health = 250;
        if (isEnemy){
            setImage("enemytower.png");
            image = new GreenfootImage("enemytower.png");
        }
        else {
            setImage("mytower.png");
            image = new GreenfootImage("mytower.png");
        }
        getImage().scale(width, height);
        cooldown = 0;
        this.isDead = false;
        healthBar = new SuperStatBar(this.health, 50, this, 48, 4, 36, Color.GREEN, Color.RED, false);
    }
    
    /**
     * Similar to above, but with the ability to customize health
     * 
     * @param isEnemy    Whether the castle belongs to the enemy or not
     * @param length     The desired width of the castle
     * @param width      The desired height of the castle
     * @param radius     Attack radius of the castle
     * @param health     The amount of health the tower has
     */
    public Castle(boolean isEnemy, int width, int height, double radius, int health){
        this.isEnemy = isEnemy;
        this.attackRadius = radius;
        this.health = health;
        if (isEnemy){
            setImage("enemytower.png");
            image = new GreenfootImage("enemytower.png");
        }
        else {
            setImage("mytower.png");
            image = new GreenfootImage("mytower.png");
        }
        getImage().scale(width, height);
        cooldown = 0;
        this.isDead = false;
        healthBar = new SuperStatBar(this.health, 50, this, 48, 4, 36, Color.GREEN, Color.RED, false);
    }
    
    /**
     * Act - do whatever the Castle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (((Level)getWorld()).finished){
            return;
        }
        if (!isDead){
            checkForEnemy();
            if(cooldown != 0) {
                cooldown--;
            }
            else {
                attack();
            }
            if(actNumber == 0){
                getWorld().addObject(healthBar, getX(), getY()+200);
            }
            healthBar.update(this.health);
            actNumber++;
        }
    }
    
    /**
     * Finds the closest Troop that belong to the enemy within its radius
     */
    private void checkForEnemy(){
        currentTarget = null;
        ArrayList<Troop> possible = (ArrayList<Troop>)getWorld().getObjects(Troop.class);
        double closestDistance = 900;
        for(Troop curr : possible){
            if (curr.enemy() == isEnemy){
                continue;
            }
            double dis = findDistanceBetween(curr, this);
            if(dis <= attackRadius){
                if(dis < closestDistance){
                    closestDistance = dis;
                    currentTarget = curr;
                }
            }
        }
    }
    
    /**
     * Summons a projectile to attack the nearest enemy, if one is available
     */
    private void attack(){
        if(currentTarget != null){
            getWorld().addObject(new Projectile(30, 8, currentTarget), getX(), getY());
            cooldown = 40;
        }
    }
    
    /**
     * Finds the distance between two actors. 
     * 
     * @param a1           The first actor.
     * @param a2           The second actor. 
     * @return double      The distance between the two actors. 
     */
    private double findDistanceBetween(Actor a1, Actor a2){
        return Math.sqrt(Math.pow(a1.getX() - a2.getX(), 2) + Math.pow(a1.getY() - a2.getY(), 2));
    }
    
    /**
     * Subtracts the health from the castle. 
     * 
     * @param value     The health to be subtracted. 
     */
    public void subtractHealth(int value){
        System.out.println("backup");
        if (!isDead){
            health -= value;
            System.out.println(health);
            if (health <= 0){
                isDead = true;
                Image destroyedCastle = new Image(new GreenfootImage("castledestroyed.png"));
                ((Level)getWorld()).addObject(destroyedCastle, getX(), getY());
                if (isEnemy){
                    ((Level)getWorld()).setVictory();
                }
                else {
                    ((Level)getWorld()).setDefeat();
                }
                getWorld().removeObject(this); 
            }
        }
    }
    
    /**
     * Check if the castle has been destroyed.
     * @return boolean         Whether or not the castle is destroyed. 
     */
    public boolean getIsDead(){
        return isDead;
    }
    
    /**
     * Changes the health of the Castle
     * 
     * @param health    The new health of the Castle
     */
    public void setHealth(int health){
        this.health = health;
    }
}

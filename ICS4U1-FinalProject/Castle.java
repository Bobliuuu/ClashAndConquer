import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Castle Class
 * <p>
 * A subclass of the building superclass. A castle is the primary target of every level. 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Castle extends Building
{
    // Instance variables
    private boolean isEnemy;
    private GreenfootImage image;
    private Troop currentTarget;
    private double attackRadius;
    private int cooldown;
    
    /**
     * Default constructor for the castle class
     * 
     * @param isEnemy    Whether the castle belongs to the enemy or not
     */
    public Castle(boolean isEnemy){
        this.isEnemy = isEnemy;
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
    }
    
    /**
     * Act - do whatever the Castle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkForEnemy();
        if(cooldown != 0) cooldown--;
        else attack();
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
            getWorld().addObject(new Projectile(10, 8, currentTarget), getX(), getY());
            cooldown = 40;
        }
    }
    
    private double findDistanceBetween(Actor a1, Actor a2){
        return Math.sqrt(Math.pow(a1.getX() - a2.getX(), 2) + Math.pow(a1.getY() - a2.getY(), 2));
    }
}

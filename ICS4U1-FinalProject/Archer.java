import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Archer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Archer extends Troop
{
    public Archer(int health, int attack, double movementSpeed, double attackSpeed, double radius, boolean isEnemy){
        super(health, attack, movementSpeed, attackSpeed, radius, isEnemy);
    }
    /**
     * Act - do whatever the Archer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public void animate(){
        
    }
    
    public int getAttackCounter(){
        return 0;
    }
    
    public void attackAnimate(){
        
    }
}

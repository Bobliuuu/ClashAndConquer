import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Poison here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Poison extends Spell
{
    private GreenfootImage[] animations;
    private int animateCount, duration = 204;
    
    public Poison(int atk, double spd, int rds, boolean enemy, int x, int y){
        super(atk, spd, rds, enemy, x, y);
        animateCount = -1;
        animations = new GreenfootImage[51];
        for(int i = 0; i < 51; i++) animations[i] = new GreenfootImage("Spells/Poison/PoisonAttack" + i + ".png");
    }
    
    /**
     * Act - do whatever the Poison wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(duration != 0){
            animate();
            if(duration % 4 == 0) attack();
            duration--;
        }
        else{
            getWorld().removeObject(this);
        }
    }
    
    public void animate(){
        animateCount++;
        if(animateCount == 204) animateCount = 0;
        setImage(animations[animateCount/4]);
    }
    
    public void attackAnimate(){
    
    }
    
    public void setDuration(int dur){
        duration = dur;
    }
    
    public void attack(){
        ArrayList<Actor> targets = (ArrayList<Actor>)getObjectsInRange(radius, Actor.class);
            
        for(int i = 0; i < targets.size(); i++){
            Actor current = targets.get(i);
                
            if(current instanceof Troop){
                if(((Troop)current).enemy() != fromEnemy){
                    ((Troop)current).subtractHealth(attack);
                }
            }
            else if(current instanceof Building){
                if(((Building)current).enemy() != fromEnemy){
                    ((Building)current).subtractHealth(attack);
                }
            }
        }
    }
}


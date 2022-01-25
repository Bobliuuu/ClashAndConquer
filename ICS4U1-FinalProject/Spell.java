import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Spell here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Spell extends SuperSmoothMover
{
    protected int attack, radius, lastX, lastY;
    protected double speed;
    protected boolean fromEnemy;
    
    public Spell(int attack, double speed, int radius, boolean fromEnemy, int x, int y){
        this.attack = attack;
        this.speed = speed;
        this.radius = radius;
        this.fromEnemy = fromEnemy;
        this.lastX = x;
        this.lastY = y;
    }
    
    public abstract void animate();
    
    /**
     * Act - do whatever the Spell wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        turnTowards(lastX, lastY);
        move(speed);
        checkPosition();
    }
    
    public void checkPosition(){
        if(getX() == lastX && getY() == lastY){
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
            ((Level)getWorld()).removeObject(this);
        }
    }
}

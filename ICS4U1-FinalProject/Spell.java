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
    protected int attack, radius, lastX, lastY, countdown;
    protected double speed;
    protected boolean fromEnemy;
    
    public Spell(int attack, double speed, int radius, boolean fromEnemy, int x, int y){
        this.attack = attack;
        this.speed = speed;
        this.radius = radius;
        this.fromEnemy = fromEnemy;
        this.lastX = x;
        this.lastY = y;
        countdown = 0;
    }
    
    public abstract void animate();
    public abstract void attackAnimate();
    
    /**
     * Act - do whatever the Spell wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(countdown == 0){
            animate();
            turnTowards(lastX, lastY);
            move(speed);
            checkPosition();
        }
        else{
            attackAnimate();
            countdown--;
            if(countdown == 1) ((Level)getWorld()).removeObject(this);
        }
    }
    
    public void checkPosition(){
        if(findDistanceBetween(lastX, lastY) < speed){
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
            countdown = 36;
        }
    }
    
    private double findDistanceBetween(double x, double y){
        return Math.sqrt(Math.pow(this.getX() - x, 2) + Math.pow(this.getY() - y, 2));
    }
}

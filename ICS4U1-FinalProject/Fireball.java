import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends Spell
{
    public Fireball(int atk, double spd, int rds, boolean enemy, int x, int y){
        super(atk, spd, rds, enemy, x, y);
        GreenfootImage circle = new GreenfootImage(25, 25);
        circle.setColor(Color.RED);
        circle.fillOval(0, 0, 25, 25);
        setImage(circle);
    }
 
    public void animate(){
    
    }
}

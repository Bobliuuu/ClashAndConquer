import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Knight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knight extends Troop
{
    private GreenfootImage[] animations;
    private int animationCount;
    public Knight(){
        super(100, 10, 1, 3, 100, false);
        animationCount = 0;
        animations = new GreenfootImage[12];
        for (int i = 0; i < 12; i++){
            animations[i] = new GreenfootImage("Troops/Knight/KnightMove" + i + ".png");
        }
    }
    
    public void animate(){
        animationCount++;
        if (animationCount == 48){
            animationCount = 0;
        }
        setImage(animations[animationCount / 4]);
    }
    
    public void attack(){
        
    }
}

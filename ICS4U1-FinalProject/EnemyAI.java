import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyAI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyAI extends Actor
{
    private int countdown;
    private int level;
    private int countSpawn;
    
    public EnemyAI(int level){
        this.level = level;
        if (level == 1){
            countdown = 200;
        }
        else if (level == 2){
            
        }
        else if (level == 3){
            
        }
        setImage((GreenfootImage)null);
    }
    /**
     * Act - do whatever the EnemyAI wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (level == 1){
            tryToSpawn(130, 200);
        }
    }
    
    private void tryToSpawn(int x, int y){
        if(countdown == 0){
            Knight knight = new Knight(true);
            getWorld().addObject(knight, x + Greenfoot.getRandomNumber(540), y);
            countdown = 100;
            countSpawn++;
            if (countSpawn == 3){
                countSpawn = 0;
            }
        }
        else{
            countdown--;
        }
    }
    
    public int getRandomNumberRange(int low, int high){
       int rand = Greenfoot.getRandomNumber(high - low + 1);
       return rand + low;
    }
}

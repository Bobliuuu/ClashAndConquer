import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * EnemyAI Class
 * <p>
 * Class that spawns the enemies for each level
 * 
 * @author Matthew Gong
 * @version January 2022
 */
public class EnemyAI extends Actor
{
    // Instance variables
    private int countdown;
    private int level;
    private int countSpawn;
    
    /**
     * Basic constructor for an enemy AI, given a certain level
     * @param level     The level that the enemy AI is being creeated on. 
     */
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
    
    /**
     * Trying to spawn an object at a location. 
     */
    private void tryToSpawn(int x, int y){
        if(countdown == 0){
            Knight knight = new Knight(100, 10, 1, 3, 80, true);
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
    
    /**
     * Get a random number from low to high inclusive. 
     */
    public int getRandomNumberRange(int low, int high){
       int rand = Greenfoot.getRandomNumber(high - low + 1);
       return rand + low;
    }
}

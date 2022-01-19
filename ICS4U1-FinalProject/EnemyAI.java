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
    private int countdown, originalTime;
    private int attackIncrease, healthIncrease;
    private int level;
    private boolean half;
    
    /**
     * Basic constructor for an enemy AI, given a certain level
     * @param level     The level that the enemy AI is being creeated on. 
     */
    public EnemyAI(int level, boolean half){
        this.level = level;
        if (level == 1){
            countdown = 200 - Greenfoot.getRandomNumber(30);
            originalTime = countdown;
        }
        else if (level == 2){
            countdown = 175 - Greenfoot.getRandomNumber(40);
            originalTime = countdown;
        }
        else if (level == 3){
            countdown = 125 - Greenfoot.getRandomNumber(50);
            originalTime = countdown;
        }
        else if (level == 4){
            countdown = 100 - Greenfoot.getRandomNumber(40);
            originalTime = countdown;
        }
        countdown *= 2;
        originalTime *= 2;
        attackIncrease = (int)(Math.pow(2, level-1));
        healthIncrease = (int)Math.pow(3, level-1);
        
        if(half){
            attackIncrease /= 2;
            healthIncrease /= 2;
            countdown += 150;
            originalTime += 150;
        }
        
        setImage((GreenfootImage)null);
    }
    
    /**
     * Act - do whatever the EnemyAI wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        tryToSpawn(130, 200);
    }
    
    /**
     * Trying to spawn an object at a location. 
     */
    private void tryToSpawn(int x, int y){
        if(countdown == 0){
            Knight knight = new Knight(100 + healthIncrease, 10 + attackIncrease, 1, 3, 80, true);
            getWorld().addObject(knight, x + Greenfoot.getRandomNumber(540), y);
            countdown = originalTime;
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

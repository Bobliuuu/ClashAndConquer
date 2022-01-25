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
    private int[] spawnChances;
    private int level;
    private boolean half;
    
    /**
     * Basic constructor for an enemy AI, given a certain level
     * @param level     The level that the enemy AI is being creeated on. 
     */
    public EnemyAI(int level, boolean half){
        this.level = level;
        spawnChances = new int[2];
        if (level == 1){
            countdown = 200 - Greenfoot.getRandomNumber(30);
            originalTime = countdown;
            spawnChances[0] = 100;
            spawnChances[1] = 0;
            attackIncrease = 1;
            healthIncrease = 1;
        }
        else if (level == 2){
            countdown = 175 - Greenfoot.getRandomNumber(40);
            originalTime = countdown;
            spawnChances[0] = 100;
            spawnChances[1] = 0;
            attackIncrease = 2;
            healthIncrease = 3;
        }
        else if (level == 3){
            countdown = 125 - Greenfoot.getRandomNumber(50);
            originalTime = countdown;
            spawnChances[0] = 90;
            spawnChances[1] = 10;
            attackIncrease = 4;
            healthIncrease = 9;
        }
        else if (level == 4){
            countdown = 100 - Greenfoot.getRandomNumber(40);
            originalTime = countdown;
            spawnChances[0] = 75;
            spawnChances[1] = 25;
            attackIncrease = 8;
            healthIncrease = 27;
        }
        // attackIncrease = (int)(Math.pow(2, level-1));
        // healthIncrease = (int)Math.pow(3, level-1);
        
        if(half){
            attackIncrease /= 2;
            healthIncrease /= 2;
            countdown += 30;
            originalTime += 30;
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
            spawnTroop(x, y);
            countdown = originalTime;
        }
        else{
            countdown--;
        }
    }
    
    private void spawnTroop(int x, int y){
        int chance = 0, rand = Greenfoot.getRandomNumber(100)+1;
        
        for(int i = 0; i < 2; i++){
            if(rand > chance && rand <= chance+spawnChances[i]){
                if(i == 0){
                    Knight knight = new Knight(100 + healthIncrease, 10 + attackIncrease, 1, 3, 60, true);
                    getWorld().addObject(knight, x + Greenfoot.getRandomNumber(540), y);
                }
                else if(i == 1){
                    Archer archer = new Archer(100 + healthIncrease, 10 + attackIncrease, 1, 5, 120, true);
                    getWorld().addObject(archer, x + Greenfoot.getRandomNumber(540), y);
                }
            }
            else chance += spawnChances[i];
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

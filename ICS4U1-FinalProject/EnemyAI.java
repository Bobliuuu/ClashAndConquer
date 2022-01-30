import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * The class that controls how the enemy acts during the levels and how it changes depending on the level
 * 
 * @author Matthew Gong, Daniel Qian
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
     * 
     * @param level     The level that the enemy AI is being creeated on
     * @param half      Whether the enemyAI is on easy mode 
     */
    public EnemyAI(int level, boolean half){
        this.level = level;
        spawnChances = new int[5];
        
        // setting the countdown between summoning a troop, increases to health and attack, and spawn chances for each troop depending on level
        // setting the countdown between summoning a troop, increases to health and attack, and spawn chances for each troop depending on level
        if (level == 1){
            countdown = 200 - Greenfoot.getRandomNumber(30);
            originalTime = countdown;
            spawnChances[0] = 100;
            spawnChances[1] = 0;
            spawnChances[2] = 0;
            spawnChances[3] = 0;
            spawnChances[4] = 0;
            attackIncrease = 6;
            healthIncrease = 6;
        }
        else if (level == 2){
            countdown = 175 - Greenfoot.getRandomNumber(40);
            originalTime = countdown;
            spawnChances[0] = 100;
            spawnChances[1] = 0;
            spawnChances[2] = 0;
            spawnChances[3] = 0;
            spawnChances[4] = 0;
            attackIncrease = 12;
            healthIncrease = 12;
        }
        else if (level == 3){
            countdown = 175 - Greenfoot.getRandomNumber(50);
            originalTime = countdown;
            spawnChances[0] = 90;
            spawnChances[1] = 10;
            spawnChances[2] = 0;
            spawnChances[3] = 0;
            spawnChances[4] = 0;
            attackIncrease = 24;
            healthIncrease = 24;
        }
        else if (level == 4){
            countdown = 175 - Greenfoot.getRandomNumber(50);
            originalTime = countdown;
            spawnChances[0] = 75;
            spawnChances[1] = 25;
            spawnChances[2] = 0;
            spawnChances[3] = 0;
            spawnChances[4] = 0;
            attackIncrease = 36;
            healthIncrease = 36;
        }
        else if (level == 5){
            countdown = 175 - Greenfoot.getRandomNumber(50);
            originalTime = countdown;
            spawnChances[0] = 60;
            spawnChances[1] = 25;
            spawnChances[2] = 15;
            spawnChances[3] = 0;
            spawnChances[4] = 0;
            attackIncrease = 48;
            healthIncrease = 48;
        }
        else if (level == 6){
            countdown = 175 - Greenfoot.getRandomNumber(50);
            originalTime = countdown;
            spawnChances[0] = 55;
            spawnChances[1] = 25;
            spawnChances[2] = 20;
            spawnChances[3] = 0;
            spawnChances[4] = 0;
            attackIncrease = 60;
            healthIncrease = 60;
        }
        else if (level == 7){
            countdown = 175 - Greenfoot.getRandomNumber(50);
            originalTime = countdown;
            spawnChances[0] = 45;
            spawnChances[1] = 25;
            spawnChances[2] = 20;
            spawnChances[3] = 10;
            spawnChances[4] = 0;
            attackIncrease = 72;
            healthIncrease = 72;
        }
        else if (level == 8){
            countdown = 175 - Greenfoot.getRandomNumber(50);
            originalTime = countdown;
            spawnChances[0] = 45;
            spawnChances[1] = 25;
            spawnChances[2] = 20;
            spawnChances[3] = 10;
            spawnChances[4] = 0;
            attackIncrease = 84;
            healthIncrease = 84;
        }
        else if (level == 9){
            countdown = 175 - Greenfoot.getRandomNumber(50);
            originalTime = countdown;
            spawnChances[0] = 45;
            spawnChances[1] = 25;
            spawnChances[2] = 20;
            spawnChances[3] = 10;
            spawnChances[4] = 0;
            attackIncrease = 96;
            healthIncrease = 96;
        }
        else if (level == 10){
            countdown = 175 - Greenfoot.getRandomNumber(50);
            originalTime = countdown;
            spawnChances[0] = 45;
            spawnChances[1] = 25;
            spawnChances[2] = 20;
            spawnChances[3] = 10;
            spawnChances[4] = 0;
            attackIncrease = 108;
            healthIncrease = 108;
        }
        else if (level == 11){
            countdown = 175 - Greenfoot.getRandomNumber(50);
            originalTime = countdown;
            spawnChances[0] = 35;
            spawnChances[1] = 25;
            spawnChances[2] = 20;
            spawnChances[3] = 10;
            spawnChances[4] = 10;
            attackIncrease = 120;
            healthIncrease = 120;
        }
        else if (level == 12){
            countdown = 175 - Greenfoot.getRandomNumber(50);
            originalTime = countdown;
            spawnChances[0] = 30;
            spawnChances[1] = 25;
            spawnChances[2] = 20;
            spawnChances[3] = 15;
            spawnChances[4] = 10;
            attackIncrease = 132;
            healthIncrease = 132;
        }
        else if (level == 13){
            countdown = 175 - Greenfoot.getRandomNumber(50);
            originalTime = countdown;
            spawnChances[0] = 30;
            spawnChances[1] = 25;
            spawnChances[2] = 20;
            spawnChances[3] = 15;
            spawnChances[4] = 10;
            attackIncrease = 144;
            healthIncrease = 144;
        }
        else if (level == 14){
            countdown = 175 - Greenfoot.getRandomNumber(50);
            originalTime = countdown;
            spawnChances[0] = 38;
            spawnChances[1] = 25;
            spawnChances[2] = 20;
            spawnChances[3] = 15;
            spawnChances[4] = 2;
            attackIncrease = 156;
            healthIncrease = 156;
        }
        else if (level == 15){
            countdown = 165 - Greenfoot.getRandomNumber(50);
            originalTime = countdown;
            spawnChances[0] = 26;
            spawnChances[1] = 25;
            spawnChances[2] = 30;
            spawnChances[3] = 15;
            spawnChances[4] = 4;
            attackIncrease = 168;
            healthIncrease = 168;
        }
        
        // decreases the atack and health increase while increasing the time between summonings for easy mode
        if(half){
            attackIncrease /= 4;
            healthIncrease /= 4;
            countdown += 75;
            originalTime += 75;
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
     * 
     * @param x     Its x-coordinate upon summoning
     * @param y     Its y-coordinate upon summoning
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
    
    /**
     * Chooses which troop to spawn and adds it to the World
     * 
     * @param x     Its x-coordinate upon summoning
     * @param y     Its y-coordinate upon summoning
     */
    private void spawnTroop(int x, int y){
        // gets a random number between 1 and 100
        int chance = 0, rand = Greenfoot.getRandomNumber(100)+1;
        
        for(int i = 0; i < 5; i++){
            // checking if the random number is between the right numbers
            // say if knight's chance is 60% while Archer is 40% then is the random first between 1 and 60.
            // if not then is it between 61 and 100
            if(rand > chance && rand <= chance+spawnChances[i]){
                // spawning different troops depending on i
                if(i == 0){
                    Knight knight = new Knight(100 + healthIncrease, 10 + attackIncrease, 1, 3, 60, true);
                    getWorld().addObject(knight, x + Greenfoot.getRandomNumber(540), y);
                }
                else if(i == 1){
                    Archer archer = new Archer(70 + healthIncrease, 8 + attackIncrease, 1, 5, 120, true);
                    getWorld().addObject(archer, x + Greenfoot.getRandomNumber(540), y);
                }
                else if(i == 2){
                    Skeleton skeleton = new Skeleton(20 + healthIncrease, 5 + attackIncrease, 1, 6, 140, true);
                    int pos = x + Greenfoot.getRandomNumber(540);
                    getWorld().addObject(skeleton, pos+20, y);
                    getWorld().addObject(skeleton, pos-20, y);
                    getWorld().addObject(skeleton, pos+20, y+20);
                    getWorld().addObject(skeleton, pos+20, y-20);
                    getWorld().addObject(skeleton, pos-20, y+20);
                    getWorld().addObject(skeleton, pos-20, y-20);
                }
                else if(i == 3){
                    Giant giant = new Giant(240 + healthIncrease, 18 + attackIncrease, 1, 6, 140, true);
                    getWorld().addObject(giant, x + Greenfoot.getRandomNumber(540), y);
                }
                else if(i == 4){
                    Tombstone tombstone = new Tombstone(200, true, 100);
                    getWorld().addObject(tombstone, x + Greenfoot.getRandomNumber(540), y);
                }
            }
            else chance += spawnChances[i];
        }
    }
    
    /**
     * Get a random number from low to high inclusive. 
     * 
     * @param low   The lower boundary
     * @param high  The upper boundary
     */
    public int getRandomNumberRange(int low, int high){
       int rand = Greenfoot.getRandomNumber(high - low + 1);
       return rand + low;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

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
        //ArrayList<Castle> possible = (ArrayList<Castle>)getWorld().getObjects(Castle.class);
        
        if (level == 1){
            countdown = 200 - Greenfoot.getRandomNumber(30);
            originalTime = countdown;
            spawnChances[0] = 100;
            spawnChances[1] = 0;
            attackIncrease = 6;
            healthIncrease = 6;
        }
        else if (level == 2){
            countdown = 175 - Greenfoot.getRandomNumber(40);
            originalTime = countdown;
            spawnChances[0] = 100;
            spawnChances[1] = 0;
            attackIncrease = 12;
            healthIncrease = 12;
        }
        else if (level == 3){
            countdown = 125 - Greenfoot.getRandomNumber(50);
            originalTime = countdown;
            spawnChances[0] = 90;
            spawnChances[1] = 10;
            attackIncrease = 24;
            healthIncrease = 24;
        }
        else if (level == 4){
            countdown = 100 - Greenfoot.getRandomNumber(40);
            originalTime = countdown;
            spawnChances[0] = 75;
            spawnChances[1] = 25;
            attackIncrease = 36;
            healthIncrease = 36;
        }
        else if (level == 5){
            countdown = 70 - Greenfoot.getRandomNumber(20);
            originalTime = countdown;
            spawnChances[0] = 75;
            spawnChances[1] = 25;
            attackIncrease = 48;
            healthIncrease = 48;
        }
        else if (level == 6){
            countdown = 60 - Greenfoot.getRandomNumber(20);
            originalTime = countdown;
            spawnChances[0] = 75;
            spawnChances[1] = 25;
            attackIncrease = 60;
            healthIncrease = 60;
        }
        else if (level == 7){
            countdown = 50 - Greenfoot.getRandomNumber(25);
            originalTime = countdown;
            spawnChances[0] = 75;
            spawnChances[1] = 25;
            attackIncrease = 72;
            healthIncrease = 72;
        }
        else if (level == 8){
            countdown = 50 - Greenfoot.getRandomNumber(25);
            originalTime = countdown;
            spawnChances[0] = 70;
            spawnChances[1] = 30;
            attackIncrease = 84;
            healthIncrease = 84;
        }
        else if (level == 9){
            countdown = 50 - Greenfoot.getRandomNumber(25);
            originalTime = countdown;
            spawnChances[0] = 65;
            spawnChances[1] = 35;
            attackIncrease = 96;
            healthIncrease = 96;
        }
        else if (level == 10){
            countdown = 50 - Greenfoot.getRandomNumber(25);
            originalTime = countdown;
            spawnChances[0] = 60;
            spawnChances[1] = 40;
            attackIncrease = 108;
            healthIncrease = 108;
        }
        else if (level == 11){
            countdown = 50 - Greenfoot.getRandomNumber(25);
            originalTime = countdown;
            spawnChances[0] = 55;
            spawnChances[1] = 45;
            attackIncrease = 120;
            healthIncrease = 120;
        }
        else if (level == 12){
            countdown = 50 - Greenfoot.getRandomNumber(25);
            originalTime = countdown;
            spawnChances[0] = 50;
            spawnChances[1] = 50;
            attackIncrease = 132;
            healthIncrease = 132;
        }
        else if (level == 13){
            countdown = 40 - Greenfoot.getRandomNumber(15);
            originalTime = countdown;
            spawnChances[0] = 50;
            spawnChances[1] = 50;
            attackIncrease = 144;
            healthIncrease = 144;
        }
        else if (level == 14){
            countdown = 40 - Greenfoot.getRandomNumber(15);
            originalTime = countdown;
            spawnChances[0] = 50;
            spawnChances[1] = 50;
            attackIncrease = 156;
            healthIncrease = 156;
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

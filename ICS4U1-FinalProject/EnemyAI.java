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
    
    public EnemyAI(int level){
        this.level = level;
        if (level == 1){
            countdown = 100;
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
        tryToSpawn(400, 200);
    }
    
    private void tryToSpawn(int x, int y){
        if(countdown == 0){
            Knight current = new Knight();
            current.setEnemy(true);
            getWorld().addObject(current, x, y);
            countdown = 100;
        }
        else{
            countdown--;
        }
    }
}

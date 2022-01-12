import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level here.
 * 
 * Coordinates: 
 * Bridges: (127, 385), (673, 385), (398, 385)
 * End of road: (397, 170):
 * Road turns: (128,590), (674, 590) [my side], (128, 170), (672, 170)
 * 
 * @author Jerry Zhu
 * @version January 2022
 */
public class Level extends World
{
    // Instance varaibles
    private Image levelMap;
    private Image elixir;
    private Castle myCastle;
    private Castle enemyCastle;
    private ElixirBar elixirBar;
    
    /**
     * Level world constructor
     */
    public Level()
    {    
        // Create a new world with 800 x 836 cells with a cell size of 1x1 pixels.
        super(800, 836, 1); 
        levelMap = new Image(new GreenfootImage("background.png"));
        addObject(levelMap, getWidth()/2, getHeight()/2);
        
        myCastle = new Castle(false);
        addObject(myCastle, 400, 680);
        
        enemyCastle = new Castle(true, 130, 130);
        addObject(enemyCastle, 400, 100);
        
        elixir = new Image(new GreenfootImage("elixirbar.png"));
        addObject(elixir, 400, 770);

        elixirBar = new ElixirBar();
        addObject(elixirBar, 400, 770);
    }
    
    public void act(){
        elixirBar.addElixir();
    }
}

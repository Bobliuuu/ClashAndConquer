import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Elixir Bar Class
 * <p>
 * Class that displays an elixir bar that charges up over time. 
 * 
 * @author Jerry Zhu
 * @version January 2022
 */
public class ElixirBar extends Actor
{
    // Instance variables
    private final double MAX_ELIXIR = 20.01;
    private double elixir;
    private double elixirSpeed;
    private int size;
    private GreenfootImage image;
    
    /**
     * Basic constructor for the elixir bar
     */
    public ElixirBar()
    {
        image = new GreenfootImage("elixir.png");
        image.scale(35, 20);
        setImage(image);
        elixir = 1;
        elixirSpeed = 0.008;
    }
    
    /**
     * Similar to above, but with the ability to customize elixir speed.
     * @param elixirSpeed     The speed of elixir increase. 
     */
    public ElixirBar(double elixirSpeed)
    {
        image = new GreenfootImage("elixir.png");
        image.scale(35, 20);
        setImage(image);
        elixir = 1;
        this.elixirSpeed = elixirSpeed;
    }
    
    /**
     * Similar to above, but with the ability to customize starting elixir.
     * @param elixirSpeed     The speed of elixir increase. 
     */
    public ElixirBar(double elixirSpeed, double elixir)
    {
        image = new GreenfootImage("elixir.png");
        image.scale(35, 20);
        setImage(image);
        this.elixir = elixir;
        this.elixirSpeed = elixirSpeed;
    }
    
    /**
     * Act method for the elixir bar class
     */
    public void act()
    {
        if(size < 350){
            changeSize();
        }
        setLocation(250 + (size / 2), 771);
        getWorld().showText("" + (int)(elixir), 235, 771);
    }
    
    /**
     * Update and change the size of the elixir bar
     */
    public void changeSize()
    {
        size = ((int)(elixir * 17)) + 1; // Truncate elixir to an integer
        if(elixir < 10){
            elixir += elixirSpeed;
        }
        image.scale(size, 20);
    }
    
    /**
     * Add elixir to the elixir bar at each level act
     */
    public void addElixir()
    {
        if (elixir + elixirSpeed <= MAX_ELIXIR){
            elixir += elixirSpeed;
        }
    }
    
    /**
     * Checks if elixir bar has the specified amount of elixir. 
     * @param amount        The amount of elixir used.
     * @return boolean      Whether the elixir has been subtracted from the elixir bar. 
     */
    public boolean hasElixir(int amount)
    {
        return elixir >= amount;
    }
    
    /**
     * Subtract elixir when a troop is deployed. 
     * @param amount        The amount of elixir used.
     * @return boolean      Whether the elixir has been subtracted from the elixir bar. 
     */
    public boolean useElixir(int amount)
    {
        if (elixir >= amount){
            elixir -= amount;
            return true;
        }
        return false;
    }
    
    /**
     * Increase the elixir speed by the elixir towers
     */    
    public void increaseElixirSpeed(double amount){
        elixirSpeed += amount;
    }
    
    /**
     * Decrease the elixir speed by the elixir towers being destroyed
     */    
    public void decreaseElixirSpeed(double amount){
        elixirSpeed -= amount;
    }
}


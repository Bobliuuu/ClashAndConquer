import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ElixirBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ElixirBar extends Actor
{
    private final double MAX_ELIXIR = 20.01;
    private double elixir;
    private double elixirSpeed;
    private int size;
    private GreenfootImage image;
    
    public ElixirBar()
    {
        image = new GreenfootImage("elixir.png");
        image.scale(35, 20);
        setImage(image);
        elixir = 1;
        elixirSpeed = 0.008;
    }
    
    public void act()
    {
        if(size < 350){
            changeSize();
        }
        setLocation(250 + (size / 2), 771);
        getWorld().showText("" + (int)(elixir), 235, 771);
    }
    
    public void changeSize()
    {
        size = ((int)(elixir * 17)) + 1;
        if(elixir < 10){
            elixir += elixirSpeed;
        }
        image.scale(size, 20);
    }
    
    public void addElixir()
    {
        if (elixir + elixirSpeed <= MAX_ELIXIR){
            elixir += elixirSpeed;
        }
    }
    
    public boolean useElixir(int amount)
    {
        if(elixir >= amount){
            elixir -= amount;
            return true;
        }
        return false;
    }
}


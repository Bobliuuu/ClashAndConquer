import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shop extends World
{
    private Image backButton;
    private Image gems;
    private TextLabel gemsLabel;
    private Image speedPotion;
    private Image invisibilityPotion;
    private Image healthPotion;
    private Image purchaseButton;
    //private Image settingsButton;
    
    /**
     * Constructor for objects of class Shop.
     * 
     */
    public Shop()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1); 
        
        backButton = new Image(new GreenfootImage("Buttons/backbutton.png"));
        backButton.getImage().scale(80, 50);
        addObject(backButton, 80, 50);
        
        gems = new Image(new GreenfootImage("gem.png"));
        gems.getImage().scale(100, 50);
        addObject(gems, 810, 50);
        
        Font font2 = new Font("Verdana", true, false, 20);
        gemsLabel = new TextLabel("0", font2);
        addObject(gemsLabel, 840, 90);
        
        speedPotion = new Image(new GreenfootImage("ShopItems/SpeedPotion.PNG"));
        //Image Size is 417x614
        speedPotion.getImage().scale(292, 430);
        addObject(speedPotion, 150, 300);
        
        invisibilityPotion = new Image(new GreenfootImage("ShopItems/InvisibilityPotion.PNG"));
        invisibilityPotion.getImage().scale(292, 430);
        addObject(invisibilityPotion, 450, 300);
        
        healthPotion = new Image(new GreenfootImage("ShopItems/HealthPotion.PNG"));
        healthPotion.getImage().scale(292, 430);
        addObject(healthPotion, 750, 300);
        
        purchaseButton = new Image(new GreenfootImage("ShopItems/PurchaseButton.PNG"));
        //337x74
        purchaseButton.getImage().scale(236, 52);
        addObject(purchaseButton, 450, 475);
        
        //settingsButton = new Image(new GreenfootImage("Buttons/settings.png"));
        //settingsButton.getImage().scale(100, 250);
        //addObject(settingsButton, 300, 150);
        
        //int fontSize = 35;
        //Color fontColor = new Color(233, 255, 221);
        //Color fontBgColor = new Color(0, 0, 0, 0);
        //Color bgColor = new Color(136, 206, 95);
     
        //GreenfootImage txtImg = new GreenfootImage("hello", fontSize, fontColor, fontBgColor);
        //GreenfootImage img = new GreenfootImage(txtImg.getWidth()+80, txtImg.getHeight()+40);
     
        //img.setColor(bgColor);
        //img.fillOval(-6, 10, 45, img.getHeight()-10);
        //img.fillOval(img.getWidth()-39, 10, 45, img.getHeight()-10);
        //img.fillRect(10, 10, img.getWidth()-20, img.getHeight());
        //img.drawImage(txtImg, (img.getWidth() - txtImg.getWidth())/2, (img.getHeight() - txtImg.getHeight())/2);
    }
    
    
    
    public void act(){
        checkClick();
    }
    
    public void checkClick(){
        if (Greenfoot.mouseClicked(backButton)){
            Start start = new Start();
            Greenfoot.setWorld(start);
        }
    }
}

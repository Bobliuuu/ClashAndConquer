import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
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
    private Image backItemButton;
    private Image forwardItemButton;
    private ArrayList<ShopItem> powerups;
    private ArrayList<TextButton> buybuttons;
    private int shopItemIndex;
    private int gemsCount;
    private GreenfootSound music;
    private UserInfo user;
    
    private void DisplayPowerUp (int position)
    {
        for (int counter = 0; counter < 3; counter++) {     
                ShopItem powerup = powerups.get((position + counter) % powerups.size());        
                powerup.getImage().scale(292, 430);
                addObject(powerup, 150+(300*counter), 300);
                              
                TextButton btn = buybuttons.get((position + counter) % powerups.size());
                addObject (btn, 150+(300*counter), 450);
        }
    }
    
    private void ClearPowerUp (int prevposition)
    {       
        for (int counter = 0; counter < 3; counter++) {     
                ShopItem powerup = powerups.get((prevposition + counter) % powerups.size());  
                TextButton btn = buybuttons.get((prevposition + counter) % powerups.size()); 
                removeObject(powerup);
                removeObject(btn);
        }
    }
    
    private void updateGemsCount() {
        removeObject(gemsLabel);
        Font font2 = new Font("Verdana", true, false, 20);
        gemsLabel = new TextLabel(String.valueOf(this.gemsCount), font2);
        addObject(gemsLabel, 800, 90);
        // gemsLabel.setText(String.valueOf(this.gemsCount));
    }
    
    /**
     * Constructor for objects of class Shop.
     * 
     */
    public Shop()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1); 
        
        if (UserInfo.isStorageAvailable()){
            user = UserInfo.getMyInfo();
            int gems = user.getInt(0);
            this.gemsCount = gems;
        }
        else {
            this.gemsCount = 0;
        }
        
        backButton = new Image(new GreenfootImage("Buttons/backbutton.png"));
        backButton.getImage().scale(80, 50);
        addObject(backButton, 80, 50);
        
        gems = new Image(new GreenfootImage("gem.png"));
        gems.getImage().scale(140, 50);
        addObject(gems, 810, 50);
        
        Font font2 = new Font("Verdana", true, false, 20);
        gemsLabel = new TextLabel("0", font2);
        addObject(gemsLabel, 800, 90);
        
        updateGemsCount();
        
        // back and forth buttons for going through powerups.
        backItemButton = new Image(new GreenfootImage("Buttons/backbutton.png"));
        backItemButton.getImage().scale(80, 50);
        addObject(backItemButton, 400, 560);
        
        forwardItemButton = new Image(new GreenfootImage("Buttons/backbutton.png"));
        forwardItemButton.getImage().scale(80, 50);
        forwardItemButton.getImage().rotate(180);
        addObject(forwardItemButton, 500, 560);
        
        this.powerups = new ArrayList<ShopItem>();
        this.buybuttons = new ArrayList<TextButton>();
        
        this.powerups.add(new ShopItem(100, 1));
        this.powerups.add(new ShopItem(200, 2));
        this.powerups.add(new ShopItem(300, 3));
        this.powerups.add(new ShopItem(400, 4));
        
        for (ShopItem powerup : powerups) {
            TextButton btn = new TextButton("Buy for " + String.valueOf(powerup.getCost()) + " Gems", 5, 190, true, Color.WHITE, Color.BLACK, Color.BLUE, Color.WHITE, Color.BLACK, new Font ("Verdana",true ,false ,14));
            buybuttons.add(btn);
        }
        
        this.shopItemIndex = 0;
        this.DisplayPowerUp(shopItemIndex);
    }
    
    public void started(){
        if (UserInfo.isStorageAvailable()){
            user = UserInfo.getMyInfo();
            if (user.getInt(3) == 0){
                if (music != null){
                    music.stop();
                }
            }
            else {
                if (music != null){
                    music.stop();
                }
                music = new GreenfootSound("mainsong" + user.getInt(3) + ".mp3");
                music.play();
            }
        }
    }
    
    public void act(){
        checkClick();
    }
    
    public void checkClick(){
        if (Greenfoot.mouseClicked(backButton)){
            if (music != null){
                music.stop();
            }
            Start start = new Start();
            start.started();
            Greenfoot.setWorld(start);
        }
        if (Greenfoot.mouseClicked(backItemButton)){
            if (shopItemIndex > 0) {
                this.ClearPowerUp (shopItemIndex);
                this.shopItemIndex = this.shopItemIndex - 1;
                this.DisplayPowerUp(shopItemIndex);
            }
        }
        if (Greenfoot.mouseClicked(forwardItemButton)){
            this.ClearPowerUp (shopItemIndex);
            this.shopItemIndex = this.shopItemIndex + 1;
            this.DisplayPowerUp(shopItemIndex);           
        } 
        
        for (int i = 0; i < buybuttons.size(); i++) {
            if (Greenfoot.mouseClicked(buybuttons.get(i))) {
                // if someone clicked ith button, then ith powerup will be purchased.
                ShopItem itm = powerups.get(i);
                this.gemsCount -= itm.getCost();
                updateGemsCount();
                // System.out.println(itm.getCost());
                // Subtract gems from user info class
                if (UserInfo.isStorageAvailable()){
                    user = UserInfo.getMyInfo();
                    user.setInt(0, this.gemsCount);
                    user.store();
                }
            }
        }
    }
}

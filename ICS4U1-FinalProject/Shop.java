import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Shop here.
 * 
 * @author Ibrahim Rahman
 * @version January 2022
 */
public class Shop extends World
{
    private GreenfootImage background;
    private Image backButton;
    private Image gems;
    private SuperTextBox gemsLabel;
    private Image speedPotion;
    private Image invisibilityPotion;
    private Image healthPotion;
    private Image purchaseButton;
    private Image backItemButton;
    private Image forwardItemButton;
    private ArrayList<ShopItem> powerups;
    private ArrayList<TextButton> buybuttons;
    private ArrayList<SuperTextBox> levels;
    private int shopItemIndex;
    private int gemsCount;
    private GreenfootSound music;
    private UserInfo user;
    private static final Color transparent = new Color(0, 0, 0, 0);
    private static final Color orange = new Color(255, 134, 45);
    
     private void displayPowerUp (int position)
    {
        for (int counter = 0; counter < 3; counter++) {     
            ShopItem powerup = powerups.get((position + counter) % powerups.size());        
            powerup.getImage().scale(292, 430);
            addObject(powerup, 150+(300*counter), 300);

            TextButton btn = buybuttons.get((position + counter) % powerups.size());
            addObject (btn, 150+(300*counter), 480);
            
            SuperTextBox lvl = levels.get((position + counter) % powerups.size());
            addObject (lvl, 150+(300*counter), 380);
        }
    }

    private void clearPowerUp (int prevposition)
    {       
        for (int counter = 0; counter < 3; counter++) {     
            ShopItem powerup = powerups.get((prevposition + counter) % powerups.size());  
            TextButton btn = buybuttons.get((prevposition + counter) % powerups.size());
            SuperTextBox lvl = levels.get((prevposition + counter) % powerups.size());
            removeObject(powerup);
            removeObject(btn);
            removeObject(lvl);
        }
    }
    
    private void updateGemsCount() {
        removeObject(gemsLabel);
        //Font font2 = new Font("Verdana", true, false, 20);
        gemsLabel.getImage().clear();
        gemsLabel.update(String.valueOf(this.gemsCount));
        addObject(gemsLabel, 800, 53);
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
        
        background = new GreenfootImage("images/Worlds/ShopBackground.jpg");
        background.scale(getWidth(), getHeight());
        setBackground(background);
        
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
        gemsLabel = new SuperTextBox("0", transparent, Color.BLACK, font2, false, 18*6, 0, transparent);
        addObject(gemsLabel, 800, 53);
        
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
        this.levels = new ArrayList<SuperTextBox>();
        
        // ShopItem(int cost, String type, int level)
        this.powerups.add(new ShopItem(100, "CastleHealth", 1));
        this.powerups.add(new ShopItem(300, "ElexirHealth", 2));
        this.powerups.add(new ShopItem(200, "ElexirSpeed", 2));
        this.powerups.add(new ShopItem(300, "PoisonAttack", 2));
        this.powerups.add(new ShopItem(300, "PoisonDuration", 2));
        this.powerups.add(new ShopItem(400, "FireballAttack", 1));
        this.powerups.add(new ShopItem(400, "FireballRadius", 1));
        this.powerups.add(new ShopItem(100, "ArrowAttack", 1));
        this.powerups.add(new ShopItem(200, "GiantAttack", 2));
        this.powerups.add(new ShopItem(300, "GiantHealth", 2));
        this.powerups.add(new ShopItem(400, "KnightAttack", 1));
        this.powerups.add(new ShopItem(100, "KnightHealth", 1));
        this.powerups.add(new ShopItem(200, "ArcherAttack", 2));
        this.powerups.add(new ShopItem(300, "ArcherHealth", 2));
        this.powerups.add(new ShopItem(400, "TombstoneCooldown", 1));
        this.powerups.add(new ShopItem(400, "TombstoneHealth", 1));

        
        for (ShopItem powerup : powerups) {
            TextButton btn = new TextButton("Buy for " + String.valueOf(powerup.getCost()) + " Gems", 7, 170, true, Color.DARK_GRAY, Color.WHITE, orange, Color.WHITE, transparent, new Font ("Open Sans",true ,false ,15));
            buybuttons.add(btn);
            
            SuperTextBox lvl = new SuperTextBox("Level " + String.valueOf(powerup.getLevel()), transparent, Color.BLACK, font2, false, 18*6, 0, transparent);
            levels.add(lvl);
        }
        
        this.shopItemIndex = 0;
        this.displayPowerUp(shopItemIndex);
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
            if (music != null){
                music.setVolume(user.getInt(2));
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
                this.clearPowerUp (shopItemIndex);
                this.shopItemIndex = this.shopItemIndex - 1;
                this.displayPowerUp(shopItemIndex);
            }
        }
        if (Greenfoot.mouseClicked(forwardItemButton)){
            this.clearPowerUp (shopItemIndex);
            this.shopItemIndex = this.shopItemIndex + 1;
            this.displayPowerUp(shopItemIndex);           
        } 
        /**
         * Type 1 = Elixir Speed
         * Type 2 = Castle Health
         * Type 3 = Knight Health
         * Type 4 = Knight Attack
         */
        for (int i = 0; i < buybuttons.size(); i++) {
            if (Greenfoot.mouseClicked(buybuttons.get(i))) {
                // if someone clicked ith button, then ith powerup will be purchased.
                ShopItem itm = powerups.get(i);
                this.gemsCount -= itm.getCost();
                updateGemsCount();
                // Subtract gems from user info class
                if (UserInfo.isStorageAvailable()){
                    user = UserInfo.getMyInfo();
                    if (itm.getType() == 1){ // Elixir speed
                        user.setInt(4, user.getInt(4) + 1);
                    }
                    else if (itm.getType() == 2){ // Castle health 
                        user.setInt(5, user.getInt(5) + 1);
                    }
                    else if (itm.getType() == 3){ // Knight health
                        String[] parsed = user.getString(0).split(" ");
                        parsed[1] = Integer.toString(Integer.valueOf(parsed[1]));
                        String s = String.join(" ", parsed);
                        user.setString(0, s);
                    }
                    else if (itm.getType() == 4){ // Knight attack
                        String[] parsed = user.getString(0).split(" ");
                        parsed[0] = Integer.toString(Integer.valueOf(parsed[0]));
                        String s = String.join(" ", parsed);
                        user.setString(0, s);
                    }
                    // Subtract gems from user
                    user.setInt(0, this.gemsCount);
                    user.store();
                }
            }
        }
    }
}

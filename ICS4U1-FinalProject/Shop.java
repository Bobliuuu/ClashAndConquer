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
    private final int[] powerupMultiplier = {}; // Feature
    
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
        
        background = new GreenfootImage("Worlds/ShopBackground.jpg");
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
        // All powerup costs are for testing only
        if (UserInfo.isStorageAvailable()){
            user = UserInfo.getMyInfo();
            String[] parsed0 = user.getString(0).split(" ");
            String[] parsed1 = user.getString(1).split(" ");
            String[] parsed2 = user.getString(2).split(" ");
            this.powerups.add(new ShopItem(100, "CastleHealth", user.getInt(5) + 1)); 
            this.powerups.add(new ShopItem(100, "ElixirBarSpeed", user.getInt(4) + 1)); 
            this.powerups.add(new ShopItem(100, "KnightHealth", Integer.valueOf(parsed0[0])));
            this.powerups.add(new ShopItem(400, "KnightAttack", Integer.valueOf(parsed0[1])));
            this.powerups.add(new ShopItem(300, "ArcherHealth", Integer.valueOf(parsed0[2])));
            this.powerups.add(new ShopItem(200, "ArcherAttack", Integer.valueOf(parsed0[3])));
            this.powerups.add(new ShopItem(300, "GiantHealth", Integer.valueOf(parsed0[4])));
            this.powerups.add(new ShopItem(300, "GiantAttack", Integer.valueOf(parsed0[5])));
            this.powerups.add(new ShopItem(300, "SkeletonHealth", Integer.valueOf(parsed0[6])));
            this.powerups.add(new ShopItem(300, "SkeletonAttack", Integer.valueOf(parsed0[7])));
            this.powerups.add(new ShopItem(300, "ElixirHealth", Integer.valueOf(parsed1[0])));
            this.powerups.add(new ShopItem(200, "ElixirSpeed", Integer.valueOf(parsed1[1])));
            this.powerups.add(new ShopItem(400, "FireballAttack", Integer.valueOf(parsed1[2])));
            this.powerups.add(new ShopItem(400, "FireballRadius", Integer.valueOf(parsed1[3])));
            this.powerups.add(new ShopItem(300, "PoisonAttack", Integer.valueOf(parsed2[0])));
            this.powerups.add(new ShopItem(300, "PoisonDuration", Integer.valueOf(parsed2[1])));
            this.powerups.add(new ShopItem(400, "TombstoneCooldown", Integer.valueOf(parsed2[2])));
            this.powerups.add(new ShopItem(400, "TombstoneHealth", Integer.valueOf(parsed2[3])));
        }
        else {
            this.powerups.add(new ShopItem(100, "CastleHealth", 1)); 
            this.powerups.add(new ShopItem(100, "ElixirBarSpeed", 1)); 
            this.powerups.add(new ShopItem(100, "KnightHealth", 1));
            this.powerups.add(new ShopItem(400, "KnightAttack", 1));
            this.powerups.add(new ShopItem(300, "ArcherHealth", 1));
            this.powerups.add(new ShopItem(200, "ArcherAttack", 1));
            this.powerups.add(new ShopItem(300, "GiantHealth", 1));
            this.powerups.add(new ShopItem(300, "GiantAttack", 1));
            this.powerups.add(new ShopItem(300, "SkeletonHealth", 1));
            this.powerups.add(new ShopItem(300, "SkeletonAttack", 1));
            this.powerups.add(new ShopItem(300, "ElixirHealth", 1));
            this.powerups.add(new ShopItem(200, "ElixirSpeed", 1));
            this.powerups.add(new ShopItem(400, "FireballAttack", 1));
            this.powerups.add(new ShopItem(400, "FireballRadius", 1));
            this.powerups.add(new ShopItem(300, "PoisonAttack", 1));
            this.powerups.add(new ShopItem(300, "PoisonDuration", 1));
            this.powerups.add(new ShopItem(400, "TombstoneCooldown", 1));
            this.powerups.add(new ShopItem(400, "TombstoneHealth", 1));
        }
        
        for (ShopItem powerup : powerups){
            powerup.setCost(powerup.getLevel() * 40);
            
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
        for (int i = 0; i < buybuttons.size(); i++) {
            if (Greenfoot.mouseClicked(buybuttons.get(i))) {
                // if someone clicked ith button, then ith powerup will be purchased.
                ShopItem itm = powerups.get(i); 
                
                this.gemsCount -= itm.getCost();
                updateGemsCount();
                
                itm.setLevel(itm.getLevel() + 1); // Update powerup level
                this.clearPowerUpAt(i);
                
                // Subtract gems from user info class
                if (UserInfo.isStorageAvailable()){
                    user = UserInfo.getMyInfo();
                    if (itm.getType().equals("ElixirBarSpeed")){ 
                        user.setInt(4, user.getInt(4) + 1);
                    }
                    else if (itm.getType().equals("CastleHealth")){ 
                        user.setInt(5, user.getInt(5) + 1);
                    }
                    else if (itm.getType().equals("KnightHealth")){
                        String[] parsed = user.getString(0).split(" ");
                        parsed[0] = Integer.toString(Integer.valueOf(parsed[0]));
                        String s = String.join(" ", parsed);
                        user.setString(0, s);
                    }
                    else if (itm.getType().equals("KnightAttack")){
                        String[] parsed = user.getString(0).split(" ");
                        parsed[1] = Integer.toString(Integer.valueOf(parsed[1]));
                        String s = String.join(" ", parsed);
                        user.setString(0, s);
                    }
                    else if (itm.getType().equals("ArcherHealth")){
                        String[] parsed = user.getString(0).split(" ");
                        parsed[2] = Integer.toString(Integer.valueOf(parsed[2]));
                        String s = String.join(" ", parsed);
                        user.setString(0, s);
                    }
                    else if (itm.getType().equals("ArcherAttack")){
                        String[] parsed = user.getString(0).split(" ");
                        parsed[3] = Integer.toString(Integer.valueOf(parsed[3]));
                        String s = String.join(" ", parsed);
                        user.setString(0, s);
                    }
                    else if (itm.getType().equals("GiantHealth")){
                        String[] parsed = user.getString(0).split(" ");
                        parsed[4] = Integer.toString(Integer.valueOf(parsed[4]));
                        String s = String.join(" ", parsed);
                        user.setString(0, s);
                    }
                    else if (itm.getType().equals("GiantAttack")){
                        String[] parsed = user.getString(0).split(" ");
                        parsed[5] = Integer.toString(Integer.valueOf(parsed[5]));
                        String s = String.join(" ", parsed);
                        user.setString(0, s);
                    }
                    else if (itm.getType().equals("SkeletonHealth")){
                        String[] parsed = user.getString(0).split(" ");
                        parsed[6] = Integer.toString(Integer.valueOf(parsed[6]));
                        String s = String.join(" ", parsed);
                        user.setString(0, s);
                    }
                    else if (itm.getType().equals("SkeletonAttack")){
                        String[] parsed = user.getString(0).split(" ");
                        parsed[7] = Integer.toString(Integer.valueOf(parsed[7]));
                        String s = String.join(" ", parsed);
                        user.setString(0, s);
                    }
                    else if (itm.getType().equals("ElixirHealth")){
                        String[] parsed = user.getString(1).split(" ");
                        parsed[0] = Integer.toString(Integer.valueOf(parsed[0]));
                        String s = String.join(" ", parsed);
                        user.setString(1, s);
                    }
                    else if (itm.getType().equals("ElixirAttack")){
                        String[] parsed = user.getString(1).split(" ");
                        parsed[1] = Integer.toString(Integer.valueOf(parsed[1]));
                        String s = String.join(" ", parsed);
                        user.setString(1, s);
                    }
                    else if (itm.getType().equals("TombstoneHealth")){
                        String[] parsed = user.getString(1).split(" ");
                        parsed[2] = Integer.toString(Integer.valueOf(parsed[2]));
                        String s = String.join(" ", parsed);
                        user.setString(1, s);
                    }
                    else if (itm.getType().equals("SkeletonCooldown")){
                        String[] parsed = user.getString(1).split(" ");
                        parsed[3] = Integer.toString(Integer.valueOf(parsed[3]));
                        String s = String.join(" ", parsed);
                        user.setString(1, s);
                    }
                    else if (itm.getType().equals("FireballSpeed")){
                        String[] parsed = user.getString(2).split(" ");
                        parsed[0] = Integer.toString(Integer.valueOf(parsed[0]));
                        String s = String.join(" ", parsed);
                        user.setString(2, s);
                    }
                    else if (itm.getType().equals("FireballAttack")){
                        String[] parsed = user.getString(2).split(" ");
                        parsed[1] = Integer.toString(Integer.valueOf(parsed[1]));
                        String s = String.join(" ", parsed);
                        user.setString(2, s);
                    }
                    else if (itm.getType().equals("PoisonRadius")){
                        String[] parsed = user.getString(2).split(" ");
                        parsed[2] = Integer.toString(Integer.valueOf(parsed[2]));
                        String s = String.join(" ", parsed);
                        user.setString(2, s);
                    }
                    else if (itm.getType().equals("PoisonAttack")){
                        String[] parsed = user.getString(2).split(" ");
                        parsed[3] = Integer.toString(Integer.valueOf(parsed[3]));
                        String s = String.join(" ", parsed);
                        user.setString(2, s);
                    }
                    // Subtract gems from user
                    user.setInt(0, this.gemsCount);
                    user.store();
                }
            }
        }
    }
    
    public void clearPowerUpAt(int idx){
        int i = 0;
        for (ShopItem powerup : powerups){
            if (i == idx){ 
                TextButton btn = buybuttons.get(idx);
                SuperTextBox lvl = levels.get(idx);
                removeObject(btn);
                removeObject(lvl);
                buybuttons.remove(idx);
                levels.remove(idx);
                
                powerup.setCost(powerup.getLevel() * 40);
            
                TextButton btnNew = new TextButton("Buy for " + String.valueOf(powerup.getCost()) + " Gems", 7, 170, true, Color.DARK_GRAY, Color.WHITE, orange, Color.WHITE, transparent, new Font ("Open Sans",true ,false ,15));
                buybuttons.add(idx, btnNew);
                
                Font font2 = new Font("Verdana", true, false, 20);
                SuperTextBox lvlNew = new SuperTextBox("Level " + String.valueOf(powerup.getLevel()), transparent, Color.BLACK, font2, false, 18*6, 0, transparent);
                levels.add(idx, lvlNew);
                
                addObject (btnNew, 150+(300*(idx - shopItemIndex)), 480);
        
                addObject (lvlNew, 150+(300*(idx - shopItemIndex)), 380);
                
                return;
            }
            i++;
        }
    }
}
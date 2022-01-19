import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level here.
 * 
 * Coordinates: 
 * Bridges: (127, 385), (673, 385), (398, 385)
 * End of road: (397, 170):
 * Road turns: (128, 590), (674, 590) [my side], (128, 170), (672, 170)
 * 
 * @author Jerry Zhu
 * @version January 2022
 */
public class Level extends World
{
    // Hardcoded variables
    private final int[][] cardCoordinates = {{50, 760}, {150, 760}, {650, 760}, {753, 760}};
    
    // Instance variables
    private Image levelMap;
    private Image elixir;
    private Image unplacedTroop;
    private Image defeatScreen;
    private Image victoryScreen;
    private Castle myCastle;
    private Castle enemyCastle;
    private ElixirBar elixirBar;
    private RedZone redZone;
    private CardDeck cardDeck;
    private EnemyAI enemyAI;
    private boolean troopIsSelected, isWeak = false;
    private String troopSelected;
    private int levelValue;
    private int victoryCountdown;
    private int defeatCountdown;
    public static boolean removed = false;
    
    /**
     * Level world constructor
     * 
     * @param levelValue     The desired level to be initialized. 
     */
    public Level(int levelValue)
    {   
        // Create a new world with 800 x 836 cells with a cell size of 1x1 pixels.
        super(800, 836, 1); 
        
        this.levelValue = levelValue;
        
        levelMap = new Image(new GreenfootImage("background.png"));
        addObject(levelMap, getWidth()/2, getHeight()/2);
        
        myCastle = new Castle(false);
        addObject(myCastle, 400, 680);
        
        enemyCastle = new Castle(true, 130, 130, 150);
        enemyCastle.setHealth(250 + (100*(levelValue-1) + Greenfoot.getRandomNumber(50)));
        addObject(enemyCastle, 400, 100);
        
        elixir = new Image(new GreenfootImage("elixirbar.png"));
        addObject(elixir, 400, 770);

        elixirBar = new ElixirBar();
        addObject(elixirBar, 400, 770);
        
        redZone = new RedZone();
        addObject(redZone, 400, 190);
        
        String[] cardNames = {"Blank", "Knight", "Knight", "Blank"};
        cardDeck = new CardDeck(cardNames);
        for (int i = 0; i < 4; i++){
            addObject(cardDeck.getCardAtIndex(i), cardCoordinates[i][0], cardCoordinates[i][1]);
        }
        
        enemyAI = new EnemyAI(levelValue, isWeak);
        addObject(enemyAI, 0, 0);
        
        troopIsSelected = false;
        troopSelected = "none";
        unplacedTroop = new Image();
    }
    
    public void act(){
        if (winOrLose()){
            return;
        }
        //checkMousePosition();
        checkMouseClick();
        moveUnplacedTroop();
        elixirBar.addElixir();
        //myCastle.removeCastle();
    }
    
    public void checkMousePosition(){
        if (Greenfoot.mouseClicked(levelMap)){
            System.out.println(Greenfoot.getMouseInfo().getX() + " " + Greenfoot.getMouseInfo().getY());
        }
    }
    
    public void checkMouseClick(){
        if (Greenfoot.mouseClicked(unplacedTroop) && Greenfoot.getMouseInfo() != null){
            if (unplacedTroop.intersectsCard()){
                setTroopSelected(unplacedTroop.getCardName());
            }
            if (Greenfoot.getMouseInfo().getX() >= 70 && Greenfoot.getMouseInfo().getX() <= 720 && 
                Greenfoot.getMouseInfo().getY() >= 380 && Greenfoot.getMouseInfo().getY() <= 620){
                if (elixirBar.useElixir(3)){
                    if (troopSelected == "Knight"){
                        removeObject(unplacedTroop);
                        unplacedTroop = new Image();
                        Knight placedTroop = new Knight(100, 10, 1, 3, 80, false);
                        addObject(placedTroop, Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
                        redZone.setToNone();
                        setTroopSelected("Blank");
                    }
                }
            }
        }
    }
    
    public void moveUnplacedTroop(){
        if (!unplacedTroop.getEmpty()){
            if (Greenfoot.getMouseInfo() != null){
                unplacedTroop.setLocation(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
            }
        }
    }
    
    public void setTroopSelected(String cardName){
        if (cardName == "Blank"){
            troopIsSelected = false;
            troopSelected = "Blank";
            return;
        }
        troopIsSelected = true;
        troopSelected = cardName;
        unplacedTroop = new Image(new GreenfootImage("Troops/Knight/" + cardName + "Move0.png"));
        unplacedTroop.setTransparency(100);
        addObject(unplacedTroop, Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
    }
    
    public void changeRedZone(boolean mouseClicked){
        if (mouseClicked){
            redZone.setToRedZone();
        }
        else {
            redZone.setToNone();
        }
    }
    
    public boolean winOrLose(){
        if (defeatCountdown != 0){
            if (defeatCountdown == 1){
                LevelMenu levelMenu = new LevelMenu();
                Greenfoot.setWorld(levelMenu);
            }
            defeatScreen = new Image(new GreenfootImage("defeat.png"));
            defeatScreen.getImage().scale(800, 200);
            addObject(defeatScreen, 400, 300);
            defeatCountdown--;
            return true;
        }
        else if (victoryCountdown != 0){
            if (victoryCountdown == 1){
                LevelMenu levelMenu = new LevelMenu();
                Greenfoot.setWorld(levelMenu); 
            }
            victoryScreen = new Image(new GreenfootImage("victory.jpg"));
            victoryScreen.getImage().scale(800, 200);
            addObject(victoryScreen, 400, 300);
            victoryCountdown--;
            return true;
        }
        return false;
    }
    
    public void setVictoryCountdown(int value){
        victoryCountdown = value;
    }
    
    public void setDefeatCountdown(int value){
        defeatCountdown = value;
    }
    
    public void setIfWeak(boolean weak){
        this.isWeak = weak;
    }
    
    public Castle getMyCastle(){
        return myCastle;
    }
    
    public Castle getEnemyCastle(){
        return enemyCastle;
    }
}

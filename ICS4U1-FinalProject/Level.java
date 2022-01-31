import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Level Class
 * <p>
 * The main level in which each game takes place. Spawns the enemies using the Enemy AI, the level, and castles. 
 * Implements a timer to keep track of level time, and a 2D array of coordinates. 
 * 
 * Important Coordinates: 
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
    private int cardIndex;
    private boolean isVictory;
    private boolean isDefeat;
    public static boolean removed = false;
    private GreenfootSound music;
    private UserInfo user;
    private Timer timer;
    public static boolean finished;
    
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
        
        levelMap = new Image(new GreenfootImage("Worlds/background.png"));
        addObject(levelMap, getWidth()/2, getHeight()/2);
        
        if (UserInfo.isStorageAvailable()){
            user = UserInfo.getMyInfo();
            myCastle = new Castle(false, 150, 150, 200, 200 + user.getInt(5) * 40);
            myCastle.getImage().scale(80, 100);
            addObject(myCastle, 400, 680);
        }
        else {
            myCastle = new Castle(false, 150, 150, 200, 200);
            myCastle.getImage().scale(80, 100);
            addObject(myCastle, 400, 680);
        }
        
        enemyCastle = new Castle(true, 130, 130, 200, 180 + (30*(levelValue-1) + Greenfoot.getRandomNumber(50)));
        addObject(enemyCastle, 400, 100);
        
        elixir = new Image(new GreenfootImage("elixirbar.png"));
        addObject(elixir, 400, 770);

        elixirBar = new ElixirBar();
        addObject(elixirBar, 400, 770);
        
        redZone = new RedZone();
        addObject(redZone, 400, 190);
        
        String[] cardNames = {"Knight", "Archer", "Giant", "Skeleton"};
        cardDeck = new CardDeck(cardNames);
        displayCards();
        
        enemyAI = new EnemyAI(levelValue, true);
        addObject(enemyAI, 0, 0);
        
        troopIsSelected = false;
        troopSelected = "none";
        unplacedTroop = new Image();
        finished = false;
        
        timer = new Timer();
        timer.start();
    }
    
    /**
     * A method to display all the cards in the game. 
     */
    public void displayCards(){
        for (int i = 0; i < 4; i++){
            addObject(cardDeck.getCardAtIndex(i), cardCoordinates[i][0], cardCoordinates[i][1]);
        }
    }
    
    /**
     * Act method to be run every act. 
     */
    public void act(){
        //checkMousePosition();
        checkMouseClick();
        moveUnplacedTroop();
        elixirBar.addElixir();
        winOrLose();
    }
    
    /**
     * When World is created, start the music and set the volume. 
     */
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
                music.playLoop();
            }
            if (music != null){
                music.setVolume(user.getInt(2));
            }
        }
    }
    
    /**
     * Debug method to check the mouse position of the user on click. 
     */
    public void checkMousePosition(){
        if (Greenfoot.mouseClicked(levelMap)){
            System.out.println(Greenfoot.getMouseInfo().getX() + " " + Greenfoot.getMouseInfo().getY());
        }
    }
    
    /**
     * Check if any buttons are clicked, and load the corresponding object.
     * This method is used to spawn troops, towers, and spells. 
     */
    public void checkMouseClick(){
        if (Greenfoot.mouseClicked(unplacedTroop) && Greenfoot.getMouseInfo() != null){
            if (unplacedTroop.intersectsCard()){
                String troopName = unplacedTroop.getCardName();
                removeObject(unplacedTroop);
                setTroopSelected(troopName);
                cardIndex = cardDeck.getCardIndex(troopName);
            }
            if (!unplacedTroop.getEmpty() && !unplacedTroop.checkTouchingCastle()){
                if (Greenfoot.getMouseInfo().getX() >= 70 && Greenfoot.getMouseInfo().getX() <= 720 && 
                Greenfoot.getMouseInfo().getY() >= 380 && Greenfoot.getMouseInfo().getY() <= 700){
                    if (elixirBar.hasElixir(3) && troopSelected.equals("Knight")){
                        elixirBar.useElixir(3);
                        removeObject(unplacedTroop);
                        unplacedTroop = new Image();
                        Knight placedTroop;
                        if (UserInfo.isStorageAvailable()){
                            String[] parsed = user.getString(0).split(" ");
                            placedTroop = new Knight(110 + 14 * Integer.valueOf(parsed[0]), 15 + 14 * Integer.valueOf(parsed[1]), 1, 3, false);
                        }
                        else {
                            placedTroop = new Knight(110, 15, 1, 3, false);
                        }
                        addObject(placedTroop, Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
                        redZone.setToNone();
                        setTroopSelected("Blank");
                        removeObject(cardDeck.getCardAtIndex(cardIndex));
                        cardDeck.switchCard(cardIndex);
                        addObject(cardDeck.getCardAtIndex(cardIndex), cardCoordinates[cardIndex][0], cardCoordinates[cardIndex][1]);
                    }
                    else if (elixirBar.hasElixir(3) && troopSelected.equals("Archer")){
                        elixirBar.useElixir(3);
                        removeObject(unplacedTroop);
                        unplacedTroop = new Image();
                        Archer placedTroop;
                        if (UserInfo.isStorageAvailable()){
                            String[] parsed = user.getString(0).split(" ");
                            placedTroop = new Archer(80 + 14 * Integer.valueOf(parsed[2]), 10 + 14 * Integer.valueOf(parsed[3]), 1, 6, false);
                        }
                        else {
                            placedTroop = new Archer(80, 10, 1, 6, false);
                        }
                        addObject(placedTroop, Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
                        redZone.setToNone();
                        setTroopSelected("Blank");
                        removeObject(cardDeck.getCardAtIndex(cardIndex));
                        cardDeck.switchCard(cardIndex);
                        addObject(cardDeck.getCardAtIndex(cardIndex), cardCoordinates[cardIndex][0], cardCoordinates[cardIndex][1]);
                    }
                    else if (elixirBar.hasElixir(5) && troopSelected.equals("Giant")){
                        elixirBar.useElixir(5);
                        removeObject(unplacedTroop);
                        unplacedTroop = new Image();
                        Giant placedTroop;
                        if (UserInfo.isStorageAvailable()){
                            String[] parsed = user.getString(0).split(" ");
                            placedTroop = new Giant(250 + 14 * Integer.valueOf(parsed[4]), 20 + 14 * Integer.valueOf(parsed[5]), 1, 6, false);
                        }
                        else {
                            placedTroop = new Giant(250, 20, 1, 6, false);
                        }
                        addObject(placedTroop, Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
                        redZone.setToNone();
                        setTroopSelected("Blank");
                        removeObject(cardDeck.getCardAtIndex(cardIndex));
                        cardDeck.switchCard(cardIndex);
                        addObject(cardDeck.getCardAtIndex(cardIndex), cardCoordinates[cardIndex][0], cardCoordinates[cardIndex][1]);
                    }
                    else if (elixirBar.hasElixir(5) && troopSelected.equals("Skeleton")){
                        elixirBar.useElixir(5);
                        removeObject(unplacedTroop);
                        unplacedTroop = new Image();
                        Skeleton placedTroop;
                        Skeleton placedTroop2;
                        Skeleton placedTroop3;
                        Skeleton placedTroop4;
                        Skeleton placedTroop5;
                        Skeleton placedTroop6;
                        if (UserInfo.isStorageAvailable()){
                            String[] parsed = user.getString(0).split(" ");
                            placedTroop = new Skeleton(25 + 14 * Integer.valueOf(parsed[6]), 8 + 14 * Integer.valueOf(parsed[7]), 1, 6, false);
                            placedTroop2 = new Skeleton(25 + 14 * Integer.valueOf(parsed[6]), 8 + 14 * Integer.valueOf(parsed[7]), 1, 6, false);
                            placedTroop3 = new Skeleton(25 + 14 * Integer.valueOf(parsed[6]), 8 + 14 * Integer.valueOf(parsed[7]), 1, 6, false);
                            placedTroop4 = new Skeleton(25 + 14 * Integer.valueOf(parsed[6]), 8 + 14 * Integer.valueOf(parsed[7]), 1, 6, false);
                            placedTroop5 = new Skeleton(25 + 14 * Integer.valueOf(parsed[6]), 8 + 14 * Integer.valueOf(parsed[7]), 1, 6, false);
                            placedTroop6 = new Skeleton(25 + 14 * Integer.valueOf(parsed[6]), 8 + 14 * Integer.valueOf(parsed[7]), 1, 6, false);
                        }
                        else {
                            placedTroop = new Skeleton(25, 8, 1, 6, false);
                            placedTroop2 = new Skeleton(25, 8, 1, 6, false);
                            placedTroop3 = new Skeleton(25, 8, 1, 6, false);
                            placedTroop4 = new Skeleton(25, 8, 1, 6, false);
                            placedTroop5 = new Skeleton(25, 8, 1, 6, false);
                            placedTroop6 = new Skeleton(25, 8, 1, 6, false);
                        }
                        addObject(placedTroop, Greenfoot.getMouseInfo().getX()+20, Greenfoot.getMouseInfo().getY());
                        addObject(placedTroop2, Greenfoot.getMouseInfo().getX()-20, Greenfoot.getMouseInfo().getY());
                        addObject(placedTroop3, Greenfoot.getMouseInfo().getX()+20, Greenfoot.getMouseInfo().getY()+20);
                        addObject(placedTroop4, Greenfoot.getMouseInfo().getX()+20, Greenfoot.getMouseInfo().getY()-20);
                        addObject(placedTroop5, Greenfoot.getMouseInfo().getX()-20, Greenfoot.getMouseInfo().getY()+20);
                        addObject(placedTroop6, Greenfoot.getMouseInfo().getX()-20, Greenfoot.getMouseInfo().getY()-20);
                        redZone.setToNone();
                        setTroopSelected("Blank");
                        removeObject(cardDeck.getCardAtIndex(cardIndex));
                        cardDeck.switchCard(cardIndex);
                        addObject(cardDeck.getCardAtIndex(cardIndex), cardCoordinates[cardIndex][0], cardCoordinates[cardIndex][1]);
                    }
                }
                // Tower region
                if (Greenfoot.getMouseInfo().getX() >= 70 && Greenfoot.getMouseInfo().getX() <= 720 && 
                Greenfoot.getMouseInfo().getY() >= 380 && Greenfoot.getMouseInfo().getY() <= 680){
                    if (elixirBar.hasElixir(6) && troopSelected.equals("Elixirtower")){
                        elixirBar.useElixir(6);
                        removeObject(unplacedTroop);
                        unplacedTroop = new Image();
                        ElixirTower placedTroop;
                        if (UserInfo.isStorageAvailable()){
                            String[] parsed = user.getString(1).split(" ");
                            placedTroop = new ElixirTower(0.004 + 0.002 * Integer.valueOf(parsed[1]), false, 175 + Integer.valueOf(parsed[0]));
                        }
                        else {
                            placedTroop = new ElixirTower(0.004, false, 175);
                        }
                        addObject(placedTroop, Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
                        redZone.setToNone();
                        setTroopSelected("Blank");
                        removeObject(cardDeck.getCardAtIndex(cardIndex));
                        cardDeck.switchCard(cardIndex);
                        addObject(cardDeck.getCardAtIndex(cardIndex), cardCoordinates[cardIndex][0], cardCoordinates[cardIndex][1]);
                    }
                    else if (elixirBar.hasElixir(6) && troopSelected.equals("Tombstone")){
                        elixirBar.useElixir(6);
                        removeObject(unplacedTroop);
                        unplacedTroop = new Image();
                        Tombstone placedTroop;
                        if (UserInfo.isStorageAvailable()){
                            String[] parsed = user.getString(1).split(" ");
                            placedTroop = new Tombstone(200 + 14 * Integer.valueOf(parsed[2]), false, 150 + 14 * Integer.valueOf(parsed[3]));
                        }
                        else {
                            placedTroop = new Tombstone(200, false, 150);
                        }
                        addObject(placedTroop, Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
                        redZone.setToNone();
                        setTroopSelected("Blank");
                        removeObject(cardDeck.getCardAtIndex(cardIndex));
                        cardDeck.switchCard(cardIndex);
                        addObject(cardDeck.getCardAtIndex(cardIndex), cardCoordinates[cardIndex][0], cardCoordinates[cardIndex][1]);
                    }
                }
            }
            if (elixirBar.hasElixir(4) && troopSelected.equals("Fireball")){
                elixirBar.useElixir(4);
                removeObject(unplacedTroop);
                unplacedTroop = new Image();
                Fireball placedTroop;
                if (UserInfo.isStorageAvailable()){
                    String[] parsed = user.getString(2).split(" ");
                    placedTroop = new Fireball(80 + 14 * Integer.valueOf(parsed[0]), 6, 100 + 8 * Integer.valueOf(parsed[1]), false, Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
                }
                else {
                    placedTroop = new Fireball(80, 6, 100, false, Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
                }
                addObject(placedTroop, 400, 680);
                redZone.setToNone();
                setTroopSelected("Blank");
                removeObject(cardDeck.getCardAtIndex(cardIndex));
                cardDeck.switchCard(cardIndex);
                addObject(cardDeck.getCardAtIndex(cardIndex), cardCoordinates[cardIndex][0], cardCoordinates[cardIndex][1]);
            }
            else if (elixirBar.hasElixir(4) && troopSelected.equals("Poison")){
                elixirBar.useElixir(4);
                removeObject(unplacedTroop);
                unplacedTroop = new Image();
                Poison placedTroop;
                if (UserInfo.isStorageAvailable()){
                    String[] parsed = user.getString(2).split(" ");
                    placedTroop = new Poison(20 + 10 * Integer.valueOf(parsed[2]), Integer.valueOf(parsed[3]), 80, false);
                }
                else {
                    placedTroop = new Poison(25, 8, 0, false);
                }
                addObject(placedTroop, Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
                redZone.setToNone();
                setTroopSelected("Blank");
                removeObject(cardDeck.getCardAtIndex(cardIndex));
                cardDeck.switchCard(cardIndex);
                addObject(cardDeck.getCardAtIndex(cardIndex), cardCoordinates[cardIndex][0], cardCoordinates[cardIndex][1]);
            }
        }
    }
    
    /**
     * Set the index of the desired card in the level. 
     */
    public void setCardIndex(){
        String troopName = unplacedTroop.getCardName();
        cardIndex = cardDeck.getCardIndex(troopName);
    }
    
    /**
     * Move a "ghost" or unplaced troop to a different position as the mouse moves. 
     */
    public void moveUnplacedTroop(){
        if (!unplacedTroop.getEmpty()){
            if (Greenfoot.getMouseInfo() != null){
                unplacedTroop.setLocation(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
            }
        }
    }
    
    /**
     * Set the troop selected by the player when a card is clicked. 
     * 
     * @param cardName      The name of the troop card that was selected/clicked. 
     */
    public void setTroopSelected(String cardName){
        if (cardName == "Blank"){
            troopIsSelected = false;
            troopSelected = "Blank";
            return;
        }
        troopIsSelected = true;
        troopSelected = cardName;
        if (cardName == "Fireball"){
            unplacedTroop = new Image(new GreenfootImage("Spells/Fireball/FireballAttack0.png"));
        }
        else if (cardName == "Poison"){
            unplacedTroop = new Image(new GreenfootImage("Spells/Poison/PoisonAttack0.png"));
        }
        else if (cardName == "Elixirtower"){
            unplacedTroop = new Image(new GreenfootImage("elixircollectorlevel1.png"));
            unplacedTroop.getImage().scale(75, 75);
        }
        else if (cardName == "Tombstone"){
            unplacedTroop = new Image(new GreenfootImage("tombstone.png"));
            unplacedTroop.getImage().scale(50, 75);
        }
        else {
            unplacedTroop = new Image(new GreenfootImage("Troops/" + cardName + "/" + cardName + "Ghost.png"));
        }
        unplacedTroop.setTransparency(100);
        addObject(unplacedTroop, Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
    }
    
    /**
     * Change the redzone of the level to indicate where the object can be placed. 
     */
    public void changeRedZone(boolean mouseClicked){
        if (mouseClicked){
            redZone.setToRedZone();
        }
        else {
            redZone.setToNone();
        }
    }
    
    /**
     * Check if the player has won or lost, and load the level menu if necessary. 
     */
    public void winOrLose(){
        if (finished){
            try{
                Thread.sleep(2000);
            }
            catch (InterruptedException ie){
                ie.printStackTrace();
            }
            finished = false;
            LevelMenu levelMenu = new LevelMenu();
            levelMenu.started();
            Greenfoot.setWorld(levelMenu);
        }
        if (isDefeat){
            defeatScreen = new Image(new GreenfootImage("defeat.png"));
            defeatScreen.getImage().scale(800, 200);
            addObject(defeatScreen, 400, 300);
            finished = true;
            // Calculate gems with timer
            if (UserInfo.isStorageAvailable()){ 
                user = UserInfo.getMyInfo();
                timer.end();
                user.setInt(9, user.getInt(9) + timer.getTimeInSeconds());
                user.setInt(0, user.getInt(0) + 2 * timer.getTimeInSeconds());
                user.store();
            }
            user.setInt(8, user.getInt(8) + 1);
            user.store();
            isDefeat = false;
        }
        else if (isVictory){
            victoryScreen = new Image(new GreenfootImage("victory.png"));
            victoryScreen.getImage().scale(800, 200);
            addObject(victoryScreen, 400, 300);
            finished = true;
            if (UserInfo.isStorageAvailable()){ 
                user = UserInfo.getMyInfo();
                String levels = user.getString(3);
                String[] parsed = levels.split(" ");
                if (parsed[levelValue - 1].equals("0")){ // First time win
                    user.setInt(0, user.getInt(0) + 100 * levelValue);
                    parsed[levelValue - 1] = "1";
                    String s = String.join(" ", parsed);
                    user.setString(3, s);
                    user.store();
                }
                else {
                    user.setInt(0, user.getInt(0) + 25 * levelValue);
                    user.store();
                }
                user.setInt(7, user.getInt(7) + 1);
                if(user.getScore() == levelValue) user.setScore(user.getScore() + 1);
                user.store();
            }
            isVictory = false; 
        }
    }
    
    /**
     * Set the victory variable if the player has won. 
     */
    public void setVictory(){
        isVictory = true;
    }
    
    /**
     * Set the defeat variable if the player has lost.
     */
    public void setDefeat(){
        isDefeat = true;
    }
    
    /**
     * Set the enemyAI to toggle it's values and make it weaker. 
     * 
     * @param weak     Whether or not the enemyAI should be made weak. 
     */
    public void setIfWeak(boolean weak){
        this.isWeak = weak;
    }
    
    /**
     * Gets the player's castle object.
     */
    public Castle getMyCastle(){
        return myCastle;
    }
    
    /**
     * Gets the enemy's castle object. 
     */
    public Castle getEnemyCastle(){
        return enemyCastle;
    }
}

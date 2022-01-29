import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stats here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stats extends World
{
    private Image backButton;
    private SuperTextBox levelLabel;
    private SuperTextBox winLossLabel;
    private SuperTextBox gemsSpentLabel;
    private SuperTextBox timePlayedLabel;
    
    private SuperTextBox firstPlaceLabel;
    private SuperTextBox secondPlaceLabel;
    private SuperTextBox thirdPlaceLabel;
    private SuperTextBox fourthPlaceLabel;
    private SuperTextBox fifthPlaceLabel;
    
    private SuperTextBox firstPlaceLevelLabel;
    private SuperTextBox secondPlaceLevelLabel;
    private SuperTextBox thirdPlaceLevelLabel;
    private SuperTextBox fourthPlaceLevelLabel;
    private SuperTextBox fifthPlaceLevelLabel;
    
    private static final Color transparent = new Color(0, 0, 0, 0);
    /**
     * Constructor for objects of class Stats.
     * 
     */
    public Stats()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1);

        backButton = new Image(new GreenfootImage("Buttons/backbutton.png"));
        backButton.getImage().scale(80, 50);
        addObject(backButton, 41, 25);
        
        Font font = new Font ("Open Sans", true, false, 35);
        
        // Player Stats Labels
        levelLabel = new SuperTextBox("0", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(levelLabel, 392, 173);
        
        winLossLabel = new SuperTextBox("0" + " : " + "0", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(winLossLabel, 370, 268);
        
        gemsSpentLabel = new SuperTextBox("1000", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(gemsSpentLabel, 363, 374);
        
        timePlayedLabel = new SuperTextBox("00:00", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(timePlayedLabel, 359, 486);
        
        
        // Leaderboard
        
        firstPlaceLabel = new SuperTextBox("TestUsername123", transparent, Color.BLUE, font, false, 18*6, 0, transparent);
        addObject(firstPlaceLabel, 548, 139);
                
        firstPlaceLevelLabel = new SuperTextBox("Lvl " + "5", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(firstPlaceLevelLabel, 776, 139);
        
        secondPlaceLabel = new SuperTextBox("TestUsername123", transparent, Color.BLUE, font, false, 18*6, 0, transparent);
        addObject(secondPlaceLabel, 548, 234);
                
        secondPlaceLevelLabel = new SuperTextBox("Lvl " + "5", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(secondPlaceLevelLabel, 776, 234);

        thirdPlaceLabel = new SuperTextBox("TestUsername123", transparent, Color.BLUE, font, false, 18*6, 0, transparent);
        addObject(thirdPlaceLabel, 548, 335);
                
        thirdPlaceLevelLabel = new SuperTextBox("Lvl " + "5", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(thirdPlaceLevelLabel, 776, 335);
        
        fourthPlaceLabel = new SuperTextBox("TestUsername123", transparent, Color.BLUE, font, false, 18*6, 0, transparent);
        addObject(fourthPlaceLabel, 548, 444);
                
        fourthPlaceLevelLabel = new SuperTextBox("Lvl " + "5", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(fourthPlaceLevelLabel, 776, 444);
        
        fifthPlaceLabel = new SuperTextBox("TestUsername123", transparent, Color.BLUE, font, false, 18*6, 0, transparent);
        addObject(fifthPlaceLabel, 548, 537);
                
        fifthPlaceLevelLabel = new SuperTextBox("Lvl " + "5", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(fifthPlaceLevelLabel, 776, 537);

    }
    public void act(){
        checkClick();
    }
    public void checkClick(){
        if (Greenfoot.mouseClicked(backButton)){
            Start start = new Start();
            start.started();
            Greenfoot.setWorld(start);
        }
    }
}
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
    private GreenfootImage background;
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
        background = new GreenfootImage("MyStatsBackground.PNG");
        background.scale(getWidth(), getHeight());
        setBackground(background);
        
        backButton = new Image(new GreenfootImage("Buttons/backbutton.png"));
        backButton.getImage().scale(80, 50);
        addObject(backButton, 65, 45);
        
        Font font = new Font ("Open Sans", true, false, 35);
        
        // Player Stats Labels
        levelLabel = new SuperTextBox("0", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(levelLabel, 382, 153);
        
        winLossLabel = new SuperTextBox("0" + " : " + "0", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(winLossLabel, 363, 244);
        
        gemsSpentLabel = new SuperTextBox("1000", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(gemsSpentLabel, 355, 344);
        
        timePlayedLabel = new SuperTextBox("00:00", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(timePlayedLabel, 352, 450);
        
        
        // Leaderboard
        
        firstPlaceLabel = new SuperTextBox("TestUsername123", transparent, Color.BLUE, font, false, 18*6, 0, transparent);
        addObject(firstPlaceLabel, 578, 149);
                
        firstPlaceLevelLabel = new SuperTextBox("Lvl " + "5", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(firstPlaceLevelLabel, 796, 149);
        
        secondPlaceLabel = new SuperTextBox("TestUsername123", transparent, Color.BLUE, font, false, 18*6, 0, transparent);
        addObject(secondPlaceLabel, 578, 241);
                
        secondPlaceLevelLabel = new SuperTextBox("Lvl " + "5", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(secondPlaceLevelLabel, 796, 241);

        thirdPlaceLabel = new SuperTextBox("TestUsername123", transparent, Color.BLUE, font, false, 18*6, 0, transparent);
        addObject(thirdPlaceLabel, 578, 343);
                
        thirdPlaceLevelLabel = new SuperTextBox("Lvl " + "5", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(thirdPlaceLevelLabel, 796, 343);
        
        fourthPlaceLabel = new SuperTextBox("TestUsername123", transparent, Color.BLUE, font, false, 18*6, 0, transparent);
        addObject(fourthPlaceLabel, 578, 450);
                
        fourthPlaceLevelLabel = new SuperTextBox("Lvl " + "5", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(fourthPlaceLevelLabel, 796, 450);
        
        fifthPlaceLabel = new SuperTextBox("TestUsername123", transparent, Color.BLUE, font, false, 18*6, 0, transparent);
        addObject(fifthPlaceLabel, 578, 541);
                
        fifthPlaceLevelLabel = new SuperTextBox("Lvl " + "5", transparent, Color.BLACK, font, false, 18*6, 0, transparent);
        addObject(fifthPlaceLevelLabel, 796, 541);

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
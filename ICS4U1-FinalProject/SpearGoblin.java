import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpearGoblin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpearGoblin extends Troop
{
    private GreenfootImage[] walkAnimations, attackAnimations;
    private int walkCount, attackCount, cooldown;
    
    public SpearGoblin(int health, int attack, double movementSpeed, double attackSpeed, double radius, boolean isEnemy){
        super(health, attack, movementSpeed, attackSpeed, radius, isEnemy);
        walkCount = 0;
        attackCount = -1;
        cooldown = (int)(this.attackSpeed * 4);
        walkAnimations = new GreenfootImage[14];
        attackAnimations = new GreenfootImage[14];
        for(int i = 0; i < 14; i++){
            walkAnimations[i] = new GreenfootImage("Troops/Goblin/GoblinSpearMove" + i + ".png");
            attackAnimations[i] = new GreenfootImage("Troops/Goblin/GoblinSpearAttack" + i + ".png");
        }
    }

    public void attack()
    {
        if(this.target != null && cooldown == 0){
            getWorld().addObject(new Projectile(this.attack, 10, this.target), getX(), getY());
            super.attack();
            cooldown = (int)(this.attackSpeed * 4);
        }
        else{
            cooldown--;
        }
    }
    
    public void animate(){
        walkCount++;
        if(walkCount == 56) walkCount = 0;
        setImage(walkAnimations[walkCount/4]);
    }
    
    public int getAttackCounter(){
        return attackCount;
    }
    
    public void attackAnimate(){
        attackCount++;
        if(attackCount == 56) attackCount = 0;
        setImage(attackAnimations[attackCount/4]);
    }
}

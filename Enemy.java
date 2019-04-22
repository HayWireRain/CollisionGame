import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    public int animationCount1, animationCount2;
    public int movementSpeed;
    public GreenfootImage[] enemyImages;
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Enemy()
    {
        enemyImages=new GreenfootImage[4];
        for (int i=0; i<4; i++)
        {
            enemyImages[i]=new GreenfootImage("Enemy"+i+".png");
        }
        movementSpeed=Greenfoot.getRandomNumber(8)+5;
        animationCount1 = 0;
        animationCount2 = 0;
    }
    public void act() 
    {
        MyWorld wrld=(MyWorld) getWorld();
        animationCount1++;
        if (animationCount1 == 4)
        {
            animationCount1 = 0;
            animationCount2++;
            if(animationCount2==4)
            {
                animationCount2 = 0;
            }
            setImage(enemyImages[animationCount2]);
        }   
        setLocation(getX()-movementSpeed, getY());
        if(getX()<-getImage().getWidth())
        {
            wrld.removeObject(this);
        }
    }    
}

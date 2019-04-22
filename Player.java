import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    public GreenfootImage[] playerImages;
    public int animationCount1, animationCount2;
    public int animationSpeed;
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Player()
    {
        playerImages=new GreenfootImage[2];
        for (int i=0; i<2; i++)
        {
            playerImages[i]=new GreenfootImage("Player"+i+".png");
        }
        animationSpeed = 10;
        animationCount1 = 0;
        animationCount2 = 0;
    }
    public void act() 
    {
        MyWorld wrld=(MyWorld) getWorld();
        animationCount1++;
        if (animationCount1 == animationSpeed)
        {
            animationCount1 = 0;
            animationCount2++;
            if(animationCount2==2)
            {
                animationCount2 = 0;
            }
            setImage(playerImages[animationCount2]);
        }   
        if(wrld.gameState.equals("running"))
        {
            if(Greenfoot.isKeyDown("up") && getY()>30)
            {
                setLocation(getX(), getY()-6);
                setRotation(340);
            }
            else if(Greenfoot.isKeyDown("down") && getY()<1000)
            {
                setLocation(getX(), getY()+6);
                setRotation(30);
            }
            else
            {
                setRotation(0);
            }
            Actor a=getOneIntersectingObject(Enemy.class);
            if(a!=null)
            {
                wrld.gameState="finished";
            }
        }
        else if(wrld.gameState=="finished")
        {
            setRotation(90);
            if(getY()<wrld.getHeight()-0)
            {
                setLocation(getX(), getY()+5);
            }
        }    
    }
}
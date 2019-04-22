import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Background here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Background extends Actor
{
    /**
     * Act - do whatever the Background wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        MyWorld wrld=(MyWorld) getWorld();
        setLocation(getX()-1, getY());
        if(getX()==-wrld.getWidth()/2)
        {
            setLocation(wrld.getWidth() + wrld.getWidth()/2, wrld.getHeight()/2);
        }
        // Add your action code here.
    }    
}

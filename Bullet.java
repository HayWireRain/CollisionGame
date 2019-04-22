import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Actor
{
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(10);
        MyWorld wrld=(MyWorld) getWorld();
        Actor a=getOneIntersectingObject(Enemy.class);
        if (a!=null)
        {
            wrld.removeObject(a);
            wrld.removeObject(this);
            wrld.score++;
            wrld.scoreTxt.setText(wrld.score+"");
        }
    }    
}

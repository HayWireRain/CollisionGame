 import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Dimension;
import java.awt.Toolkit;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    GreenfootImage bckIMG1, bckIMG2, bckIMGSpace1, bckIMGSpace2;
    Background bck1, bck2, bckSpace1, bckSpace2, Player1, Player2;
    public String gameState;
    public boolean stage2, stage2Flash, skyArea;
    public Text startupMessageTxt, scoreLabelTxt, scoreTxt, instructionTxt, pressITxt;
    public Player player;
    public int spawnCount, spawnTime;
    public Flash flash;
    public GreenfootImage flashImage;
    public int fadeToWhiteCount;
    public boolean isFadingToWhite;
    public boolean isBecomingOpaque;
    public GreenfootSound deathSound, bgMusicSpace, bgMusicSky, shootSound;
    public boolean hasPlayed;
    public int fallingCount;
    public int score;
    public boolean canFire;
    public boolean hasTransitioned;
    public boolean isTransitioning;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        super((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-25,
            (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-125,1,false); 
        bckIMG1= new GreenfootImage("bckSky.png");
        bckIMG2= new GreenfootImage("bckSky2.png");
        bckIMGSpace1 = new GreenfootImage("bckSpace1.png");
        bckIMGSpace2 = new GreenfootImage("bckSpace2.png");
        bckIMG1.scale(getWidth(), getHeight());
        bckIMG2.scale(getWidth(), getHeight());
        bckIMGSpace1.scale(getWidth(), getHeight());
        bckIMGSpace2.scale(getWidth(), getHeight());
        bck1= new Background();
        bck1.setImage(bckIMG1);
        bck2= new Background();
        bck2.setImage(bckIMG2);
        addObject(bck1, getWidth()/2, getHeight()/2);
        addObject(bck2, getWidth()/2+getWidth(), getHeight()/2);
        player = new Player();
        addObject(player,77,435); 
        startupMessageTxt = new Text("Press ENTER to start!",Color.WHITE,55);
        gameState = "startUp";
        scoreLabelTxt = new Text("Score:",Color.WHITE,55);
        scoreTxt = new Text("0",Color.WHITE,55);
        pressITxt = new Text("Press [i] For Instructions",Color.ORANGE,55);
        instructionTxt = new Text("Up and Down arrow keys to move \n [z] to shoot \n Shoot down the UFOs, and avoid making contact with them.",Color.ORANGE,55);
        spawnCount = 0;
        fallingCount = 0;
        spawnTime = Greenfoot.getRandomNumber(50)+20;
        flash = new Flash();
        flashImage = (new GreenfootImage("flash.png"));
        flash.setImage(flashImage);
        flashImage.setTransparency(0);
        addObject(flash, getWidth()/2, getHeight()/2);
        setPaintOrder(Flash.class, Text.class, Bullet.class, Player.class, Enemy.class, Background.class);
        stage2 = false;
        stage2Flash = false;
        isFadingToWhite = false;
        isBecomingOpaque = false;
        hasPlayed = false;
        deathSound = new GreenfootSound("death.mp3");
        bgMusicSpace = new GreenfootSound("bg.mp3");
        bgMusicSky = new GreenfootSound("bgSky.mp3");
        shootSound = new GreenfootSound("shoot.mp3");
        score = 0;
        canFire = true;
        hasTransitioned = false;
        isTransitioning = true;
        skyArea = true;
        addObject(pressITxt, getWidth()/2, 800);
    }
    public void act()
    {
         if(gameState.equals("startUp"))
         {
                skyArea = true;
                isTransitioning = true;
                stage2 = false;
                stage2Flash = false;
                addObject(startupMessageTxt, getWidth()/2, 200);
                bgMusicSpace.stop();
                if(Greenfoot.isKeyDown("i"))
                {
                    addObject(instructionTxt, getWidth()/2, 450);
                    removeObject(pressITxt);
                }
                if(Greenfoot.isKeyDown("Enter"))
                {
                        gameState = ("running");
                        removeObject(startupMessageTxt);
                        removeObject(pressITxt);
                        removeObject(instructionTxt);
                        player.setLocation(77,435);
                        hasPlayed = false;
                        score = 0;
                        scoreTxt.setText("0");
                }
         }
         else if(gameState.equals("running"))
         {
                spawnCount++;
                addObject(scoreLabelTxt, getHeight()/14, 25);
                addObject(scoreTxt, getHeight()/6, 25);
                bck1.setImage(bckIMG1);
                bck2.setImage(bckIMG2);
                if(skyArea == true)
                {
                    bgMusicSky.play();
                    bgMusicSpace.stop();
                }
                if(spawnCount==spawnTime)
                {
                        spawnCount=0;
                        spawnTime = Greenfoot.getRandomNumber(20)+40;
                        addObject(new Enemy(), getWidth()+50, Greenfoot.getRandomNumber(getHeight()-50));
                 
                }
                if(Greenfoot.isKeyDown("z") && canFire)
                {
                    addObject(new Bullet(), player.getX()+55, player.getY());
                    shootSound.play();
                    canFire = false;
                }
                if(!Greenfoot.isKeyDown("z") && !canFire)
                {
                    canFire = true;
                    if(shootSound.isPlaying() && Greenfoot.isKeyDown("z"))
                    {
                        shootSound.stop();
                        shootSound.play();
                    }
                    else
                    {
                        shootSound.play();
                    }
                }
                if(isTransitioning && score == 15)
                {
                    stage2Flash = true;
                    hasTransitioned = true;
                }
                if(hasTransitioned == true)
                {
                    isTransitioning = false;
                    //hasTransitioned = false;
                }
                if(stage2 == false)
                {
                    bck1.setImage(bckIMG1);
                    bck2.setImage(bckIMG2);
                }
                if(stage2Flash == true)
                {
                    isFadingToWhite = true;
                }
                if(isFadingToWhite == true)
                {
                    if(flashImage.getTransparency()<245)
                    {
                        flashImage.setTransparency(flashImage.getTransparency()+10);
                        flash.setImage(flashImage);
                    }
                    else
                    {
                        flashImage.setTransparency(255);
                        flash.setImage(flashImage);
                        isBecomingOpaque = true;
                        stage2 = true; 
                    }
                }
                if(isBecomingOpaque == true)
                {
                        isFadingToWhite=false;
                        stage2Flash = false;
                        if(flashImage.getTransparency()>10)
                        {
                 
                                    flashImage.setTransparency(flashImage.getTransparency()-10);
                                    flash.setImage(flashImage);
                
                        }
                        else
                        {
                                    flashImage.setTransparency(0);
                                    flash.setImage(flashImage);
                                    isBecomingOpaque = false;
                        }
                }
                if(stage2 == true)
                {
                    bck1.setImage(bckIMGSpace1);
                    bck2.setImage(bckIMGSpace2);
                    skyArea = false;
                    bgMusicSpace.play();
                    bgMusicSky.stop();
                    if(spawnCount==spawnTime)
                    {
                        spawnCount=0;
                        spawnTime = Greenfoot.getRandomNumber(20)+0;
                        addObject(new Enemy(), getWidth()+50, Greenfoot.getRandomNumber(getHeight()-50));
                    }
                }
        }
        else if(gameState.equals("finished"))
        {
            player.setImage("playerDead.png");
            fallingCount++;
            bgMusicSpace.stop();
            if(hasPlayed == false)
            {
                deathSound.play();
                hasPlayed = true;
            }
            if(fallingCount == 450)
            {
                gameState = ("startUp");
                fallingCount = 0;
            }
        }
    }
}
  

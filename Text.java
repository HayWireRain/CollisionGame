import greenfoot.*;

public class Text extends Actor
{
	public Color color;
	public int size;
	public String text;
	
	
    public Text()  
    {  
        this("", Color.WHITE, 24);
		color=Color.WHITE;
		text="";
		size=24;
    }  
	
	public Text(String t)
	{
		this(t, Color.WHITE, 24);
		color=Color.WHITE;
		size=24;
		text=t;
	}
	 
    public Text(String text, Color color, int size)  
    {          
		setText(text, color, size); 
		this.color=color;
		this.size=size;
    }  
 
    public void setText(String text, Color color, int size)  
    {  
        setImage(new GreenfootImage(text, size, color, new Color(0, 0, 0, 0)));  
    }   
	
	public void setText(String text)
	{
		setImage(new GreenfootImage(text, size, color, new Color(0, 0, 0, 0)));  
		this.text=text;
	}
 
     
}    

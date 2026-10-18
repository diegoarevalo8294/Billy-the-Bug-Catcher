package item;

import java.awt.Color;

import main.GamePanel;
import sprites.Range;
import sprites.Sprite;

public class iSpit extends Range{

	GamePanel gp;
	
	public iSpit(GamePanel gp) 
	{
		super(gp);
		this.gp = gp;
		
		name = "Spit";
		speed = 8;
		maxLife = 60;
		life = maxLife;
		attack = 2;
		useCost = 1;
		alive = false;
		getImage();
	}
	
	public void getImage()
	{
		front1 = setup("/proj/spitFront1");
		front2 = setup("/proj/spitFront2");
		back1 = setup("/proj/spitBack1");
		back2 = setup("/proj/spitBack2");
		left1 = setup("/proj/spitLeft1");
		left2 = setup("/proj/spitLeft2");
		right1 = setup("/proj/spitRight1");
		right2 = setup("/proj/spitRight2");
		
	}
	
	public boolean haveRes(Sprite caster)
	{
		boolean haveRes = false;
		if(caster.saliva >= useCost)
		{
			haveRes = true;
		}
		return haveRes;
	}
	
	public void takeRes(Sprite caster)
	{
		caster.saliva -= useCost;
	}
	
	public Color getParticleColor()
	{
		Color color = new Color(30, 144, 255);
		return color;
	}
	
	public int getParticleSize()
	{
		int size = 12;
		return size;
	}
	
	public int getParticleSpeed()
	{
		int speed = 1;
		return speed;
	}
	
	public int getParticleMaxLife()
	{
		int maxLife = 20;
		return maxLife;
	}
}
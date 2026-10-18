package tileInt;

import java.awt.Color;

import main.GamePanel;
import sprites.Sprite;

public class Int_Tree extends Interactive{

	GamePanel gp;
	
	public Int_Tree(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		front1 = setup("/tiles/grass n tree");
		destructible = true;
		life = 3;
	}
	
	
	public boolean isRightItem(Sprite sprite)
	{
		boolean isRightItem = false;
		if(sprite.currentWeapon.type == 4)
		{
			isRightItem = true;
		}
		return isRightItem;
	}
	
	public Color getParticleColor()
	{
		Color color = new Color(150, 75, 0);
		return color;
	}
	
	public int getParticleSize()
	{
		int size = 6;
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
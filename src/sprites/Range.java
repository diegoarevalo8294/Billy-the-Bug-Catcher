package sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Range extends Sprite{
	
	Sprite caster;

	public Range(GamePanel gp) {
		super(gp);
	}
	
	
	public void set(int wX, int wY, String direction, boolean alive, Sprite caster)
	{
		this.wX = wX;
		this.wY = wY;
		this.direction = direction;
		this.alive = alive;
		this.caster = caster;
		this.life = this.maxLife;
	}
	
	public void update()
	{
		if(caster == gp.billy)
		{
			int enemyIndex = gp.cChecker.checkSprite(this, gp.enemy);
			if(enemyIndex != 999)
			{
				gp.billy.damageEnemy(enemyIndex);
				generateParticle(caster.projectile, gp.enemy[gp.currentMap][enemyIndex]);
				alive = false;
			}
		}
		
		if(caster != gp.billy) 
		{
			boolean contactBilly = gp.cChecker.checkBilly(this);
			if(gp.billy.iFrame == false && contactBilly == true)
			{
				damageBilly();
				generateParticle(caster.projectile, gp.billy);
				alive = false;
			}
		}
		
		switch(direction)
		{
		case "front": wY += speed; break;
		case "back": wY -= speed; break;
		case "left": wX -= speed; break;
		case "right": wX += speed; break;
		}
		
		life--;
		if(life <= 0)
		{
			alive = false;
		}
		
		counter++;
		if(counter > 12)
		{
			if(num == 1)
			{
				num = 2;
			}
			else if(num == 2) 
			{
				num = 1;
			}
			counter = 0;
		}
	}
	
	public boolean haveRes(Sprite caster)
	{
		boolean haveRes = false;
		return haveRes;
	}
	
	public void takeRes(Sprite caster)
	{

	}

}
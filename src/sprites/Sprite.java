package sprites;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import item.iKey;

import main.GamePanel;
import main.UtilityTool;

public class Sprite {
	
	GamePanel gp;
	public int wX, wY;
	public int speed;
	
	public BufferedImage back1, back2, front1, front2, left1, left2, right1, right2, secret;
	public String direction = "front";
	public BufferedImage attackFront1, attackFront2, attackBack1, attackBack2, attackLeft1, attackLeft2, attackRight1, attackRight2;
	public boolean attacking = false;
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	UtilityTool uTool = new UtilityTool();
	
	public BufferedImage image, image2, image3;
	public String name;
	public boolean talking = false;
	public boolean collision = false;
	public int defaultSpeed;
	int knockBackCounter = 0;
	
	public int counter = 0;
	public int num = 1;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	String talks[] = new String[15];
	int talkIndex = 0;
	public int actionCounter = 0;
	public boolean iFrame = false;
	public int iFrameCounter = 0;
	public int shootCounter = 0;
	public int type; // 0 = player, 1 = npc, 2 = monster, 3 = punch, 4 = axe, 5 = consumable
	
	public ArrayList<Sprite> inventory = new ArrayList<>();
	public final int inventorySize = 20;
	public int maxLife;
	public int life;
	public int attack;
	public int saliva;
	public int maxSaliva;
	public Range projectile;
	public boolean alive = true;
	public boolean dying = false;
	public int dyingCounter = 0;
	public int bugsKilled;
	public String desc = "";
	public int useCost;
	public Sprite currentWeapon;
	public int attackValue;
	public int price;
	public boolean knockBack = false;
	
	
	
	
	public Sprite(GamePanel gp)
	{
		this.gp = gp;
	}
	
	public void setAction()
	{
		
	}
	
	public void damageReaction()
	{
		
	}
	
	public void speak()
	{
		
	}
	
	public void eat(Sprite sprite)
	{
		
	}
	
	public void drink(Sprite sprite)
	{
		
	}
	
	public void drin(Sprite sprite)
	{
		
	}
	
	public BufferedImage setup(String imagePath)
	{
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return image;
	}
	
	public Color getParticleColor()
	{
		Color color = null;
		return color;
	}
	
	public int getParticleSize()
	{
		int size = 0;
		return size;
	}
	
	public int getParticleSpeed()
	{
		int speed = 0;
		return speed;
	}
	
	public int getParticleMaxLife()
	{
		int maxLife = 0;
		return maxLife;
	}
	
	public void generateParticle(Sprite gen, Sprite target)
	{
		Color color = gen.getParticleColor();
		int size = gen.getParticleSize();
		int speed = gen.getParticleSpeed();
		int maxLife = gen.getParticleMaxLife();
		
		Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
		Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
		Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
		Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
		gp.particleList.add(p1);
		gp.particleList.add(p2);
		gp.particleList.add(p3);
		gp.particleList.add(p4);
	}
	
	public void update()
	{
		if(knockBack == true)
		{
			collisionOn = false;
			gp.cChecker.checkTile(this);
			gp.cChecker.checkItem(this, false);
			gp.cChecker.checkSprite(this, gp.npc);
			gp.cChecker.checkSprite(this, gp.enemy);
			gp.cChecker.checkSprite(this, gp.iTile);
			boolean contactBilly = gp.cChecker.checkBilly(this);
			
			if((this.type == 2) && contactBilly == true)
			{
				damageBilly();
			}
			
			if(collisionOn == true)
			{
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
			else if (collisionOn == false)
			{
				switch(gp.billy.direction) 
				{
				case "back":
					wY = wY - speed;
					break;
				case "front":
					wY = wY + speed;
					break;
				case "left":
					wX = wX - speed;
					break;
				case "right":
					wX = wX + speed;
					break;
				}
			}
			
			knockBackCounter++;
			if(knockBackCounter == 10)
			{
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
		}
		else 
		{
			setAction();
			
			collisionOn = false;
			gp.cChecker.checkTile(this);
			gp.cChecker.checkItem(this, false);
			gp.cChecker.checkSprite(this, gp.npc);
			gp.cChecker.checkSprite(this, gp.enemy);
			gp.cChecker.checkSprite(this, gp.iTile);
			boolean contactBilly = gp.cChecker.checkBilly(this);
			
			if((this.type == 2) && contactBilly == true)
			{
				damageBilly();
			}
			
			if(collisionOn == false) {
				
				switch(direction) {
				case "back":
					wY = wY - speed;
					break;
				case "front":
					wY = wY + speed;
					break;
				case "left":
					wX = wX - speed;
					break;
				case "right":
					wX = wX + speed;
					break;
				}
			}
		}
		
		
	
		counter++;
		if(counter > 15)
		{
			if(num ==1)
			{
				num = 2;
			}
			else if(num ==2)
			{
				num = 1;
			}
			counter = 0;
		}
		
		if(iFrame == true)
		{
			iFrameCounter++;
			if(iFrameCounter > 30)
			{
				iFrame = false;
				iFrameCounter = 0;
			}
		}
	}
	
	public void damageBilly()
	{
		if(gp.billy.iFrame == false) 
		{
			gp.playSound(3);
			gp.billy.life -= 1;
			gp.billy.iFrame = true;
		}
	}
	
	public void draw(Graphics2D g2)
	{
		BufferedImage pic = null;
		
		int sX = wX - gp.billy.wX + gp.billy.sX;
		int sY = wY - gp.billy.wY + gp.billy.sY;
		
		if(wX + gp.tileSize> gp.billy.wX - gp.billy.sX &&
		   wX - gp.tileSize< gp.billy.wX + gp.billy.sX &&
		   wY + gp.tileSize> gp.billy.wY - gp.billy.sY &&
		   wY - gp.tileSize< gp.billy.wY + gp.billy.sY)
		{
			switch(direction)
			{
			case "back":
				if(num ==1)
				{
					pic = back1;
				}
				if(num ==2)
				{
					pic = back2;
				}
				
				break;
			case "front":
				if(num ==1)
				{
					pic = front1;
				}
				if(num ==2)
				{
					pic = front2;
				}
				break;
			case "left":
				if(num ==1)
				{
					pic = left1;
				}
				if(num ==2)
				{
					pic = left2;
				}
				break;
			case "right":
				if(num ==1)
				{
					pic = right1;
				}
				if(num ==2)
				{
					pic = right2;
				}
				break;
			}
			
			if(type == 2)
			{
				double oneScale = (double) gp.tileSize/maxLife;
				double hpBarValue = oneScale*life;
				
				g2.setColor(new Color(0, 0, 0));
				g2.fillRect(sX - 1, sY - 16, gp.tileSize + 2, 12);
				
				g2.setColor(new Color(255, 0, 30));
				g2.fillRect(sX, sY - 15, (int)hpBarValue, 10);
			}
			
			
			
			if(iFrame == true)
			{
				changeAlpha(g2, 0.4F);
			}
			
			if(dying == true)
			{
				dyingAnimate(g2);
			}
			
			g2.drawImage(pic, sX, sY, null);
			
			changeAlpha(g2, 1F);
		}
	}
	
	
	public void dyingAnimate(Graphics2D g2)
	{
		dyingCounter++;
		
		int i = 5;
		
		if(dyingCounter <= i)
		{
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0F));
			changeAlpha(g2, 0f);
		}
		
		if(dyingCounter > i && dyingCounter <= i*2)
		{
			changeAlpha(g2, 1f);
		}
		
		if(dyingCounter > i*2 && dyingCounter <= i*3)
		{
			changeAlpha(g2, 0f);
		}
		
		if(dyingCounter > i*3 && dyingCounter <= i*4)
		{
			changeAlpha(g2, 1f);
		}
		
		if(dyingCounter > i*4 && dyingCounter <= i*5)
		{
			changeAlpha(g2, 0f);
		}
		
		if(dyingCounter > i*5 && dyingCounter <= i*6)
		{
			changeAlpha(g2, 1f);
		}
		
		if(dyingCounter > i*6 && dyingCounter <= i*7)
		{
			changeAlpha(g2, 0f);
		}
		
		if(dyingCounter > i*7 && dyingCounter <= i*8)
		{
			changeAlpha(g2, 1f);
		}
		
		if(dyingCounter > i*8)
		{
			alive = false;
		}
	}
	
	
	
	public void changeAlpha(Graphics2D g2, float alphaValue)
	{
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}
}
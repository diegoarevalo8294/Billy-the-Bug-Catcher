package sprites;

import main.playerInput;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UI;
import main.UtilityTool;
import item.iKey;
import item.iPunch;
import item.iSpit;

public class Billy extends Sprite{
	

	playerInput pI;

	public final int sX;
	public final int sY;
	boolean hasKey = false;
	
	
	public Billy(GamePanel gp, playerInput pI)
	{
		super(gp);
		this.pI = pI;
		
		sX = gp.screenWidth / 2 - (gp.tileSize/2);
		sY = gp.screenHeight / 2 - (gp.tileSize/2);
		
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        
        // attackArea.width = 45;
        // attackArea.height = 45;
		
		setValues();
		drawPlayer();
		drawPlayerAttack();
		setItems();
	}
	
	//player default values
	public void setValues()
	{
		wX = gp.tileSize * 22;
		wY = gp.tileSize * 42;
		
		speed = 5;
		defaultSpeed = 5;
		direction = "front";
		hasKey = false;
		
		maxLife = 100;
		life = maxLife;
		maxSaliva = 100;
		saliva = maxSaliva;
		currentWeapon = new iPunch(gp);
		attack = getAttack();
		
		projectile = new iSpit(gp);
		System.out.println(life);
		System.out.println(bugsKilled);
	}
	
	public int getAttack()
	{
		attackArea = currentWeapon.attackArea;
		return attack = currentWeapon.attackValue;
	}
	
	public void setItems()
	{
		inventory.add(currentWeapon);
	}
	
	
	
	//draw player sprite
	public void drawPlayer()
	{
		back1 = setup("billyBack1");
		back2 = setup("billyBack2");
		front1 = setup("billyFront1");
		front2 = setup("billyFront2");
		left1 = setup("billyLeft1");
		left2 = setup("billyLeft2");
		right1 = setup("billyRight1");
		right2 = setup("billyRight2");
	}
	
	public void drawPlayerAttack()
	{
		if(currentWeapon.type == 3)
		{
			attackBack1 = setup("billyAtBack1");
			attackBack2 = setup("billyAtBack2");
			attackFront1 = setup("billyAtFront1");
			attackFront2 = setup("billyAtFront2");
			attackLeft1 = setup("billyAtLeft1");
			attackLeft2 = setup("billyAtLeft2");
			attackRight1 = setup("billyAtRight1");
			attackRight2 = setup("billyAtRight2");
		}
		
		if(currentWeapon.type == 4)
		{
			attackBack1 = setup("billyAxeBack1");
			attackBack2 = setup("billyAxeBack2");
			attackFront1 = setup("billyAxeFront1");
			attackFront2 = setup("billyAxeFront2");
			attackLeft1 = setup("billyAxeLeft1");
			attackLeft2 = setup("billyAxeLeft2");
			attackRight1 = setup("billyAxeRight1");
			attackRight2 = setup("billyAxeRight2");
		}
	}
	
	public BufferedImage setup(String imagePath)
	{
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/billy/" + imagePath + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return image;
	}
	
	// update player sprite
	public void update()
	{
		
		if(attacking == true)
		{
			attack();
		}

		else if(pI.upPressed == true || pI.downPressed == true || pI.leftPressed == true || pI.rightPressed == true)
		{
			if (pI.upPressed == true)
			{
				direction = "back";
			}
			
			else if (pI.downPressed == true)
			{
				direction = "front";
			}
			
			else if (pI.leftPressed == true)
			{
				direction = "left";
			}
			
			else if (pI.rightPressed == true)
			{
				direction = "right";
			}
			
			// collision checker
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// check item
			int itemIndex = gp.cChecker.checkItem(this, true);
			getItem(itemIndex);
			
			// npc interaction
			int npcIndex = gp.cChecker.checkSprite(this, gp.npc);
			interactNPC(npcIndex);
			
			// enemy hit detection
			int enemyIndex = gp.cChecker.checkSprite(this, gp.enemy);
			enemyHit(enemyIndex);
			
			int iTileSprite = gp.cChecker.checkSprite(this,  gp.iTile);
			
			// checks if event happens
			gp.iInput.checkEvent();
			
			
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
		}
		
		if(gp.pI.projPressed == true && projectile.alive == false && shootCounter == 30 && projectile.haveRes(this) == true)
		{
			projectile.set(wX, wY, direction, true, this);
			projectile.takeRes(this);
			gp.projList.add(projectile);
			shootCounter = 0;
			gp.playSound(7);
		}
		
		if(iFrame == true)
		{
			iFrameCounter++;
			if(iFrameCounter > 60)
			{
				iFrame = false;
				iFrameCounter = 0;
			}
		}
		
		if(life > maxLife)
		{
			life = maxLife;
		}
		
		if(life <= 0)
		{
			gp.gameState = gp.deadState;
			
		}
		
		if(shootCounter < 30)
		{
			shootCounter++;
		}
		
	}
	
	public void attack()
	{

		counter++;
		
		if(counter <= 5)
		{
			num = 1;
		}
		
		if(counter > 5 && counter <= 25)
		{
			num = 2;
			
			int currentWX = wX;
			int currentWY = wY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			switch(direction)
			{
			case "back": wY -= attackArea.height; break;
			case "front": wY += attackArea.height; break;
			case "left": wX -= attackArea.width; break;
			case "right": wX += attackArea.width; break;
			}
			
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			int enemyIndex = gp.cChecker.checkSprite(this, gp.enemy);
			damageEnemy(enemyIndex);
			
			int iTileSprite = gp.cChecker.checkSprite(this,  gp.iTile);
			damageIntTile(iTileSprite);
			
			wX = currentWX;
			wY = currentWY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		
		if(counter > 25)
		{
			num = 1;
			counter = 0;
			attacking = false;
		}
	}
	
	public void damageIntTile(int i)
	{
		if(i != 999 && gp.iTile[gp.currentMap][i].destructible == true && gp.iTile[gp.currentMap][i].isRightItem(this) == true && gp.iTile[gp.currentMap][i].iFrame == false)
		{
			gp.iTile[gp.currentMap][i].life--;
			gp.iTile[gp.currentMap][i].iFrame = true;
			generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);
			if(gp.iTile[gp.currentMap][i].life == 0)
			{
				gp.iTile[gp.currentMap][i] = null;
			}
		}
	}
	
	public void getItem(int i)
	{
		if(i != 999)
		{
			String itemName = gp.item[gp.currentMap][i].name;
			String text;
			
			if(inventory.size() != inventorySize)
			{
				inventory.add(gp.item[gp.currentMap][i]);
			}
			
			else
			{
				gp.ui.showMessage("ur bag is full dude...");
				gp.item[gp.currentMap][i] = null;
			}
			
			switch(itemName)
			{
			case "Iron Axe":
				gp.playSound(2);
				gp.item[gp.currentMap][i] = null;
			case "Pizza":
				gp.item[gp.currentMap][i] = null;
			case "Milk":
				gp.item[gp.currentMap][i] = null;
			case "Water Bottle":
				gp.item[gp.currentMap][i] = null;
			case "Key":
				gp.playSound(2);
				hasKey = true;
				gp.item[gp.currentMap][i] = null;
				gp.ui.showMessage("New item discovered");
				break;
			case "door":
				if(hasKey == true)
				{
					gp.item[gp.currentMap][i] = null;
					hasKey = false;
					gp.ui.showMessage("Used Door Key");
				}
				else
				{
					gp.ui.showMessage("Door is locked from this side");
				}
				break;
			}
		}
	}
	
	
	public void interactNPC(int i)
	{
		if(gp.pI.attackPressed == true)
		{
			gp.playSound(4);
			attacking = true;
		}
		
		if(i != 999)
		{
			gp.gameState = gp.talkState;
			gp.npc[gp.currentMap][i].speak();
		}
	}
	
	public void enemyHit(int i)
	{
		if(i != 999)
		{
			if(iFrame == false && gp.enemy[gp.currentMap][i].dying == false)
			{
				//gp.playSound(3);
				
				life -= 1;
				iFrame = true;
			}
		}
	}
	
	public void damageEnemy(int i)
	{
		if(i != 999)
		{
			if(gp.enemy[gp.currentMap][i].iFrame == false)
			{
				knockBack(gp.enemy[gp.currentMap][i]);
				//gp.playSound(5);
				gp.enemy[gp.currentMap][i].life -=1;
				gp.enemy[gp.currentMap][i].iFrame = true;
				gp.enemy[gp.currentMap][i].damageReaction();
				
				
				if(gp.enemy[gp.currentMap][i].life <= 0)
				{
					gp.enemy[gp.currentMap][i].dying = true;
					bugsKilled += 1;
					//gp.playSound(6);
					System.out.println(bugsKilled);
				}
			}
		}
	}
	
	public void knockBack(Sprite sprite)
	{
		sprite.direction = direction;
		sprite.speed += 10;
		sprite.knockBack = true;
	}
	
	public void selectItem()
	{
		int itemIndex = gp.ui.getItemIndex(gp.ui.billySlotCol, gp.ui.billySlotRow);
		
		if(itemIndex < inventory.size())
		{
			Sprite selectedItem = inventory.get(itemIndex);
			if(selectedItem.type == 3 || selectedItem.type == 4)
			{
				currentWeapon = selectedItem;
				attack = getAttack();
				drawPlayerAttack();
			}
			
			if(selectedItem.type == 5)
			{
				selectedItem.eat(this);
				selectedItem.drink(this);
				selectedItem.drin(this);
				inventory.remove(itemIndex);
			}
		}
	}
	
	public void draw(Graphics2D g2)
	{
		BufferedImage pic = null;
		
		switch(direction)
		{
		case "back":
			if(attacking == false)
			{
				if(num ==1)
				{
					pic = back1;
				}
				if(num ==2)
				{
					pic = back2;
				}
			}
			if(attacking == true)
			{
				if(num ==1)
				{
					pic = attackBack1;
				}
				if(num ==2)
				{
					pic = attackBack2;
				}
			}
			break;
		case "front":
			if(attacking == false)
			{
				if(num ==1)
				{
					pic = front1;
				}
				if(num ==2)
				{
					pic = front2;
				}
			}
			if(attacking == true)
			{
				if(num ==1)
				{
					pic = attackFront1;
				}
				if(num ==2)
				{
					pic = attackFront2;
				}
			}
			break;
		case "left":
			if(attacking == false)
			{
				if(num ==1)
				{
					pic = left1;
				}
				if(num ==2)
				{
					pic = left2;
				}
			}
			if(attacking == true)
			{
				if(num ==1)
				{
					pic = attackLeft1;
				}
				if(num ==2)
				{
					pic = attackLeft2;
				}
			}
			break;
		case "right":
			if(attacking == false)
			{
				if(num ==1)
				{
					pic = right1;
				}
				if(num ==2)
				{
					pic = right2;
				}
			}
			if(attacking == true)
			{
				if(num ==1)
				{
					pic = attackRight1;
				}
				if(num ==2)
				{
					pic = attackRight2;
				}
			}
			break;
		}
		
		if(iFrame == true)
		{
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4F));
		}
		
		g2.drawImage(pic, sX, sY, null);
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
		
		g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		g2.setColor(Color.white);
		g2.drawString("Lives: " + life, 10,  300);
		
		g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		g2.setColor(Color.white);
		g2.drawString("Shots: " + saliva, 10, 350);
		
		g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		g2.setColor(Color.white);
		g2.drawString("Bugs Captured: " + bugsKilled, 10, 400);
	}
	
}
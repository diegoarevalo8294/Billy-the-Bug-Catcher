package main;
import sprites.Sprite;
public class ColDetection 
{
	
	GamePanel gp;
	
	public ColDetection(GamePanel gp)
	{
		this.gp = gp;
	}
	
	public void checkTile(Sprite sprite)
	{
		
		int entityLeftWorldX = sprite.wX + sprite.solidArea.x;
		int entityRightWorldX = sprite.wX + sprite.solidArea.x + sprite.solidArea.width;
		int entityTopWorldY = sprite.wY + sprite.solidArea.y;
		int entityBottomWorldY = sprite.wY + sprite.solidArea.y + sprite.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(sprite.direction)
		{
		case "back":
			entityTopRow = (entityTopWorldY - sprite.speed) / gp.tileSize;
			tileNum1 = gp.tile.mapNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tile.mapNum[gp.currentMap][entityRightCol][entityTopRow];
			if(gp.tile.tile[tileNum1].collision == true || gp.tile.tile[tileNum2].collision == true)
			{
				sprite.collisionOn = true;
			}
			break;
			
		case "front":
			entityBottomRow = (entityBottomWorldY + sprite.speed) / gp.tileSize;
			tileNum1 = gp.tile.mapNum[gp.currentMap][entityLeftCol][entityBottomRow];
			tileNum2 = gp.tile.mapNum[gp.currentMap][entityRightCol][entityBottomRow];
			if(gp.tile.tile[tileNum1].collision == true || gp.tile.tile[tileNum2].collision == true)
			{
				sprite.collisionOn = true;
			}
			break;
			
		case "left":
			entityLeftCol = (entityLeftWorldX - sprite.speed) / gp.tileSize;
			tileNum1 = gp.tile.mapNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tile.mapNum[gp.currentMap][entityLeftCol][entityBottomRow];
			if(gp.tile.tile[tileNum1].collision == true || gp.tile.tile[tileNum2].collision == true)
			{
				sprite.collisionOn = true;
			}
			break;
			
		case "right":
			entityRightCol = (entityRightWorldX + sprite.speed) / gp.tileSize;
			tileNum1 = gp.tile.mapNum[gp.currentMap][entityRightCol][entityTopRow];
			tileNum2 = gp.tile.mapNum[gp.currentMap][entityRightCol][entityBottomRow];
			if(gp.tile.tile[tileNum1].collision == true || gp.tile.tile[tileNum2].collision == true)
			{
				sprite.collisionOn = true;
			}
			break;
		}
		
	}
	public int checkItem(Sprite sprite, boolean billy)
	{
		int index = 999;
		
		for(int i = 0; i < gp.item[1].length; i++)
		{
			if(gp.item[gp.currentMap][i] != null)
			{
				sprite.solidArea.x = sprite.wX + sprite.solidArea.x;
				sprite.solidArea.y = sprite.wY + sprite.solidArea.y;
				
				gp.item[gp.currentMap][i].solidArea.x = gp.item[gp.currentMap][i].wX + gp.item[gp.currentMap][i].solidArea.x;
				gp.item[gp.currentMap][i].solidArea.y = gp.item[gp.currentMap][i].wY + gp.item[gp.currentMap][i].solidArea.y;
				
				switch(sprite.direction) {
				case "back":
					sprite.solidArea.y = sprite.solidArea.y - sprite.speed;
					if(sprite.solidArea.intersects(gp.item[gp.currentMap][i].solidArea)) {
						if(gp.item[gp.currentMap][i].collision == true)
						{
							sprite.collisionOn = true;
						}
						if(billy == true)
						{
							index = i;
						}
					}
					break;
				case "front":
					sprite.solidArea.y = sprite.solidArea.y + sprite.speed;
					if(sprite.solidArea.intersects(gp.item[gp.currentMap][i].solidArea)) {
						if(gp.item[gp.currentMap][i].collision == true)
						{
							sprite.collisionOn = true;
						}
						if(billy == true)
						{
							index = i;
						}
					}
					break;
				case "left":
					sprite.solidArea.x = sprite.solidArea.y - sprite.speed;
					if(sprite.solidArea.intersects(gp.item[gp.currentMap][i].solidArea)) {
						if(gp.item[gp.currentMap][i].collision == true)
						{
							sprite.collisionOn = true;
						}
						if(billy == true)
						{
							index = i;
						}
					}
					break;
				case "right":
					sprite.solidArea.x = sprite.solidArea.x + sprite.speed;
					if(sprite.solidArea.intersects(gp.item[gp.currentMap][i].solidArea)) {
						if(gp.item[gp.currentMap][i].collision == true)
						{
							sprite.collisionOn = true;
						}
						if(billy == true)
						{
							index = i;
						}
					}
					break;
				}
				sprite.solidArea.x = sprite.solidAreaDefaultX;
				sprite.solidArea.y = sprite.solidAreaDefaultY;
				gp.item[gp.currentMap][i].solidArea.x = gp.item[gp.currentMap][i].solidAreaDefaultX;
				gp.item[gp.currentMap][i].solidArea.y = gp.item[gp.currentMap][i].solidAreaDefaultY;
			}
			
		}
		
		return index;
	}
	
	public int checkSprite(Sprite sprite, Sprite[][] target)
	{
		int index = 999;
		
		for(int i = 0; i < target[1].length; i++)
		{
			if(target[gp.currentMap][i] != null)
			{
				sprite.solidArea.x = sprite.wX + sprite.solidArea.x;
				sprite.solidArea.y = sprite.wY + sprite.solidArea.y;
				
				target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].wX + target[gp.currentMap][i].solidArea.x;
				target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].wY + target[gp.currentMap][i].solidArea.y;
				
				switch(sprite.direction) {
				case "back":
					sprite.solidArea.y = sprite.solidArea.y - sprite.speed;
					if(sprite.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
						if(target[gp.currentMap][i] != sprite)
						{
							sprite.collisionOn = true;
							index = i;
						}
					}
					break;
				case "front":
					sprite.solidArea.y = sprite.solidArea.y + sprite.speed;
					if(sprite.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
						if(target[gp.currentMap][i] != sprite)
						{
							sprite.collisionOn = true;
							index = i;
						}
					}
					break;
				case "left":
					sprite.solidArea.x = sprite.solidArea.y - sprite.speed;
					if(sprite.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
						if(target[gp.currentMap][i] != sprite)
						{
							sprite.collisionOn = true;
							index = i;
						}
					}
					break;
				case "right":
					sprite.solidArea.x = sprite.solidArea.x + sprite.speed;
					if(sprite.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
						if(target[gp.currentMap][i] != sprite)
						{
							sprite.collisionOn = true;
							index = i;
						}

					}
					break;
				}
				sprite.solidArea.x = sprite.solidAreaDefaultX;
				sprite.solidArea.y = sprite.solidAreaDefaultY;
				target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
				target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
			}
			
		}
		
		return index;
	}
	
	public boolean checkBilly(Sprite sprite)
	{
		boolean contactBilly = false;
		
		sprite.solidArea.x = sprite.wX + sprite.solidArea.x;
		sprite.solidArea.y = sprite.wY + sprite.solidArea.y;
		
		gp.billy.solidArea.x = gp.billy.wX + gp.billy.solidArea.x;
		gp.billy.solidArea.y = gp.billy.wY + gp.billy.solidArea.y;
		
		switch(sprite.direction) {
		case "back":
			sprite.solidArea.y = sprite.solidArea.y - sprite.speed;
			if(sprite.solidArea.intersects(gp.billy.solidArea)) {
				sprite.collisionOn = true;
				contactBilly = true;
			}
			break;
		case "front":
			sprite.solidArea.y = sprite.solidArea.y + sprite.speed;
			if(sprite.solidArea.intersects(gp.billy.solidArea)) {
				sprite.collisionOn = true;
				contactBilly = true;
			}
			break;
		case "left":
			sprite.solidArea.x = sprite.solidArea.y - sprite.speed;
			if(sprite.solidArea.intersects(gp.billy.solidArea)) {
				sprite.collisionOn = true;
				contactBilly = true;
			}
			break;
		case "right":
			sprite.solidArea.x = sprite.solidArea.x + sprite.speed;
			if(sprite.solidArea.intersects(gp.billy.solidArea)) {
				sprite.collisionOn = true;
				contactBilly = true;
			}
			break;
		}
		sprite.solidArea.x = sprite.solidAreaDefaultX;
		sprite.solidArea.y = sprite.solidAreaDefaultY;
		gp.billy.solidArea.x = gp.billy.solidAreaDefaultX;
		gp.billy.solidArea.y = gp.billy.solidAreaDefaultY;
		
		return contactBilly;
	}
}
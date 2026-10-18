package main;

import java.awt.Rectangle;

public class EventInput {
	
	GamePanel gp;
	Rectangle eventRect[][];
	EventHand eventHand[][][];
	int eventRectX, eventRectY;
	
	public EventInput(GamePanel gp)
	{
		this.gp = gp;
		
		eventHand = new EventHand[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		int map = 0;
		int col = 0;
		int row = 0;
		while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow)
		{
			eventHand[map][col][row] = new EventHand();
			eventHand[map][col][row] = new EventHand();
			eventHand[map][col][row].x = 23;
			eventHand[map][col][row].y = 23;
			eventHand[map][col][row].width = 5;
			eventHand[map][col][row].height = 5;
			eventHand[map][col][row].eventRectX = eventHand[map][col][row].x;
			eventHand[map][col][row].eventRectY = eventHand[map][col][row].y;
			
			col++;
			if(col == gp.maxWorldCol) 
			{
				col = 0;
				row++;
				
				if(row == gp.maxWorldRow) 
				{
					row = 0;
					map++;
				}
			}
		}
	}
	
	public void checkEvent()
	{
		if((hit(0, 15, 43, "right")==true) || (hit(0, 15, 43, "front")==true))
		{
			damagePit(0, 15, 43, gp.talkState);
		}
		
		else if(hit(0, 8, 39, "front")==true)
		{
			gainSpit(0, 8, 39, gp.talkState);
		}
		
		else if(hit(0, 48, 40, "front") == true)
		{
			teleport(1,22,42);
		}
		
		else if(hit(1, 48, 40, "front") == true)
		{
			teleport(0, 22, 42);
		}
		
		
	}
	public boolean hit(int map, int col, int row, String reqDirection)
	{
		boolean hit = false;
		
		if(map == gp.currentMap)
		{
			gp.billy.solidArea.x = gp.billy.wX + gp.billy.solidArea.x;
			gp.billy.solidArea.y = gp.billy.wY + gp.billy.solidArea.y;
			eventHand[map][col][row].x = col*gp.tileSize + eventHand[map][col][row].x;
			eventHand[map][col][row].y = row*gp.tileSize + eventHand[map][col][row].y;
			
			if(gp.billy.solidArea.intersects(eventHand[map][col][row]) && eventHand[map][col][row].eventTrigger == false)
			{
				if(gp.billy.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any"))
				{
					hit = true;
				}
			}
			
			gp.billy.solidArea.x = gp.billy.solidAreaDefaultX;
			gp.billy.solidArea.y = gp.billy.solidAreaDefaultY;
			eventHand[map][col][row].x = eventHand[map][col][row].eventRectX;
			eventHand[map][col][row].y = eventHand[map][col][row].eventRectY;
		}
		
		
		return hit;
	}
	
	public void damagePit(int map, int col, int row, int gameState)
	{
		gp.gameState = gameState;
		gp.ui.currentTalk = "you died lol";
		//gp.playSound(3);
		gp.billy.life -= 3;
		System.out.println(gp.billy.life);
		eventHand[map][col][row].eventTrigger = true;
	}
	
	public void teleport(int map, int col, int row)
	{
		gp.currentMap = map;
		gp.billy.wX = gp.tileSize * col;
		gp.billy.wY = gp.tileSize * row;
		//gp.playSound(3);
		
	}
	
	public void gainSpit(int map, int col, int row, int gameState)
	{
		if(gp.pI.spacePressed == true)
		{
			gp.gameState = gameState;
			gp.ui.currentTalk = "You drank an unknown substance";
			//gp.playSound(6);
			gp.billy.saliva += 6;
			eventHand[map][col][row].eventTrigger = true;
		}
		gp.pI.spacePressed = false;
	}
}
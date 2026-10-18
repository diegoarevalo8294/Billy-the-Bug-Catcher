package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import sprites.Sprite;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	BufferedImage heart1, heart2;
	
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public String currentTalk = "";
	public int comNum = 0;
	public boolean loreState = false;
	public int billySlotCol = 0;
	public int billySlotRow = 0;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	int subState = 0;
	public Sprite npc;
	
	
	public UI(GamePanel gp)
	{
		this.gp = gp;
		

	}
	
	public void showMessage(String text)
	{
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2)
	{
		this.g2 = g2;
		
		g2.setFont(new Font ("Comic Sans MS", Font.PLAIN, 35));
		g2.setColor(Color.white);
		
		if(gp.gameState == gp.startState) 
		{
			drawStartScreen();
		}
		
		if(gp.gameState == gp.playState)
		{
			
		}
		
		if(gp.gameState == gp.pauseState)
		{
			drawPauseScreen();
		}
		
		if(gp.gameState == gp.talkState)
		{
			drawTalkScreen();
		}
		
		if(gp.gameState == gp.stuffState) 
		{
			drawInventoryScreen(gp.billy, true);
		}
		
		if(gp.gameState == gp.optionState)
		{
			drawOptionScreen();
		}
		
		if(gp.gameState == gp.deadState) 
		{
			drawDeadScreen();
		}
		
		if(gp.gameState == gp.tradeState)
		{
			drawTradeScreen();
		}
		
		if(messageOn == true)
		{
			g2.setFont(new Font ("Comic Sans MS", Font.PLAIN, 35));
			g2.setColor(Color.white);
			g2.drawString(message, gp.tileSize/2, gp.tileSize);
			
			messageCounter++;
			
			if(messageCounter > 180)
			{
				messageCounter = 0;
				messageOn = false;
			}
		}
	}
	
	public void drawStartScreen()
	{
		if(loreState == false)
		{
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
			String string = "Billy the Bug Catcher";
			String string2 = "THE GAME";
			int x = getCenterString(string);
			int y = gp.tileSize * 3;
			
			g2.setColor(Color.black);
			g2.drawString(string2, x+165, y+115);
			g2.drawString(string, x+5, y+5);
			
			g2.setColor(Color.white);
			g2.drawString(string2, x+160, y+110);
			g2.drawString(string, x, y);
			
			x = gp.screenWidth/2 - (gp.tileSize*2)/2;
			y += gp.tileSize*2;
			g2.drawImage(gp.billy.secret, 0, 180, 800, 400, null);
			
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
			
			string = "Play";
			x = getCenterString(string);
			y += gp.tileSize * 4;
			g2.drawString(string, x, y-20);
			if(comNum == 0)
			{
				g2.drawString("-", x-gp.tileSize, y-20);
			}
			
			string = "Quit";
			x = getCenterString(string);
			y += gp.tileSize;
			g2.drawString(string, x, y+20);
			if(comNum == 1)
			{
				g2.drawString("-", x-gp.tileSize, y+20);
			}
		}
		
		else if(loreState == true)
		{
			g2.setFont(g2.getFont().deriveFont(50F));
			
			String string = "Lore Time!";
			int x = getCenterString(string);
			int y = gp.tileSize;
			
			g2.setColor(Color.black);
			g2.drawString(string, x+3, y+3);
			
			g2.setColor(Color.green);
			g2.drawString(string, x, y);
			
			g2.setFont(g2.getFont().deriveFont(30F));
			
			String string2 = "It's Billy's first day on the Safari Zone where /nhe will be able to catch a lot of bugs! /nHowever, he forgot his handy dandy bug net /nat home "
					+ "because he was gaming too hard. /nYou must guide Billy on how to catch the /nbugs without his bug net, but also while /nkeeping him safe.";
			
			for(String line : string2.split("/n"))
			{
				g2.drawString(line, gp.tileSize, y+100);
				y += 50;
			}
			
			g2.setFont(g2.getFont().deriveFont(50F));
			
			String string3 = "Press Enter";
			
			g2.setColor(Color.black);
			g2.drawString(string3, 453, 503);
			
			g2.setColor(Color.green);
			g2.drawString(string3, 450, 500);
		}
		
	}
	
	public void drawPauseScreen()
	{
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String string = "Paused Game";
		int x = getCenterString(string);
		int y = gp.screenHeight/2;
		
		g2.drawString(string, x, y);
	}
	
	public void drawTalkScreen()
	{
		int x = gp.tileSize * 2;
		int y = gp.tileSize * 8;
		int width = gp.screenWidth - (gp.tileSize * 4);
		int height = gp.tileSize * 4;
		
		// drawSubscreen(x, y-50, 200, 50);
		drawSubscreen(x, y, width, height);
		
		x += gp.tileSize;
		y += gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
		// g2.drawString("insert npc name here", x, y-65);
		
			
		for(String line : currentTalk.split("/n"))
		{
			g2.drawString(line, x, y);
			y+= 40;
		}
	}
	
	public void drawInventoryScreen(Sprite sprite, boolean cursor)
	{
		int frameX = 0;
		int frameY = 0;
		int frameWidth = 0;
		int frameHeight = 0;
		int slotCol = 0;
		int slotRow = 0;
		
		if(sprite == gp.billy)
		{
			frameX = gp.tileSize * 9;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize * 6;
			frameHeight = gp.tileSize * 5;
			slotCol = billySlotCol;
			slotRow = billySlotRow;
		}
		else
		{
			frameX = gp.tileSize * 1;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize * 6;
			frameHeight = gp.tileSize * 5;
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;
		}
		
		
		drawSubscreen(frameX, frameY, frameWidth, frameHeight);
		
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.tileSize + 3;
		
		for(int i = 0; i < sprite.inventory.size(); i++)
		{
			if(sprite.inventory.get(i) == sprite.currentWeapon)
			{
				g2.setColor(new Color (50, 205, 50));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
			
			g2.drawImage(sprite.inventory.get(i).front1, slotX, slotY, null);
			
			slotX += slotSize;
			
			if(i == 4 || i == 9 || i == 14)
			{
				slotX = slotXstart;
				slotY += slotSize;
			}
		}
		if(cursor == true)
		{
			int cursorX = slotXstart + (slotSize * slotCol);
			int cursorY = slotYstart + (slotSize * slotRow);
			int cursorWidth = gp.tileSize;
			int cursorHeight = gp.tileSize;
			
			g2.setColor(new Color (50, 205, 50));
			g2.setStroke(new BasicStroke(3));
			g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
			
			int dFrameX = frameX;
			int dFrameY = frameY + frameHeight + 25;
			int dFrameWidth = frameWidth;
			int dFrameHeight = gp.tileSize * 4;
			int textX = dFrameX + 20;
			int textY = dFrameY + gp.tileSize;
			g2.setFont(g2.getFont().deriveFont(15F));
			int itemIndex = getItemIndex(slotCol, slotRow);
			if(itemIndex < sprite.inventory.size())
			{
				drawSubscreen(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
				
				for(String line: sprite.inventory.get(itemIndex).desc.split("\n"))
				{
					g2.drawString(line, textX, textY);
					textY += 32;
				}
				
			}
		}
		
		
	}
	
	public int getItemIndex(int slotCol, int slotRow)
	{
		int itemIndex = slotCol + (slotRow*5);
		return itemIndex;
	}
	
	public void drawOptionScreen()
	{
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameX = gp.tileSize * 6;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize * 8;
		int frameHeight = gp.tileSize * 10;
		drawSubscreen(frameX, frameY, frameWidth, frameHeight);
		
		switch(subState)
		{
		case 0: option_top(frameX, frameY); break;
		case 1: break;
		case 2: option_control(frameX, frameY); break;
		case 3: option_quitGameConfirm(frameX, frameY); break;
		}
		
		gp.pI.enterPressed = false;
	}
	
	public void option_top(int frameX, int frameY)
	{
		int textX;
		int textY;
		
		// title
		String text = "Options";
		textX = getCenterString(text) + 100;
		textY = frameY + gp.tileSize;
		g2.drawString(text,  textX,  textY);
		
		// full screen
		textX = frameX + gp.tileSize;
		textY += gp.tileSize * 2;
		g2.setFont(g2.getFont().deriveFont(25F));
		g2.drawString("Full Screen", textX, textY);
		if(comNum == 0)
		{
			g2.drawString("-", textX - 25, textY);
			if(gp.pI.enterPressed == true)
			{
				if(gp.fullScreenOn == false)
				{
					gp.fullScreenOn = true;
				}
				else if(gp.fullScreenOn == true)
				{
					gp.fullScreenOn = false;
				}
			}
		}
		
		// ctrl
		textY += gp.tileSize;
		g2.drawString("CTRLS", textX, textY);
		if(comNum == 1)
		{
			g2.drawString("-", textX - 25, textY);
			if(gp.pI.enterPressed == true)
			{
				subState = 2;
				comNum = 0;
			}
		}
		
		// music
		textY += gp.tileSize;
		g2.drawString("Music", textX, textY);
		if(comNum == 2)
		{
			g2.drawString("-", textX - 25, textY);
		}
		
		// sfx
		textY += gp.tileSize;
		g2.drawString("SFX", textX, textY);
		if(comNum == 3)
		{
			g2.drawString("-", textX - 25, textY);
		}
		
		// quit
		textY += gp.tileSize;
		g2.drawString("Quit Game", textX, textY);
		if(comNum == 4)
		{
			g2.drawString("-", textX - 25, textY);
			if(gp.pI.enterPressed == true)
			{
				subState = 3;
				comNum = 0;
			}
		}
		
		// close
		textY += gp.tileSize * 2;
		g2.drawString("Close Menu", textX, textY);
		if(comNum == 5)
		{
			g2.drawString("-", textX - 25, textY);
			if(gp.pI.enterPressed == true)
			{
				gp.gameState = gp.playState;
				comNum = 0;
			}
		}
		
		// full screen box
		textX = frameX + gp.tileSize * 5 + 10;
		textY = frameY + gp.tileSize * 2 + 30;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, 24, 24);
		if(gp.fullScreenOn == true)
		{
			g2.fillRect(textX, textY, 24, 24);
		}
		
		// enter crtls
		textY += gp.tileSize;
		g2.drawString("", textX, textY);
		
		// music vol
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		int volumeWidth = 24 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		// sfx vol
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24 * gp.sound.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
	}
	
	public void option_control(int frameX, int frameY)
	{
		int textX;
		int textY;
		
		String text = "Control";
		textX = getCenterString(text) + 100;
		textY = frameY + gp.tileSize;
		g2.drawString(text,  textX,  textY);
		
		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(25F));
		g2.drawString("Move", textX, textY); textY += gp.tileSize;
		g2.drawString("Attack", textX, textY); textY += gp.tileSize;
		g2.drawString("Shoot", textX, textY); textY += gp.tileSize;
		g2.drawString("Inventory", textX, textY); textY += gp.tileSize;
		g2.drawString("Pause", textX, textY); textY += gp.tileSize;
		g2.drawString("Options", textX, textY); textY += gp.tileSize;
		
		textX = frameX + gp.tileSize * 6;
		textY = frameY + gp.tileSize * 2;
		g2.setFont(g2.getFont().deriveFont(25F));
		g2.drawString("WASD", textX, textY); textY += gp.tileSize;
		g2.drawString("O", textX, textY); textY += gp.tileSize;
		g2.drawString("P", textX, textY); textY += gp.tileSize;
		g2.drawString("I", textX, textY); textY += gp.tileSize;
		g2.drawString("ESC", textX, textY); textY += gp.tileSize;
		g2.drawString("M", textX, textY); textY += gp.tileSize;
		
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize * 9;
		g2.drawString("back", textX, textY);
		if(comNum == 0)
		{
			g2.drawString("-",  textX-25,  textY);
			if(gp.pI.enterPressed == true)
			{
				subState = 0;
				comNum = 3;
			}
		}
	}
	
	public void option_quitGameConfirm(int frameX, int frameY)
	{
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize * 3;
		
		currentTalk = "Quit the game and \nsell your soul to me?";
		
		for(String line: currentTalk.split("\n"))
		{
			g2.drawString(line,  textX,  textY);
			textY += 40;
		}
		
		String text = "Of course!";
		textX = getCenterString(text) + 100;
		textY += gp.tileSize * 3;
		g2.drawString(text,  textX,  textY);
		if(comNum == 0)
		{
			g2.drawString("-",  textX-25,  textY);
			if(gp.pI.enterPressed == true)
			{
				System.exit(0);
			}
		}
		
		text = "No way jose";
		textX = getCenterString(text) + 100;
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(comNum == 1)
		{
			g2.drawString("-",  textX-25,  textY);
			if(gp.pI.enterPressed == true)
			{
				subState = 0;
				comNum = 4;
			}
		}
	}
	
	public void drawDeadScreen()
	{
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0,  gp.screenWidth,  gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100f));
		
		text = "You died idiot!";
		g2.setColor(Color.black);
		x = getCenterString(text);
		y = gp.tileSize * 3;
		g2.drawString(text, x, y);
		
		g2.setColor(Color.white);
		g2.drawString(text,  x-4,  y-4);
		
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Rage Quit";
		x = getCenterString(text);
		y += gp.tileSize * 5;
		g2.drawString(text,  x,  y);
		g2.drawString("-",  x-40, y);
		
	}
	
	public void drawTradeScreen()
	{
		switch(subState) {
		case 0: tradeSelect(); break;
		case 1: tradeBuy(); break;
		case 2: tradeSell(); break;
		}
		gp.pI.enterPressed = false;
	}
	public void tradeSelect()
	{
		drawTalkScreen();
		
		int x = gp.tileSize * 1;
		int y = gp.tileSize * 1;
		int width = (int)(gp.tileSize * 3.5);
		int height = (int)(gp.tileSize * 3.5);
		drawSubscreen(x, y, width, height);
		
		x += gp.tileSize;
		y += gp.tileSize;
		g2.drawString("Buy", x, y);
		if(comNum == 0)
		{
			g2.drawString("-", x-24 , y);
			if(gp.pI.enterPressed == true)
			{
				subState = 1;
			}
		}
		y += gp.tileSize;
		
		g2.drawString("Sell", x, y);
		if(comNum == 1)
		{
			g2.drawString("-", x-24, y);
			if(gp.pI.enterPressed == true)
			{
				subState = 2;
			}
		}
		y += gp.tileSize;
		
		g2.drawString("Exit", x, y);
		if(comNum == 2)
		{
			g2.drawString("-", x-24, y);
			if(gp.pI.enterPressed == true)
			{
				comNum = 0;
				gp.gameState = gp.talkState;
				currentTalk = "Next time don't hold up the line.";
			}
		}
		y += gp.tileSize;
	}
	public void tradeBuy()
	{
		drawInventoryScreen(gp.billy, false);
		drawInventoryScreen(npc, true);
		
		int x = gp.tileSize*5;
		int y = gp.tileSize*10;
		int width = gp.tileSize*3;
		int height = gp.tileSize*2;
		drawSubscreen(x, y, width, height);
		g2.drawString("Cancel Trade", x+24, y+60);
		
		x = gp.tileSize*8;
		y = gp.tileSize*10;
		width = gp.tileSize*3;
		height = gp.tileSize*2;
		drawSubscreen(x, y, width, height);
		g2.drawString("Bug Count: " + gp.billy.bugsKilled, x+24, y+60);
		
		int itemIndex = getItemIndex(npcSlotCol, npcSlotRow);
		if(itemIndex < npc.inventory.size())
		{
			x = (int)(gp.tileSize*4.5);
			y = (int)(gp.tileSize*5.5);
			width = (int)(gp.tileSize*2);
			height = (int)(gp.tileSize*1.5);
			drawSubscreen(x, y, width, height);
			g2.drawString("Bugs:", x+10, y+45);
			
			int price = npc.inventory.get(itemIndex).price;
			g2.drawString("" + price, x+55, y+45);
			
			if(gp.pI.enterPressed == true)
			{
				if(npc.inventory.get(itemIndex).price > gp.billy.bugsKilled)
				{
					subState = 0;
					gp.gameState = gp.talkState;
					currentTalk = "More bug captures required.";
					drawTalkScreen();
				}
				else if(gp.billy.inventory.size() == gp.billy.inventorySize)
				{
					gp.gameState = gp.talkState;
					currentTalk = "Inventory is full.";
				}
				else
				{
					gp.billy.bugsKilled -= npc.inventory.get(itemIndex).price;
					gp.billy.inventory.add(npc.inventory.get(itemIndex));
				}
			}
		}
	}
	public void tradeSell()
	{
		drawInventoryScreen(gp.billy, true);
		
		int x;
		int y;
		int width;
		int height;
		
		x = gp.tileSize*5;
		y = gp.tileSize*10;
		width = gp.tileSize*3;
		height = gp.tileSize*2;
		drawSubscreen(x, y, width, height);
		g2.drawString("Cancel Trade", x+24, y+60);
		
		x = gp.tileSize*8;
		y = gp.tileSize*10;
		width = gp.tileSize*3;
		height = gp.tileSize*2;
		drawSubscreen(x, y, width, height);
		g2.drawString("Bug Count: " + gp.billy.bugsKilled, x+24, y+60);
		
		int itemIndex = getItemIndex(billySlotCol, billySlotRow);
		if(itemIndex < gp.billy.inventory.size())
		{
			x = (int)(gp.tileSize*10.5);
			y = (int)(gp.tileSize*5.5);
			width = (int)(gp.tileSize*2);
			height = (int)(gp.tileSize*1.5);
			drawSubscreen(x, y, width, height);
			g2.drawString("Bugs:", x+10, y+45);
			
			int price = gp.billy.inventory.get(itemIndex).price;
			g2.drawString("" + price, x+100, y+45);
			
			if(gp.pI.enterPressed == true)
			{
				if(gp.billy.inventory.get(itemIndex)== gp.billy.currentWeapon)
				{
					comNum = 0;
					subState = 0;
					gp.gameState = gp.talkState;
					currentTalk = "Can't sell equipped weapon.";
				}
				else
				{
					gp.billy.inventory.remove(itemIndex);
					gp.billy.bugsKilled += price;
				}
			}
		}
	}
	
	public void drawSubscreen(int x, int y, int width, int height)
	{
		g2.setColor(new Color (0, 0, 0, 200));
		g2.fillRoundRect(x, y, width, height, 20, 20);
		
		g2.setColor(new Color (50, 205, 50));
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 10, 10);
	}
	
	public int getCenterString(String string)
	{
		int length = (int)g2.getFontMetrics().getStringBounds(string, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
}
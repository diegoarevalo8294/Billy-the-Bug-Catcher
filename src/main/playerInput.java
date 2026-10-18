package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import sprites.Billy;

public class playerInput implements KeyListener{
	
    GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, attackPressed, projPressed, spacePressed;
	boolean showDebugScreen = false;
	
	public playerInput(GamePanel gp)
	{
		this.gp = gp;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int c = e.getKeyCode();
		
		if(gp.gameState == gp.startState) 
		{
			if(gp.ui.loreState == false)
			{
				if(c == KeyEvent.VK_W)
				{
					gp.ui.comNum--;
					if(gp.ui.comNum <0)
					{
						gp.ui.comNum = 1;
					}
				}
				if(c == KeyEvent.VK_S)
				{
					gp.ui.comNum++;
					if(gp.ui.comNum > 1)
					{
						gp.ui.comNum = 0;
					}
				}
				if(c == KeyEvent.VK_ENTER)
				{
					if(gp.ui.comNum == 0)
					{
						gp.ui.loreState = true;
					}
					if(gp.ui.comNum == 1)
					{
						System.exit(0);
					}
				}
			}
			
			else if(gp.ui.loreState == true)
			{
				if(c == KeyEvent.VK_ENTER)
				{
					gp.gameState = gp.playState;
					//gp.stopMusic();
					//gp.playMusic(1);
					gp.ui.loreState = false;
					
				}
			}
		}
		
		if(gp.gameState == gp.playState) 
		{
			if(c == KeyEvent.VK_W)
			{
				upPressed = true;
			}
			if(c == KeyEvent.VK_S)
			{
				downPressed = true;
			}
			if(c == KeyEvent.VK_A)
			{
				leftPressed = true;
			}
			if(c == KeyEvent.VK_D)
			{
				rightPressed = true;
			}
			if(c == KeyEvent.VK_SPACE)
			{
				spacePressed = true;
			}
			if(c == KeyEvent.VK_ESCAPE)
			{
				gp.gameState = gp.pauseState;
			}
			
			if(c == KeyEvent.VK_ENTER)
			{
				enterPressed = true;
			}
			
			if(c == KeyEvent.VK_O)
			{
				attackPressed = true;
			}
			
			if(c == KeyEvent.VK_R) 
			{
				gp.billy.wX = gp.tileSize * 22;
				gp.billy.wY = gp.tileSize * 42;
			}
			
			if(c == KeyEvent.VK_M)
			{
				gp.gameState = gp.optionState;
				
			}

			if (c == KeyEvent.VK_T)
			{
				if(showDebugScreen == false)
				{
					showDebugScreen = true;
				}
				else if(showDebugScreen == true)
				{
					showDebugScreen = false;
				}
			}
			
			if(c == KeyEvent.VK_H)
			{
				switch(gp.currentMap)
				{
				case 0: gp.tile.readMap("/overWorld/world01.txt", 0); break;
				case 1: gp.tile.readMap("overWorld/newtxt.txt", 1); break;
				}
			}
			
			if(c == KeyEvent.VK_I) 
			{
				gp.gameState = gp.stuffState;
			}
			
			if(c == KeyEvent.VK_P) 
			{
				projPressed = true;
			}
		}
		
		else if(gp.gameState == gp.pauseState) 
		{
			if(c == KeyEvent.VK_ESCAPE)
			{
				gp.gameState = gp.playState;
			}
		}
		
		else if(gp.gameState == gp.talkState)
		{
			if(c == KeyEvent.VK_SPACE)
			{
				gp.gameState = gp.playState;
			}
		}
		
		else if(gp.gameState == gp.optionState)
		{
			if(c == KeyEvent.VK_M)
			{
				gp.gameState = gp.playState;
			}
			
			if(c == KeyEvent.VK_ENTER) 
			{
				enterPressed = true;
			}
			
			int maxComNum = 0;
			switch(gp.ui.subState) {
			case 0: maxComNum = 5; break;
			case 3: maxComNum = 1; break;
			}
			
			if(c == KeyEvent.VK_W) 
			{
				gp.ui.comNum--;
				//gp.playSound(9);
				if(gp.ui.comNum < 0)
				{
					gp.ui.comNum = maxComNum;
				}
			}
			
			if(c == KeyEvent.VK_S)
			{
				gp.ui.comNum++;
				//gp.playSound(9);
				if(gp.ui.comNum > maxComNum)
				{
					gp.ui.comNum = 0;
				}
					
			}
			
			if(c == KeyEvent.VK_A)
			{
				if(gp.ui.subState == 0)
				{
					if(gp.ui.comNum == 2 && gp.music.volumeScale > 0)
					{
						gp.music.volumeScale--;
						gp.music.checkVolume();
						//gp.playSound(9);
					}
					if(gp.ui.comNum == 3 && gp.sound.volumeScale > 0)
					{
						gp.sound.volumeScale--;
						//gp.playSound(9);
					}
				}
			}
			if(c == KeyEvent.VK_D)
			{
				if(gp.ui.subState == 0)
				{
					if(gp.ui.comNum == 2 && gp.music.volumeScale < 5)
					{
						gp.music.volumeScale++;
						gp.music.checkVolume();
						//gp.playSound(9);
					}
					if(gp.ui.comNum == 3 && gp.sound.volumeScale < 5)
					{
						gp.sound.volumeScale++;
						//gp.playSound(9);
					}
				}
			}
		}
		
		else if(gp.gameState == gp.deadState)
		{
	
			
			if(c == KeyEvent.VK_ENTER)
			{
				System.exit(0);
			}
		}
		
		else if(gp.gameState == gp.stuffState) 
		{
			if(c == KeyEvent.VK_I)
			{
				gp.gameState = gp.playState;
			}
			
			if(c == KeyEvent.VK_ENTER) 
			{
				gp.billy.selectItem();
				//gp.playSound(9);
			}
			billyInventory(c);
		}
		
		else if(gp.gameState == gp.tradeState)
		{
			if(c == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
			
			if(gp.ui.subState == 0)
			{
				if(c == KeyEvent.VK_W)
				{
					gp.ui.comNum--;
					if(gp.ui.comNum < 0)
					{
						gp.ui.comNum = 2;
					}
					// gp.playSound();
				}
				if(c == KeyEvent.VK_S)
				{
					gp.ui.comNum++;
					if(gp.ui.comNum > 2)
					{
						gp.ui.comNum = 0;
					}
					// gp.playSound();
				}
			}
			
			if(gp.ui.subState == 1)
			{
				npcInventory(c);
				if(c == KeyEvent.VK_ESCAPE)
				{
					gp.ui.subState = 0;
				}
			}
			
			if(gp.ui.subState == 2)
			{
				billyInventory(c);
				if(c == KeyEvent.VK_ESCAPE)
				{
					gp.ui.subState = 0;
				}
			}
		}
		
	}
	
	public void billyInventory(int code)
	{
		if(code == KeyEvent.VK_W)
		{
			if(gp.ui.billySlotRow != 0)
			{
				gp.ui.billySlotRow--;
				//gp.playSound(8);
			}
		}
		if(code == KeyEvent.VK_A)
		{
			if(gp.ui.billySlotCol != 0)
			{
				gp.ui.billySlotCol--;
				//gp.playSound(8);
			}
		}
		if(code == KeyEvent.VK_S)
		{
			if(gp.ui.billySlotRow != 3)
			{
				gp.ui.billySlotRow++;
				//gp.playSound(8);
			}
		}
		if(code == KeyEvent.VK_D)
		{
			if(gp.ui.billySlotCol != 4)
			{
				gp.ui.billySlotCol++;
				//gp.playSound(8);
			}
		}
	}
	
	public void npcInventory(int code)
	{
		if(code == KeyEvent.VK_W)
		{
			if(gp.ui.npcSlotRow != 0)
			{
				gp.ui.npcSlotRow--;
				//gp.playSound(8);
			}
		}
		if(code == KeyEvent.VK_A)
		{
			if(gp.ui.npcSlotCol != 0)
			{
				gp.ui.npcSlotCol--;
				//gp.playSound(8);
			}
		}
		if(code == KeyEvent.VK_S)
		{
			if(gp.ui.npcSlotRow != 3)
			{
				gp.ui.npcSlotRow++;
				//gp.playSound(8);
			}
		}
		if(code == KeyEvent.VK_D)
		{
			if(gp.ui.npcSlotCol != 4)
			{
				gp.ui.npcSlotCol++;
				//gp.playSound(8);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int  c = e.getKeyCode();
		
		if(c == KeyEvent.VK_W)
		{
			upPressed = false;
		}
		if(c == KeyEvent.VK_S)
		{
			downPressed = false;
		}
		if(c == KeyEvent.VK_A)
		{
			leftPressed = false;
		}
		if(c == KeyEvent.VK_D)
		{
			rightPressed = false;
		}
		if(c == KeyEvent.VK_ENTER) 
		{
			enterPressed = false;
		}
		if(c == KeyEvent.VK_O)
		{
			attackPressed = false;
		}
		if(c == KeyEvent.VK_P) 
		{
			projPressed = false;
		}
		if(c == KeyEvent.VK_SPACE) 
		{
			spacePressed = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
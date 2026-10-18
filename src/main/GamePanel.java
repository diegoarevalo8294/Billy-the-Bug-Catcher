package main;

import java.awt.Color;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import sprites.Billy;
import sprites.Sprite;
import tile.tileM;
import tileInt.Interactive;

public class GamePanel extends JPanel implements Runnable{
	
	
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; 
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow; 
	public final int maxMap = 10;
	public int currentMap = 0;
	
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	public boolean fullScreenOn = false;
	
	int FPS = 60;
	
	// tile and player input
	tileM tile = new tileM(this);
	public playerInput pI = new playerInput(this);
	
	// ui and event input
	public UI ui = new UI(this);
	public EventInput iInput = new EventInput(this);
	
	Thread gameThread;
	
	// collision checker
	public ColDetection cChecker = new ColDetection(this);
	
	// sprites (player, npc, enemies)
	public Billy billy = new Billy(this, pI);
	public Sprite npc[][] = new Sprite[maxMap][2];
	public Sprite enemy[][] = new Sprite[maxMap][50];
	public Interactive iTile[][] = new Interactive[maxMap][20];
	
	// item and sprite placer
	public Sprite item[][] = new Sprite[maxMap][10];
	public spritePlacer sPlacer = new spritePlacer(this);
	public ArrayList<Sprite> spriteList = new ArrayList<>();
	public ArrayList<Sprite> particleList = new ArrayList<>();
	public ArrayList<Sprite> projList = new ArrayList<>();
	
	
	public int gameState;
	public final int playState = 0;
	public final int pauseState = 1;
	public final int talkState = 2;
	public final  int startState = 3;
	public final int stuffState = 4;
	public final int optionState = 5;
	public final int loreState = 6;
	public final int deadState = 7;
	public final int tradeState = 8;
	
	
	Sounds music = new Sounds();
	Sounds sound = new Sounds();
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(new Color(70, 120, 80));
		this.setDoubleBuffered(true);
		this.addKeyListener(pI);
		this.setFocusable(true);
		
	}
	
	public void setGame()
	{
		sPlacer.setItem();
		sPlacer.setNPC();
		sPlacer.setEnemy();
		sPlacer.setIntTiles();
		//playMusic(0);
		gameState = startState;
		
		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D)tempScreen.getGraphics();
		setFullScreen();
		
	}
	
	public void setFullScreen()
	{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.window);
		
		screenWidth2 = Main.window.getWidth();
		screenHeight2 = Main.window.getHeight();
	}
	
	public void startGameThread() {
		
			gameThread = new Thread(this);
			gameThread.start();
		}

	@Override
	public void run() {
	
		double drawInt = 1000000000/FPS;
		double delta = 0;
		long last = System.nanoTime();
		long current;
		long timer = 0;
		int drawC = 0;
	
		while(gameThread != null)
		{
			current = System.nanoTime();
			delta += (current - last) / drawInt;
			timer += (current - last);
			last = current;
			if (delta >= 1)
			{
				update();
				drawTempScreen();
				drawScreen();
				delta--;
				drawC++;
			}
		
			if (timer >= 1000000000)
			{
				System.out.println(drawC);
				drawC = 0;
				timer = 0;
			}
		}
	}
	
	
	public void playMusic(int i)
	{
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic()
	{
		music.stop();
	}
	
	public void playSound(int i)
	{
		sound.setFile(i);
		sound.play();
	}
	
	
	public void update()
	{
		if(gameState == playState)
		{
			
			billy.update();
			
			for(int i = 0; i < npc[1].length; i++)
			{
				if(npc[currentMap][i] != null)
				{
					npc[currentMap][i].update();
				}
			}
			
			for(int i = 0; i < enemy[1].length; i++)
			{
				if(enemy[currentMap][i] != null)
				{
					if(enemy[currentMap][i].alive == true && enemy[currentMap][i].dying == false)
					{
						enemy[currentMap][i].update();
					}
					if(enemy[currentMap][i].alive == false)
					{
						enemy[currentMap][i] = null;
					}
				}
			}
			
			for(int i = 0; i < projList.size(); i++)
			{
				if(projList.get(i) != null)
				{
					if(projList.get(i).alive == true)
					{
						projList.get(i).update();
					}
					if(projList.get(i).alive == false)
					{
						projList.remove(i);
					}
				}
			}

			
			for(int i = 0; i < iTile[1].length; i++)
			{
				if(iTile[currentMap][i] != null)
				{
					iTile[currentMap][i].update();
				}
			}
			
			for(int i = 0; i < particleList.size(); i++)
			{
				if(particleList.get(i) != null)
				{
					if(particleList.get(i).alive == true)
					{
						particleList.get(i).update();
					}
					if(particleList.get(i).alive == false)
					{
						particleList.remove(i);
					}
				}
			}
		}
		
		
		
		if(gameState == pauseState)
		{
			
		}
	}
	
	public void drawTempScreen()
	{
		long drawStart = 0;
		if(pI.showDebugScreen == true)
		{
			drawStart = System.nanoTime();
		}
		
		if(gameState == startState)
		{
			ui.draw(g2);
		}

		else
		{
			tile.draw(g2);
			
			for(int i = 0; i < iTile[1].length; i++)
			{
				if(iTile[currentMap][i] != null)
				{
					iTile[currentMap][i].draw(g2);
				}
			}
			
			
			for(int i = 0; i < item[1].length; i++)
			{
				if(item[currentMap][i] != null)
				{
					spriteList.add(item[currentMap][i]);
				}
			}
			
			spriteList.add(billy);
			
			for(int i = 0; i < npc[1].length; i++)
			{
				if(npc[currentMap][i] != null)
				{
					spriteList.add(npc[currentMap][i]);
				}
			}
			
			
			for(int i = 0; i < enemy[1].length; i++)
			{
				if(enemy[currentMap][i] != null)
				{
					spriteList.add(enemy[currentMap][i]);
				}
			}
		
			
			for(int i = 0; i < projList.size(); i++)
			{
				if(projList.get(i) != null)
				{
					spriteList.add(projList.get(i));
				}
			}
			
			for(int i = 0; i < particleList.size(); i++)
			{
				if(particleList.get(i) != null)
				{
					spriteList.add(particleList.get(i));
				}
			}
			
			Collections.sort(spriteList, new Comparator<Sprite>() {

				@Override
				public int compare(Sprite s1, Sprite s2) {
					int result = Integer.compare(s1.wY,  s1.wY);
					return result;
				}
				
			});
			
			
			for(int i = 0; i < spriteList.size(); i++)
			{
				spriteList.get(i).draw(g2);
			}
			
			for(int i = 0; i < spriteList.size(); i++)
			{
				spriteList.remove(i);
			}
			
			
			
			ui.draw(g2);
		}
		
		if(pI.showDebugScreen == true)
		{
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			
			g2.setFont(new Font("Arial", Font.PLAIN, 25));
			g2.setColor(Color.white);
			
			int x = 10;
			int y = 50;
			int lineHeight = 20;
			
			g2.drawString("worldX" + billy.wX, x, y); 
			y += lineHeight;
			g2.drawString("worldY" + billy.wY, x, y);
			y += lineHeight;
			g2.drawString("col" + (billy.wX + billy.solidArea.x)/tileSize, x, y);
			y += lineHeight;
			g2.drawString("row" + (billy.wY + billy.solidArea.y)/tileSize, x, y);
			y += lineHeight;
			g2.drawString("Draw Time: " + passed, 10, 200);
		}
	}
	
	public void drawScreen()
	{
		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g.dispose();
	}

}
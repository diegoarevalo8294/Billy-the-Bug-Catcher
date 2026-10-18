package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class tileM {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapNum[][][];
	
	public tileM(GamePanel gp)
	{
		this.gp = gp;
		tile = new Tile[10];
		
		mapNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		drawTiles();
		readMap("/overWorld/world01.txt", 0);
		readMap("/overWorld/newtxt.txt", 1);
	}
	
	public void drawTiles()
	{
		setup(0, "grass", false);
		setup(1, "wall", true);
		setup(2, "water", true);
		setup(3, "grass n tree", true);
		setup(4, "floor", false);
		setup(5, "door", false);
		setup(6, "rug",  false);
	}
	
	public void setup(int index, String imagePath, boolean collision)
	{
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readMap(String fileReader, int map)
	{
		try
		{
			
			InputStream iS = getClass().getResourceAsStream(fileReader);
			BufferedReader bR = new BufferedReader(new InputStreamReader(iS));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow)
			{
				
				String line = bR.readLine();
				while(col < gp.maxWorldCol)
				{
					String numbers[] = line.split(" ");
					int intNum = Integer.parseInt(numbers[col]);
					mapNum[map][col][row] = intNum;
					col++;
				}
				if(col == gp.maxWorldCol)
				{
					col = 0;
					row++;
				}
			}
			bR.close();
			
		}catch(Exception e)
		{
			
		}
	}
	public void draw(Graphics2D g2)
	{
		int col = 0;
		int row = 0;

		
		while(col < gp.maxWorldCol && row < gp.maxWorldRow)
		{
			int tileNum = mapNum[gp.currentMap][col][row];
			
			int wX = col * gp.tileSize;
			int wY = row * gp.tileSize;
			int sX = wX - gp.billy.wX + gp.billy.sX;
			int sY = wY - gp.billy.wY + gp.billy.sY;
			
			if(wX + gp.tileSize> gp.billy.wX - gp.billy.sX &&
			   wX - gp.tileSize< gp.billy.wX + gp.billy.sX &&
			   wY + gp.tileSize> gp.billy.wY - gp.billy.sY &&
			   wY - gp.tileSize< gp.billy.wY + gp.billy.sY)
			{
				g2.drawImage(tile[tileNum].image, sX, sY, null);
			}
			
			
			col++;
			
			if(col == gp.maxWorldCol)
			{
				col = 0;
				row++;
			}
		}
	}
}
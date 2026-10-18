package main;

import enemy.Enemy_Bee;
import enemy.Enemy_Dragonfly;
import enemy.Enemy_GiantWeta;
import enemy.Enemy_Ladybug;
import item.iAxe;
import item.iBottle;
import item.iDoor;
import item.iKey;
import item.iMilk;
import item.iPizza;
import sprites.NPC_Hopper;
import sprites.NPC_Roach;
import tileInt.Int_Tree;

public class spritePlacer {
	
	GamePanel gp;
	
	public spritePlacer(GamePanel gp)
	{
		this.gp = gp;
	}
	
	public void setItem()
	{
		int mapNum = 0;
		
		gp.item[mapNum][0] = new iAxe(gp);
		gp.item[mapNum][0].wX = gp.tileSize * 27;
		gp.item[mapNum][0].wY = gp.tileSize * 43;
		
		
		gp.item[mapNum][1] = new iPizza(gp);
		gp.item[mapNum][1].wX = gp.tileSize * 23;
		gp.item[mapNum][1].wY = gp.tileSize * 43;
		
		gp.item[mapNum][2] = new iBottle(gp);
		gp.item[mapNum][2].wX = gp.tileSize * 25;
		gp.item[mapNum][2].wY = gp.tileSize * 43;
		
		gp.item[mapNum][3] = new iMilk(gp);
		gp.item[mapNum][3].wX = gp.tileSize * 20;
		gp.item[mapNum][3].wY = gp.tileSize * 43;
		
	}
	
	public void setIntTiles()
	{
		
		int mapNum = 0;
		
		gp.iTile[mapNum][0] = new Int_Tree(gp);
		gp.iTile[mapNum][0].wX = gp.tileSize * 42;
		gp.iTile[mapNum][0].wY = gp.tileSize * 32;
		
		gp.iTile[mapNum][1] = new Int_Tree(gp);
		gp.iTile[mapNum][1].wX = gp.tileSize * 43;
		gp.iTile[mapNum][1].wY = gp.tileSize * 32;
		
		gp.iTile[mapNum][2] = new Int_Tree(gp);
		gp.iTile[mapNum][2].wX = gp.tileSize * 44;
		gp.iTile[mapNum][2].wY = gp.tileSize * 32;
		
		gp.iTile[mapNum][3] = new Int_Tree(gp);
		gp.iTile[mapNum][3].wX = gp.tileSize * 45;
		gp.iTile[mapNum][3].wY = gp.tileSize * 32;
		
		gp.iTile[mapNum][4] = new Int_Tree(gp);
		gp.iTile[mapNum][4].wX = gp.tileSize * 46;
		gp.iTile[mapNum][4].wY = gp.tileSize * 32;
		
		gp.iTile[mapNum][5] = new Int_Tree(gp);
		gp.iTile[mapNum][5].wX = gp.tileSize * 47;
		gp.iTile[mapNum][5].wY = gp.tileSize * 32;
	}
	
	public void setNPC()
	{
		int mapNum = 0;
		
		gp.npc[mapNum][0] = new NPC_Hopper(gp);
		gp.npc[mapNum][0].wX = gp.tileSize * 19;
		gp.npc[mapNum][0].wY = gp.tileSize * 40;
		
		gp.npc[mapNum][1] = new NPC_Roach(gp);
		gp.npc[mapNum][1].wX = gp.tileSize * 30;
		gp.npc[mapNum][1].wY = gp.tileSize * 5;
	}
	
	public void setEnemy()
	{
		int mapNum = 0;
		
		gp.enemy[mapNum][0] = new Enemy_Ladybug(gp);
		gp.enemy[mapNum][0].wX = gp.tileSize * 22;
		gp.enemy[mapNum][0].wY = gp.tileSize * 25;
		
		gp.enemy[mapNum][1] = new Enemy_Ladybug(gp);
		gp.enemy[mapNum][1].wX = gp.tileSize * 25;
		gp.enemy[mapNum][1].wY = gp.tileSize * 26;
		
		gp.enemy[mapNum][2] = new Enemy_Ladybug(gp);
		gp.enemy[mapNum][2].wX = gp.tileSize * 18;
		gp.enemy[mapNum][2].wY = gp.tileSize * 29;
		
		gp.enemy[mapNum][2] = new Enemy_Bee(gp);
		gp.enemy[mapNum][2].wX = gp.tileSize * 18;
		gp.enemy[mapNum][2].wY = gp.tileSize * 23;
		
		gp.enemy[mapNum][3] = new Enemy_Bee(gp);
		gp.enemy[mapNum][3].wX = gp.tileSize * 29;
		gp.enemy[mapNum][3].wY = gp.tileSize * 32;
		
		gp.enemy[mapNum][4] = new Enemy_Bee(gp);
		gp.enemy[mapNum][4].wX = gp.tileSize * 34;
		gp.enemy[mapNum][4].wY = gp.tileSize * 25;
		
		gp.enemy[mapNum][14] = new Enemy_GiantWeta(gp);
		gp.enemy[mapNum][14].wX = gp.tileSize * 16;
		gp.enemy[mapNum][14].wY = gp.tileSize * 9;
		
		gp.enemy[mapNum][15] = new Enemy_Bee(gp);
		gp.enemy[mapNum][15].wX = gp.tileSize * 36;
		gp.enemy[mapNum][15].wY = gp.tileSize * 16;
		
		gp.enemy[mapNum][16] = new Enemy_Ladybug(gp);
		gp.enemy[mapNum][16].wX = gp.tileSize * 28;
		gp.enemy[mapNum][16].wY = gp.tileSize * 19;
		
		gp.enemy[mapNum][17] = new Enemy_Bee(gp);
		gp.enemy[mapNum][17].wX = gp.tileSize * 11;
		gp.enemy[mapNum][17].wY = gp.tileSize * 26;
		
		gp.enemy[mapNum][18] = new Enemy_Ladybug(gp);
		gp.enemy[mapNum][18].wX = gp.tileSize * 13;
		gp.enemy[mapNum][18].wY = gp.tileSize * 43;
		
		gp.enemy[mapNum][19] = new Enemy_Bee(gp);
		gp.enemy[mapNum][19].wX = gp.tileSize * 24;
		gp.enemy[mapNum][19].wY = gp.tileSize * 19;
		
		gp.enemy[mapNum][20] = new Enemy_Ladybug(gp);
		gp.enemy[mapNum][20].wX = gp.tileSize * 26;
		gp.enemy[mapNum][20].wY = gp.tileSize * 8;
		
		gp.enemy[mapNum][21] = new Enemy_Bee(gp);
		gp.enemy[mapNum][21].wX = gp.tileSize * 40;
		gp.enemy[mapNum][21].wY = gp.tileSize * 15;
		
		gp.enemy[mapNum][22] = new Enemy_Ladybug(gp);
		gp.enemy[mapNum][22].wX = gp.tileSize * 38;
		gp.enemy[mapNum][22].wY = gp.tileSize * 36;
		
		gp.enemy[mapNum][23] = new Enemy_Bee(gp);
		gp.enemy[mapNum][23].wX = gp.tileSize * 36;
		gp.enemy[mapNum][23].wY = gp.tileSize * 31;
		
		mapNum += 1;
		
		gp.enemy[mapNum][5] = new Enemy_Dragonfly(gp);
		gp.enemy[mapNum][5].wX = gp.tileSize * 20;
		gp.enemy[mapNum][5].wY = gp.tileSize * 25;
		
		gp.enemy[mapNum][6] = new Enemy_Dragonfly(gp);
		gp.enemy[mapNum][6].wX = gp.tileSize * 25;
		gp.enemy[mapNum][6].wY = gp.tileSize * 20;
		
		gp.enemy[mapNum][7] = new Enemy_Dragonfly(gp);
		gp.enemy[mapNum][7].wX = gp.tileSize * 26;
		gp.enemy[mapNum][7].wY = gp.tileSize * 22;
		
		gp.enemy[mapNum][8] = new Enemy_Dragonfly(gp);
		gp.enemy[mapNum][8].wX = gp.tileSize * 27;
		gp.enemy[mapNum][8].wY = gp.tileSize * 23;
		
		gp.enemy[mapNum][9] = new Enemy_Dragonfly(gp);
		gp.enemy[mapNum][9].wX = gp.tileSize * 24;
		gp.enemy[mapNum][9].wY = gp.tileSize * 26;
		
		gp.enemy[mapNum][10] = new Enemy_Dragonfly(gp);
		gp.enemy[mapNum][10].wX = gp.tileSize * 28;
		gp.enemy[mapNum][10].wY = gp.tileSize * 23;
		
		gp.enemy[mapNum][11] = new Enemy_Dragonfly(gp);
		gp.enemy[mapNum][11].wX = gp.tileSize * 27;
		gp.enemy[mapNum][11].wY = gp.tileSize * 31;
		
		gp.enemy[mapNum][12] = new Enemy_Dragonfly(gp);
		gp.enemy[mapNum][12].wX = gp.tileSize * 20;
		gp.enemy[mapNum][12].wY = gp.tileSize * 31;
		
		gp.enemy[mapNum][12] = new Enemy_Dragonfly(gp);
		gp.enemy[mapNum][12].wX = gp.tileSize * 29;
		gp.enemy[mapNum][12].wY = gp.tileSize * 23;
		
		gp.enemy[mapNum][13] = new Enemy_Dragonfly(gp);
		gp.enemy[mapNum][13].wX = gp.tileSize * 27;
		gp.enemy[mapNum][13].wY = gp.tileSize * 24;
		
		gp.enemy[mapNum][24] = new Enemy_Dragonfly(gp);
		gp.enemy[mapNum][24].wX = gp.tileSize * 27;
		gp.enemy[mapNum][24].wY = gp.tileSize * 28;
		
		gp.enemy[mapNum][25] = new Enemy_Dragonfly(gp);
		gp.enemy[mapNum][25].wX = gp.tileSize * 27;
		gp.enemy[mapNum][25].wY = gp.tileSize * 32;
	}
	
}
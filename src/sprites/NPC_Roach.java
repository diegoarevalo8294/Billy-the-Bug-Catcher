package sprites;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import item.iAxe;
import item.iBottle;
import item.iMilk;
import item.iPizza;
import main.GamePanel;

public class NPC_Roach extends Sprite{

	public NPC_Roach(GamePanel gp)
	{
		super(gp);
		
		direction = "front";
		name = "Roach";
		
		drawNPC();
		setTalk();
		setItems();
	}
	public void drawNPC()
	{
		back1 = setup("/npc/roach");
		back2 = setup("/npc/roach");
		front1 = setup("/npc/roach");
		front2 = setup("/npc/roach");
		left1 = setup("/npc/roach");
		left2 = setup("/npc/roach");
		right1 = setup("/npc/roach");
		right2 = setup("/npc/roach");
	}
	
	public void setTalk()
	{
		talks[0] = "Make it quick, I don't got all day.";

	}

	
	public void speak()
	{
		super.speak();
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
		
		if(talks[talkIndex] == null)
		{
			talkIndex = 0;
		}
		gp.ui.currentTalk = talks[talkIndex];
		talkIndex++;
		
	}
	
	public void setItems()
	{
		inventory.add(new iPizza(gp));
		inventory.add(new iMilk(gp));
		inventory.add(new iAxe(gp));
		inventory.add(new iBottle(gp));
	}
	
}

package sprites;

import java.awt.Font;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPC_Hopper extends Sprite {
	
	public NPC_Hopper(GamePanel gp)
	{
		super(gp);
		
		direction = "front";
		speed = 3;
		name = "Hopper";
		
		drawNPC();
		setTalk();
	}
	public void drawNPC()
	{
		back1 = setup("/npc/hopper");
		back2 = setup("/npc/hopper");
		front1 = setup("/npc/hopper");
		front2 = setup("/npc/hopper");
		left1 = setup("/npc/hopper");
		left2 = setup("/npc/hopper");
		right1 = setup("/npc/hopper");
		right2 = setup("/npc/hopper");
	}
	
	public void setTalk()
	{
		talks[0] = "How's it hangin' dude!";
		talks[1] = "The name's Hopper.";
		talks[2] = "You're Billy, right?";
		talks[3] = "Well, you've come to the right /nplace my amigo.";
		talks[4] = "All of the amazing bugs that are /nwaiting to be caught ahead...";
		talks[5] = "In the SAFARI ZONE!";
		talks[6] = "Also, I'm alright if you ever ask.";
		talks[7] = "I just drank a lot of coffee.";
		talks[8] = "Also, don't go into the other /nbuilding under any circumstance.";
		talks[9] = "The door should be locked anyway.";
	}
	
	public void setAction()
	{
		Random random = new Random();
		int i = random.nextInt(100)+1;
		
		if(i <= 25)
		{
			direction = "front";
		}
		if(i > 25 && i <= 50)
		{
			direction = "back";
		}
		if(i > 50 && i <= 75)
		{
			direction = "left";
		}
		if(i > 75 && i <= 100)
		{
			direction = "right";
		}
	}
	
	public void speak()
	{
		if(talks[talkIndex] == null)
		{
			talkIndex = 0;
		}
		gp.ui.currentTalk = talks[talkIndex];
		talkIndex++;
	}
	
}
package item;

import main.GamePanel;
import sprites.Sprite;

public class iBottle extends Sprite{

	GamePanel gp;
	public iBottle(GamePanel gp)
	{
		super(gp);
		
		this.gp = gp;
		
		type = 5;
		name = "Water Bottle";
		front1 = setup("/tiles/bottle");
		collision = false;
		desc = "[" + name + "]\nA Water Bottle. Increases your\nprojectile charges.";
		price = 2;
		
	}
	public void drin(Sprite sprite)
	{
		gp.gameState = gp.talkState;
		gp.ui.currentTalk = "Healthy and Hydrated.";
		
		sprite.saliva += 6;
		
		// playSound();
	}
}
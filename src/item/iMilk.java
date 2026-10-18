package item;

import main.GamePanel;
import sprites.Sprite;

public class iMilk extends Sprite{

	GamePanel gp;
	public iMilk(GamePanel gp)
	{
		super(gp);
		
		this.gp = gp;
		
		type = 5;
		name = "Milk";
		front1 = setup("/tiles/milk");
		collision = false;
		desc = "[" + name + "]\nGlass of milk. Increases your speed.";
		price = 2;
		
	}
	public void drink(Sprite sprite)
	{
		gp.gameState = gp.talkState;
		gp.ui.currentTalk = "Good milk";
		
		sprite.speed += 2;
		
		// playSound();
	}
}
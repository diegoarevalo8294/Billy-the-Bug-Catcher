package item;

import main.GamePanel;
import sprites.Sprite;

public class iPizza extends Sprite{

	GamePanel gp;
	public iPizza(GamePanel gp)
	{
		super(gp);
		
		this.gp = gp;
		
		type = 5;
		name = "Pizza";
		front1 = setup("/tiles/pizza");
		collision = false;
		desc = "[" + name + "]\nA Pizza. Heals you to your max\nhealth.";
		price = 1;
		
	}
	public void eat(Sprite sprite)
	{
		gp.gameState = gp.talkState;
		gp.ui.currentTalk = "Yummy pizza";
		
		sprite.life += 3;
		if(gp.billy.life > gp.billy.maxLife) {
			gp.billy.life = gp.billy.maxLife;
		}
		// playSound();
	}
}
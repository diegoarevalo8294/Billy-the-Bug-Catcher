package item;

import main.GamePanel;
import sprites.Sprite;

public class iAxe extends Sprite{

	public iAxe(GamePanel gp)
	{
		super(gp);
		
		type = 4;
		name = "Iron Axe";
		front1 = setup("/tiles/axe");
		collision = false;
		attackValue = 1;
		desc = "[" + name + "]\nAn Iron Axe. Used to cut breakable\ntrees.";
		attackArea.width = 35;
		attackArea.height = 35;
		price = 3;
	}
}
package item;

import main.GamePanel;
import sprites.Sprite;

public class iPunch extends Sprite{

	public iPunch(GamePanel gp) {
		super(gp);
		
		type = 3;
		name = "These Hands";
		front1 = setup("/billy/billyAtFront1");
		desc = "[" + name + "]\nGives you the ability to punch.";
		attackValue = 2;
		attackArea.width = 25;
		attackArea.height = 36;
	}

}

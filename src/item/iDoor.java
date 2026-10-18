package item;


import main.GamePanel;
import sprites.Sprite;

public class iDoor extends Sprite{
	public iDoor(GamePanel gp)
	{
		super(gp);
		name = "Door";
		front1 = setup("/tiles/do0or");
		collision = true;
		desc = "[" + name + "]\nWhy did you put the door in your\ninventory?";
	}
}

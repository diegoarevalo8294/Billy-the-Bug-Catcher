package item;


import main.GamePanel;
import sprites.Sprite;

public class iKey extends Sprite{
	public iKey(GamePanel gp)
	{
		super(gp);
		name = "Key";
		front1 = setup("/tiles/key");
		collision = false;
		desc = "[" + name + "]\nAn old key. Opens the door to the small\nbuilding in the safari";
	}
}
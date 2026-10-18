package tileInt;

import main.GamePanel;
import sprites.Sprite;

public class Interactive extends Sprite{

	GamePanel gp;
	public boolean destructible = false;
	
	public Interactive(GamePanel gp) {
		super(gp);
		this.gp = gp;
	}
	
	public boolean isRightItem(Sprite sprite)
	{
		boolean isRightItem = false;
		return isRightItem;
	}
	
	public void update()
	{
		if(iFrame == true)
		{
			iFrameCounter++;
			if(iFrameCounter > 30)
			{
				iFrame = false;
				iFrameCounter = 0;
			}
		}
	}

}
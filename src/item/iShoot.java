package item;

import main.GamePanel;
import sprites.Range;

public class iShoot extends Range{

	GamePanel gp;
	
	public iShoot(GamePanel gp) 
	{
		super(gp);
		this.gp = gp;
		
		name = "Shoot";
		speed = 10;
		maxLife = 80;
		life = maxLife;
		attack = 2;
		useCost = 1;
		alive = false;
		getImage();
	}
	
	public void getImage()
	{
		front1 = setup("/proj/spitFront1");
		front2 = setup("/proj/spitFront2");
		back1 = setup("/proj/spitBack1");
		back2 = setup("/proj/spitBack2");
		left1 = setup("/proj/spitLeft1");
		left2 = setup("/proj/spitLeft2");
		right1 = setup("/proj/spitRight1");
		right2 = setup("/proj/spitRight2");
		
	}
}
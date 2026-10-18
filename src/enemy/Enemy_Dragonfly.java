package enemy;


import java.util.Random;

import item.iShoot;
import main.GamePanel;
import sprites.Sprite;

public class Enemy_Dragonfly extends  Sprite{

	GamePanel gp;
	
	public Enemy_Dragonfly(GamePanel gp) {
		super(gp);
		
		type = 2;
		defaultSpeed = 4;
		speed = defaultSpeed;
		maxLife = 2;
		life = maxLife;
		direction = "front";
		projectile = new iShoot(gp);
		
		solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
        setAction();
	}
	
	public void getImage()
	{
		back1 = setup("/bugs/dragonflyBack");
		back2 = setup("/bugs/dragonflyBack");
		front1 = setup("/bugs/dragonflyFront");
		front2 = setup("/bugs/dragonflyFront");
		left1 = setup("/bugs/dragonflyLeft");
		left2 = setup("/bugs/dragonflyLeft");
		right1 = setup("/bugs/dragonflyRight");
		right2 = setup("/bugs/dragonflyRight");
	}
	public void setAction()
	{
		actionCounter++;
		
		if(actionCounter == 120)
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
			
			actionCounter = 0;
		}
		
		int i = new Random().nextInt(100)+1;
		if(i > 99 && projectile.alive == false && shootCounter == 30)
		{
			projectile.set(wX,  wY,  direction,  true,  this);
			gp.projList.add(projectile);
			shootCounter = 0;
		}
	}
}
	
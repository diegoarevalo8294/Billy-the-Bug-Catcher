package enemy;


import java.util.Random;

import item.iShoot;
import main.GamePanel;
import sprites.Sprite;

public class Enemy_Bee extends  Sprite{

	GamePanel gp;
	
	public Enemy_Bee(GamePanel gp) {
		super(gp);
		
		type = 2;
		speed = defaultSpeed;
		defaultSpeed = 2;
		maxLife = 3;
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
		back1 = setup("/bugs/beeBack");
		back2 = setup("/bugs/beeBack");
		front1 = setup("/bugs/beeFront");
		front2 = setup("/bugs/beeFront");
		left1 = setup("/bugs/beeLeft");
		left2 = setup("/bugs/beeLeft");
		right1 = setup("/bugs/beeRight");
		right2 = setup("/bugs/beeRight");
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
	
	public void damageReaction()
	{
		
	}
}
package enemy;


import java.util.Random;

import item.iShoot;
import main.GamePanel;
import sprites.Sprite;

public class Enemy_Ladybug extends Sprite{

	GamePanel gp;
	
	public Enemy_Ladybug(GamePanel gp) {
		super(gp);
		
		type = 2;
		speed = defaultSpeed;
		defaultSpeed = 1;
		maxLife = 4;
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
		back1 = setup("/bugs/bugBack");
		back2 = setup("/bugs/bugBack");
		front1 = setup("/bugs/bugFront");
		front2 = setup("/bugs/bugFront");
		left1 = setup("/bugs/bugLeft");
		left2 = setup("/bugs/bugLeft");
		right1 = setup("/bugs/bugRight");
		right2 = setup("/bugs/bugRight");
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
		
		if(i > 50 && projectile.alive == false && shootCounter == 30)
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
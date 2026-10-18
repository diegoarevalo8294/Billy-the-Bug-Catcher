package enemy;


import java.util.Random;

import item.iShoot;
import main.GamePanel;
import sprites.Sprite;

public class Enemy_GiantWeta extends Sprite{

	GamePanel gp;
	
	public Enemy_GiantWeta(GamePanel gp) {
		super(gp);
		
		type = 2;
		speed = defaultSpeed;
		defaultSpeed = 2;
		maxLife = 16;
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
		back1 = setup("/bugs/giantWetaBack");
		back2 = setup("/bugs/giantWetaBack");
		front1 = setup("/bugs/giantWetaFront");
		front2 = setup("/bugs/giantWetaFront");
		left1 = setup("/bugs/giantWetaLeft");
		left2 = setup("/bugs/giantWetaLeft");
		right1 = setup("/bugs/giantWetaRight");
		right2 = setup("/bugs/giantWetaRight");
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
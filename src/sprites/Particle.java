package sprites;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import main.GamePanel;

public class Particle extends Sprite{

	Sprite gen;
	Color color;
	int size;
	int xd;
	int yd;
	
	public Particle(GamePanel gp, Sprite gen, Color color, int size, int speed, int maxLife, int xd, int yd) {
		super(gp);
		
		this.gen = gen;
		this.color = color;
		this.size = size;
		this.speed = speed;
		this.maxLife = maxLife;
		this.xd = xd;
		this.yd = yd;
		life = maxLife;
		int offset = (gp.tileSize/2) - (size/2);
		wX = gen.wX + offset;
		wY = gen.wY + offset;
	}
	
	public void update()
	{
		life--;
		if(life < maxLife/3)
		{
			yd++;
		}
		wX += xd * speed;
		wY += yd * speed;
		if(life == 0)
		{
			alive = false;
		}
	}
	
	public void draw(Graphics2D g2)
	{
		int sX = wX - gp.billy.wX + gp.billy.sX;
		int sY = wY - gp.billy.wY + gp.billy.sY;
		
		g2.setColor(color);
		g2.fillRect(sX,  sY,  size,  size);
	}
}

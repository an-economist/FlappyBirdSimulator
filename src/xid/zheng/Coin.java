package xid.zheng;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

public class Coin {

	private int xHeight;
	private int yHeight;
	private int dx;
	private int radius;
	Image coin;
	
	public Coin(int xDistance){
		xHeight = xDistance;
		Random random = new Random();
		yHeight = random.nextInt(400) + radius;
		radius = 10;
		dx = -4;
		coin = Pictures.coin;
	}
	
	public void update(BouncyBirdTester testerClass, Bird firstBird){
		xHeight += dx;
		checkForCollision(testerClass, firstBird);
		if (xHeight < 0 - radius){
			Random random = new Random();
			xHeight = testerClass.getWidth() + random.nextInt(300);
			yHeight = random.nextInt(400) + radius;
		}
	}
	
	private void checkForCollision(BouncyBirdTester testerClass, Bird firstBird) {
		int birdX = firstBird.getX();
		int birdY = firstBird.getY();
		int birdR = firstBird.getRadius();
		int a = xHeight - birdX;
		int b2 = yHeight - birdY;
		int collide = radius + birdR;
		double c = Math.sqrt((double)(a*a) + (double)(b2*b2));
		
		if(c < collide){
			new Gui();
			Gui.bleep.play();
			performAction(testerClass);
			xHeight = -20;
			yHeight = -20;
		}
	}
	
	public void performAction(BouncyBirdTester testerClass) {
		testerClass.setScore(testerClass.getScore() + 500);
	}
	
	public void paint(Graphics g){
		g.drawImage(coin, xHeight - radius, yHeight - radius, Pictures.testClass);
	}
	
}

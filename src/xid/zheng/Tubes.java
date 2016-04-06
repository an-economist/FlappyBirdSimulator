package xid.zheng;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Random;

public class Tubes {

	private int dx;
	private int xHeight;
	private int yHeight;
	private int zHeight;
	private int width; 
	private int height;
	Image tubes;
	URL url;
	private boolean game_over = false;
	
	public boolean isGame_over() {
		return game_over;
	}
	
	public Tubes() {
		Random r = new Random();
		dx = -4;
		xHeight = 1250;
		zHeight = r.nextInt(400) + 200;
		yHeight = zHeight - 800;
		height = 600;
		width = 100;
		tubes = Pictures.tubes;
	}
	
	public Tubes(int xHeight, int yHeight, int zHeight) {
		dx = -4;
		this.xHeight = xHeight;
		this.yHeight = yHeight;
		this.zHeight = zHeight; 
		height = 600;
		width = 100;
		tubes = Pictures.tubes;
	}
	
	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}
	
	public void update(BouncyBirdTester testerClass, Bird bird){
		xHeight += dx; //x is the tube, adding dx which is negative puts the tube to the left
		checkForCollision(bird);
		if (xHeight < 0 - width){ //if x(tube) < 0 (-width of tube) means whole tube is to the left
			Random random = new Random(); 
			zHeight = random.nextInt(400) + 200;
			yHeight = zHeight - 800;
			xHeight = testerClass.getWidth();
		}
	}
	
	public void checkForCollision(Bird bird){ 
		int birdX = bird.getX();
		int birdY = bird.getY();
		int radius = bird.getRadius();
		
		if(birdX + radius > xHeight + dx && (birdY + radius > zHeight && birdX + radius < xHeight + dx + 120)){
			game_over = true;
		}else if(birdX + radius > xHeight + dx && (birdY - radius < yHeight + 600 && birdX + radius < xHeight + dx + 120)){
			game_over = true;
		}else if(birdY + radius >= 600){
			game_over = true;
		}
	}
	
	public void paint(Graphics g){
		g.drawImage(tubes, xHeight, yHeight, Pictures.testClass);
		g.drawImage(tubes, xHeight, zHeight, Pictures.testClass);
	}
}

package xid.zheng;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

public class Bird {

	private int xHeight = 100;
	private int yHeight = 25;
	private int radius = 30;
	private double dx = 0;
	private double dy = 0;
	private double gravity = 15;
	private double gameDy = 0;
	private double dt = .2;
	private float frame = 0;
	Image bird;
	URL url;
	
	public Bird() {
		bird = Pictures.bird;
	}
	
	public Bird(int xHeight, int yHeight) {
		this.xHeight = xHeight;
		this.yHeight = yHeight;
		bird = Pictures.bird;
	}
	

	public void setGameDy(double gameDy) {
		this.gameDy = gameDy;
	}
	
	public int getX() {
		return xHeight;
	}

	public void setX(int xHeight) {
		this.xHeight = xHeight;
	}
	
	public int getY() {
		return yHeight;
	}

	public void setY(int yHeight) {
		this.yHeight = yHeight;
	}
	
	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}
	
	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}
	
	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}
	
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public void moveRight(){
		if (dx + 1 < 10){
			dx+=1;
		}
	}
	public void moveLeft(){
		if (dx - 1> -10){
			dx -= 1;
		}
	}
	public void moveUp(){
		dy = -50;
	}
	public void reset(){
		xHeight = 100;
		yHeight = 25;
	}
	
	public void update(BouncyBirdTester testerClass){
		int tester = (int)(frame + .1);
		if (tester < 7)
			frame += .1;
		else
			frame = 0;
		
		if(yHeight > testerClass.getHeight() - radius - 1){
			dy = gameDy; 
		} else {
			dy += gravity * dt; //Acceleration formula
			yHeight += dy*dt + .5*gravity*dt*dt; //Position formula
		}
	}
	
	public void paint(Graphics g){
		g.drawImage(bird, xHeight - radius , yHeight - radius , xHeight + radius, yHeight + 
				    radius, 60 * (int)frame, 0, 60 * (int)frame + 60, 60, Pictures.testClass);
	}
	
}

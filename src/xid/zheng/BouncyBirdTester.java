package xid.zheng;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class BouncyBirdTester extends Applet implements Runnable, KeyListener{

	private Image i;
	private Graphics doubleG;
	Tubes tube1, tube2;
	Bird firstBird;
	Coin coin;
	Menu menu;
	private int score;
	double SkyX = 0;
	double SkyDx = .5;
	URL url;
	Image Sky;
	public static boolean gameOver = false;
	
	public static enum STATE{
		GAME, MENU, 
	}

	public static STATE State = STATE.MENU;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void timeToRestart(){
		State = STATE.MENU;
	}
	
	public void init(){
		setSize(800,600);
		addKeyListener(this);
		this.addMouseListener(new Menu());
		try{
			url = getDocumentBase();
		} catch (Exception e){
			e.printStackTrace();
		}
		Sky = getImage(url, "images/Ocean.png");
		new Pictures(this);
	}
	
	public void start(){
		score = 0;
		firstBird = new Bird();
		tube1 = new Tubes();
		tube2 = new Tubes(800,-300,500);
		coin = new Coin(600);
		menu = new Menu();
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		while(true){

			if(State == STATE.MENU){

				if(SkyX > getWidth() * -1){
					SkyX -= SkyDx;
				} else {
					SkyX = 0;
				}
				repaint();
				try {
					Thread.sleep(17);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if(State == STATE.GAME){

				if(SkyX > getWidth() * -1){
					SkyX -= SkyDx;
				} else {
					SkyX = 0;
				}

				gameOver = tube1.isGame_over() || tube2.isGame_over();
				if(gameOver){
					firstBird.setY(580);
					if(Menu.mouseIn){ //Happens when someone presses the Play Again Button
						score = 0;
						firstBird = new Bird();
						tube1 = new Tubes();
						tube2 = new Tubes(800,-300,500);
						coin = new Coin(600);
						menu = new Menu();
						Menu.mouseIn = false;
						gameOver = false; 
					}
				}

				if(!gameOver){
					score++;
					firstBird.update(this);
					tube1.update(this, firstBird);
					tube2.update(this, firstBird);
					coin.update(this, firstBird);
				}
				repaint();
				try {
					Thread.sleep(17);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void stop(){
		
	}
	
	public void destory(){
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			firstBird.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			firstBird.moveRight();
			break;
		case KeyEvent.VK_SPACE:
			firstBird.moveUp();
			new Gui(this);
			Gui.bounce.play();
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void update(Graphics g) {
		//DoubleBuffering, prevents flickering
		if(i == null){
			i = createImage(this.getSize().width, this.getSize().height);
			doubleG = i.getGraphics();
		}
		doubleG.setColor(getBackground());
		doubleG.fillRect(0,0, getSize().width, getSize().height);
		doubleG.setColor(getForeground());
		paint(doubleG);

		g.drawImage(i, 0, 0, this);
	}

	public void paint(Graphics g) {
		g.drawImage(Sky, (int) SkyX, 0, this);
		g.drawImage(Sky, (int) SkyX + getWidth(), 0, this);

		if(State == STATE.GAME){
			coin.paint(g);
			tube1.paint(g);
			tube2.paint(g);
			firstBird.paint(g);	
			String s = Integer.toString(score);
			Font font = new Font("Serif", Font.BOLD, 32);
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.drawString(s, getWidth() - 400+2, 50+2);
			g.setColor(Color.RED);
			g.drawString(s, getWidth() - 400, 50);

			if(gameOver){
				g.setColor(Color.WHITE);
				g.drawString("Game Over", 310, 300);
				g.drawRect(310, 325, 160, 50);
				g.setColor(Color.RED);
				g.drawString("Play Again", 314, 360);

			}
		}else if(State == STATE.MENU){
			menu.paint(g);
		}
	}

}

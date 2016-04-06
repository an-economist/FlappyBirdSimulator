package xid.zheng;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu implements MouseListener{

	public Rectangle playButton = new Rectangle(345, 275, 100, 50);
	public Rectangle helpButton = new Rectangle(345, 350, 100, 50);
	public Rectangle quitButton = new Rectangle(345, 425, 100, 50);
	boolean gameOver = false;
	BouncyBirdTester testerClass = new BouncyBirdTester();
	public static boolean mouseIn = false; // New Testing
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
			Font font = new Font("arial", Font.BOLD,60);
			g.setFont(font);
			g.setColor(Color.pink);
			g.drawString("Bouncy Bird", 220, 200);
			
			Font font1 = new Font("arial", Font.BOLD, 40);
			
			g.setColor(Color.white);
			g.setFont(font1);
			
			g.drawString("Play", playButton.x + 10, playButton.y + 40);
			g2d.draw(playButton);
			g.drawString("Help", helpButton.x + 10, helpButton.y + 40);
			g2d.draw(helpButton);
			g.drawString("Quit", quitButton.x + 10, quitButton.y + 40);
			g2d.draw(quitButton);
	}
	
	
	public void update(Graphics g){
		Font font1 = new Font("arial", Font.BOLD, 40);
		g.setFont(font1);
		g.setColor(Color.red);
		g.drawString("Play", playButton.x + 10, playButton.y + 40);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//Play Button
		gameOver = BouncyBirdTester.gameOver;
		if (mx >345 && mx <445){
			if(my > 275 && my < 325){
				BouncyBirdTester.State = BouncyBirdTester.STATE.GAME;
			}
		}

		//This occurs when user presses the play again button
		//Checks to see the game is over, if user presses play again, invoke timetorestart
		if(gameOver){
			if(((mx > 310 && mx < 470))&&( my > 325 && my < 375)){
				mouseIn = true; // New Testing
				testerClass.timeToRestart();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

}

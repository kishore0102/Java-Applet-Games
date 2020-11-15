package Pong_V2;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class mainPong extends Applet implements Runnable, MouseMotionListener, MouseListener {
  private static final long serialVersionUID = 1L;
  
  final int width = 700;
  
  final int height = 500;
  
  boolean gameStarted;
  
  Thread thread;
  
  humanPaddle p1;
  
  aiPaddle p2;
  
  Ball b1;
  
  public void init() {
    this.gameStarted = false;
    resize(700, 500);
    addMouseMotionListener(this);
    addMouseListener(this);
    this.p1 = new humanPaddle(1);
    this.p2 = new aiPaddle(0);
    this.b1 = new Ball(350, 250);
    this.b1.score = 0;
    this.thread = new Thread(this);
    this.thread.start();
  }
  
  public void paint(Graphics g) {
    g.setColor(Color.black);
    g.fillRect(0, 0, 700, 500);
    this.p1.draw(g);
    this.p2.draw(g);
    this.b1.draw(g);
    g.setColor(Color.white);
    g.setFont(new Font("serif", 1, 20));
    g.drawString(this.b1.score, 350, 20);
    if (!this.gameStarted) {
      g.setColor(Color.white);
      g.setFont(new Font("serif", 1, 15));
      g.drawString("Tennis", 320, 120);
      g.drawString("Click mouse to begin...", 270, 150);
      this.b1.x = 350.0D;
      this.b1.y = 250.0D;
    } 
    if (this.b1.getX() < 10 || this.b1.getX() > 690)
      this.gameStarted = false; 
  }
  
  public void keyTyped(KeyEvent arg0) {}
  
  public void run() {
    while (true) {
      if (this.gameStarted) {
        this.b1.move();
        this.p2.sety(this.b1.getY());
        this.b1.checkBallCollision(this.p1, this.p2);
      } 
      repaint();
      try {
        Thread.sleep(10L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } 
    } 
  }
  
  public void mouseDragged(MouseEvent arg0) {}
  
  public void mouseMoved(MouseEvent e) {
    int y = e.getY();
    this.p1.sety(y);
  }
  
  public void mouseClicked(MouseEvent e) {
    if (!this.gameStarted) {
      this.gameStarted = true;
      this.b1.score = 0;
      if (this.b1.velX > 0.0D)
        this.b1.velX = -this.b1.velX; 
    } 
  }
  
  public void mouseEntered(MouseEvent arg0) {}
  
  public void mouseExited(MouseEvent arg0) {}
  
  public void mousePressed(MouseEvent arg0) {}
  
  public void mouseReleased(MouseEvent arg0) {}
}

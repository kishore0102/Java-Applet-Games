package Pong_V2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class mainPanel extends JPanel implements ActionListener, MouseMotionListener, MouseListener {
  private static final long serialVersionUID = 1L;
  
  humanPaddle p1;
  
  aiPaddle p2;
  
  Ball b1;
  
  boolean gameStarted;
  
  Thread thread;
  
  Timer timer;
  
  public mainPanel() {
    this.gameStarted = false;
    addMouseMotionListener(this);
    addMouseListener(this);
    this.p1 = new humanPaddle(1);
    this.p2 = new aiPaddle(0);
    this.b1 = new Ball(350, 250);
    this.timer = new Timer(8, this);
    this.timer.start();
  }
  
  public void paint(Graphics g) {
    g.setColor(Color.black);
    g.fillRect(0, 0, getWidth(), getHeight());
    this.p1.draw(g);
    this.p2.draw(g);
    this.b1.draw(g);
    g.setColor(Color.white);
    g.setFont(new Font("serif", 1, 20));
    g.drawString(this.b1.score, 347, 20);
    if (!this.gameStarted) {
      g.setColor(Color.white);
      g.setFont(new Font("serif", 1, 15));
      g.drawString("Tennis", 320, 120);
      g.drawString("Click mouse to begin...", 270, 150);
      this.b1.x = 350.0D;
      this.b1.y = 250.0D;
    } 
    if (this.b1.getX() < 10 || this.b1.getX() > 684)
      this.gameStarted = false; 
  }
  
  public void mouseClicked(MouseEvent e) {
    if (!this.gameStarted) {
      this.gameStarted = true;
      this.b1.score = 0;
      if (this.b1.velX > 0.0D)
        this.b1.velX = -this.b1.velX; 
    } 
  }
  
  public void mouseMoved(MouseEvent e) {
    if (this.gameStarted) {
      int x = e.getY();
      this.p1.sety(x);
    } 
  }
  
  public void actionPerformed(ActionEvent e) {
    if (this.gameStarted) {
      this.b1.move();
      this.p2.sety(this.b1.getY());
      this.b1.checkBallCollision(this.p1, this.p2);
    } 
    repaint();
  }
  
  public void mouseEntered(MouseEvent e) {}
  
  public void mouseExited(MouseEvent e) {}
  
  public void mousePressed(MouseEvent e) {}
  
  public void mouseReleased(MouseEvent e) {}
  
  public void mouseDragged(MouseEvent e) {}
}

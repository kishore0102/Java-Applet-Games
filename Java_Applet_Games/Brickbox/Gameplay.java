package BreakBox;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements MouseMotionListener, ActionListener {
  private static final long serialVersionUID = 1L;
  
  private boolean play = false;
  
  private int paddleX = 397;
  
  private int ballPosX = 250;
  
  private int ballPosY = 250;
  
  private int ballXdir = 1;
  
  private int ballYdir = 3;
  
  private Timer timer;
  
  private int delay = 8;
  
  private mapGenerator map;
  
  private int totalBricks;
  
  private int score = 0;
  
  public Gameplay() {
    JOptionPane.showMessageDialog(null, "1.Use mouse to control the paddle\n2.Dont touch the ball using paddle edges", "Info", 1);
    int row = 4;
    int col = 12;
    this.map = new mapGenerator(row, col);
    this.totalBricks = row * col;
    addMouseMotionListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    this.play = true;
    this.timer = new Timer(this.delay, this);
    this.timer.start();
  }
  
  public void paint(Graphics g) {
    g.setColor(Color.black);
    g.fillRect(1, 1, 794, 571);
    this.map.draw((Graphics2D)g);
    g.setColor(Color.green);
    g.fillRect(this.paddleX, 530, 100, 7);
    g.setColor(Color.yellow);
    g.fillOval(this.ballPosX, this.ballPosY, 20, 20);
    g.setColor(Color.white);
    g.setFont(new Font("serif", 1, 15));
    g.drawString(this.score, 600, 25);
    if (this.ballPosY > 574) {
      this.play = false;
      this.ballPosX = 250;
      this.ballPosY = 250;
      g.setColor(Color.red);
      g.setFont(new Font("serif", 1, 20));
      g.drawString("You LOST!!!    Score is " + this.score, 200, 250);
      g.setColor(Color.green);
      g.setFont(new Font("serif", 1, 20));
      g.drawString("Drag to restart", 250, 300);
    } 
    if (this.totalBricks < 1) {
      this.play = false;
      this.ballPosX = 250;
      this.ballPosY = 250;
      g.setColor(Color.red);
      g.setFont(new Font("serif", 1, 20));
      g.drawString("You WON!!!    Score is " + this.score, 200, 250);
      g.setColor(Color.green);
      g.setFont(new Font("serif", 1, 20));
      g.drawString("drag to restart", 250, 300);
    } 
    g.dispose();
  }
  
  public void actionPerformed(ActionEvent ex) {
    if (this.play) {
      this.timer.start();
      this.ballPosX += this.ballXdir;
      this.ballPosY += this.ballYdir;
      if (this.ballPosX > 774)
        this.ballXdir = -this.ballXdir; 
      if (this.ballPosX < 0)
        this.ballXdir = -this.ballXdir; 
      if (this.ballPosY < 0)
        this.ballYdir = -this.ballYdir; 
      if ((new Rectangle(this.ballPosX, this.ballPosY, 20, 20)).intersects(new Rectangle(this.paddleX, 530, 100, 7)))
        this.ballYdir = -this.ballYdir; 
      int i;
      label37: for (i = 0; i < this.map.map.length; i++) {
        for (int j = 0; j < (this.map.map[i]).length; j++) {
          if (this.map.map[i][j] > 0) {
            int brickX = j * this.map.brickWidth + 50;
            int brickY = i * this.map.brickHeight + 40;
            int brickWidth = this.map.brickWidth;
            int brickHeight = this.map.brickHeight;
            Rectangle Rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
            Rectangle brickBall = new Rectangle(this.ballPosX, this.ballPosY, 20, 20);
            Rectangle brickRect = Rect;
            if (brickBall.intersects(brickRect)) {
              this.map.setBrickValue(0, i, j);
              this.totalBricks--;
              this.score += 5;
              if (this.ballPosX + 19 <= brickRect.x || this.ballPosX + 1 >= brickRect.x + brickRect.width) {
                this.ballXdir = -this.ballXdir;
                break label37;
              } 
              this.ballYdir = -this.ballYdir;
              break label37;
            } 
          } 
        } 
      } 
      if (this.totalBricks < 1)
        this.play = false; 
      repaint();
    } 
  }
  
  public void mouseDragged(MouseEvent arg0) {
    if (!this.play) {
      this.play = true;
      mainClass.main(null);
    } 
  }
  
  public void mouseMoved(MouseEvent e) {
    this.paddleX = e.getX();
    if (this.paddleX > 694)
      this.paddleX = 694; 
    if (this.paddleX < 0)
      this.paddleX = 0; 
  }
}

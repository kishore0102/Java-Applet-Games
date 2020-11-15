package FlappyBird_V2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class panel extends JPanel implements ActionListener, MouseListener {
  private static final long serialVersionUID = 1L;
  
  int WIDTH;
  
  int HEIGHT;
  
  int ballx;
  
  int bally;
  
  Timer timer;
  
  boolean gameStarted;
  
  boolean gameOver;
  
  Rectangle[] column;
  
  Rectangle ball;
  
  int columnWidth;
  
  int columnHeight;
  
  int space;
  
  int random;
  
  int score;
  
  public panel(int width, int height) {
    this.WIDTH = width;
    this.HEIGHT = height;
    this.score = -1;
    this.ball = new Rectangle(150, 250, 20, 20);
    this.columnWidth = 75;
    double r = Math.random() * 200.0D;
    this.random = (int)r;
    this.columnHeight = 150;
    System.out.println(this.columnHeight);
    this.space = 125;
    this.column = new Rectangle[2];
    setColumn();
    this.gameStarted = false;
    this.gameOver = false;
    addMouseListener(this);
    this.timer = new Timer(8, this);
    this.timer.start();
  }
  
  public void setColumn() {
    this.score++;
    System.out.println(this.columnHeight);
    this.column[1] = new Rectangle(this.WIDTH + this.columnWidth, this.HEIGHT - this.columnHeight, this.columnWidth, this.columnHeight);
    this.column[0] = new Rectangle(this.WIDTH + this.columnWidth, 0, this.columnWidth, this.HEIGHT - this.columnHeight - this.space);
  }
  
  public void paint(Graphics g) {
    g.setColor(Color.pink);
    g.fillRect(0, 0, this.WIDTH, this.HEIGHT);
    if (this.ball.y > 571) {
      this.gameStarted = false;
      this.gameOver = true;
    } 
    if (this.ball.y < 0)
      this.ball.y = 0; 
    for (int i = 0; i < 2; i++)
      paintColumn(g, this.column[i]); 
    g.setColor(Color.gray.darker().darker());
    g.fillOval(this.ball.x, this.ball.y, this.ball.width, this.ball.height);
    if (!this.gameStarted) {
      g.setColor(Color.black);
      g.setFont(new Font("serif", 1, 15));
      g.drawString("Click to play...", 320, 120);
    } 
    if (this.gameOver) {
      g.setColor(Color.red);
      g.setFont(new Font("serif", 1, 15));
      g.drawString("Click to play...", 320, 120);
    } 
    g.setColor(Color.gray.darker().darker());
    g.setFont(new Font("serif", 1, 30));
    g.drawString(this.score, 530, 40);
  }
  
  public void paintColumn(Graphics g, Rectangle col) {
    g.setColor(Color.gray.darker().darker());
    g.fillRect(col.x, col.y, col.width, col.height);
  }
  
  public void actionPerformed(ActionEvent arg0) {
    if (this.gameStarted) {
      this.ball.y += 2;
      (this.column[0]).x -= 2;
      (this.column[1]).x -= 2;
      if ((this.column[0]).x + this.columnWidth < 0) {
        (this.column[0]).x = 670;
        (this.column[1]).x = 670;
        double r = Math.random() * 250.0D;
        this.random = (int)r;
        this.columnHeight = 150 + this.random;
        setColumn();
      } 
      if (this.ball.intersects(this.column[0])) {
        this.gameStarted = false;
        this.gameOver = true;
      } 
      if (this.ball.intersects(this.column[1])) {
        this.gameStarted = false;
        this.gameOver = true;
      } 
    } 
    repaint();
  }
  
  public void mouseClicked(MouseEvent arg0) {
    if (this.gameStarted) {
      this.ball.y -= 50;
    } else {
      this.score = -1;
      this.ball.y = 150;
      setColumn();
      this.gameStarted = true;
      this.gameOver = false;
    } 
  }
  
  public void mouseEntered(MouseEvent arg0) {}
  
  public void mouseExited(MouseEvent arg0) {}
  
  public void mousePressed(MouseEvent arg0) {}
  
  public void mouseReleased(MouseEvent arg0) {}
}

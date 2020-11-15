package ShootingGame_V4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class mainGameV4 extends JPanel implements ActionListener, KeyListener {
  private static final long serialVersionUID = 1L;
  
  int width;
  
  int height;
  
  Rectangle target;
  
  Rectangle targetrect;
  
  Rectangle gun;
  
  Rectangle bullet;
  
  Timer timer;
  
  boolean left;
  
  boolean right;
  
  boolean shoot;
  
  int random;
  
  int score;
  
  int bulletcount;
  
  boolean gameStarted;
  
  int gunspeed;
  
  int bulletspeed;
  
  int targetspeed;
  
  Rectangle rect1;
  
  Rectangle rect2;
  
  int rect1dir;
  
  int rect2dir;
  
  public mainGameV4() {
    this.width = 360;
    this.height = 600;
    this.score = 0;
    this.bulletcount = 10;
    this.left = false;
    this.right = false;
    this.shoot = false;
    this.gameStarted = false;
    this.gunspeed = 2;
    this.bulletspeed = 2;
    this.targetspeed = 2;
    this.rect1dir = 5;
    this.rect2dir = -5;
    setPreferredSize(new Dimension(this.width, this.height));
    addKeyListener(this);
    this.target = new Rectangle(30, 50, 20, 20);
    this.targetrect = new Rectangle(0, 50, 80, 10);
    this.gun = new Rectangle(160, 500, 20, 40);
    this.bullet = new Rectangle(this.gun.x, this.gun.y, 20, 20);
    this.rect1 = new Rectangle(155, 180, 50, 5);
    this.rect2 = new Rectangle(155, 270, 50, 5);
    this.timer = new Timer(8, this);
    this.timer.start();
  }
  
  public void addNotify() {
    super.addNotify();
    requestFocus();
  }
  
  public int getRandom() {
    double r = Math.random() * 4.0D;
    int a = 2 + (int)r;
    return a;
  }
  
  public int getRandomrect() {
    double r = Math.random() * 3.0D;
    int a = 3 + (int)r;
    return a;
  }
  
  public void paintComponent(Graphics g) {
    g.setColor(Color.white);
    g.fillRect(0, 0, this.width, this.height);
    g.setColor(Color.black);
    g.fillRect(0, 0, 1, this.height);
    g.fillRect(0, 0, this.width, 1);
    g.fillRect(0, this.height - 1, this.width, 1);
    g.fillRect(this.width - 1, 0, 1, this.height);
    g.setColor(Color.black);
    g.fillOval(this.target.x, this.target.y, this.target.width, this.target.height);
    g.setColor(Color.black);
    g.fillRect(this.targetrect.x, this.targetrect.y, this.targetrect.width, this.targetrect.height);
    g.setColor(Color.black);
    g.fillOval(this.bullet.x, this.bullet.y, this.bullet.width, this.bullet.height);
    g.setColor(Color.black);
    g.fillRect(this.gun.x, this.gun.y, this.gun.width, this.gun.height);
    g.setColor(Color.black);
    g.fillRect(this.rect1.x, this.rect1.y, this.rect1.width, this.rect1.height);
    g.setColor(Color.black);
    g.fillRect(this.rect2.x, this.rect2.y, this.rect2.width, this.rect2.height);
    if (!this.gameStarted) {
      g.setColor(Color.black);
      g.fillRect(0, 200, this.width, 140);
      g.setColor(Color.white);
      g.setFont(new Font("serif", 1, 15));
      g.drawString("a/d - movements, w - shoot", 85, 242);
      g.drawString("press 1 to play", 115, 282);
    } 
    g.setColor(Color.black);
    g.setFont(new Font("serif", 1, 20));
    g.drawString(this.score, 310, 30);
    drawBullet(g, this.bulletcount);
  }
  
  public void drawBullet(Graphics g, int no) {
    int w = 40;
    g.setColor(Color.black);
    g.setFont(new Font("serif", 1, 15));
    g.drawString(no, 10, 587);
    for (int i = 0; i < no; i++) {
      g.setColor(Color.black);
      g.fillOval(w, 580, 5, 5);
      w += 10;
    } 
  }
  
  public void actionPerformed(ActionEvent arg0) {
    if (this.gameStarted) {
      this.target.x += this.targetspeed;
      this.targetrect.x += this.targetspeed;
      this.rect1.x += this.rect1dir;
      this.rect2.x += this.rect2dir;
      if (this.rect1.x > 310)
        this.rect1dir = -this.rect1dir; 
      if (this.rect1.x < 0)
        this.rect1dir = getRandomrect(); 
      if (this.rect2.x > 310)
        this.rect2dir = -this.rect2dir; 
      if (this.rect2.x < 0)
        this.rect2dir = getRandomrect(); 
      if (this.target.x < 30)
        this.targetspeed = getRandom(); 
      if (this.target.x > 310)
        this.targetspeed = -this.targetspeed; 
      if (this.gun.x > 340) {
        this.gunspeed = -this.gunspeed;
        this.bulletspeed = -this.bulletspeed;
      } 
      if (this.gun.x < 0) {
        this.gunspeed = -this.gunspeed;
        this.bulletspeed = -this.bulletspeed;
      } 
      this.gun.x += this.gunspeed;
      this.bullet.x += this.bulletspeed;
      if (this.shoot) {
        this.bullet.y -= 15;
        this.bulletspeed = 0;
        if (this.bullet.intersects(this.target)) {
          this.bulletcount++;
          this.shoot = false;
          this.score += 2;
          this.bullet.x = this.gun.x;
          this.bullet.y = this.gun.y;
          this.bulletspeed = this.gunspeed;
        } 
        if (this.bullet.intersects(this.targetrect)) {
          this.shoot = false;
          this.bulletcount--;
          this.score++;
          this.bullet.x = this.gun.x;
          this.bullet.y = this.gun.y;
          this.bulletspeed = this.gunspeed;
        } 
        if (this.bullet.intersects(this.rect1)) {
          this.bulletcount--;
          this.shoot = false;
          this.bullet.x = this.gun.x;
          this.bullet.y = this.gun.y;
          this.bulletspeed = this.gunspeed;
        } 
        if (this.bullet.intersects(this.rect2)) {
          this.bulletcount--;
          this.shoot = false;
          this.bullet.x = this.gun.x;
          this.bullet.y = this.gun.y;
          this.bulletspeed = this.gunspeed;
        } 
        if (this.bullet.y < -25) {
          this.bulletcount--;
          this.shoot = false;
          this.bullet.x = this.gun.x;
          this.bullet.y = this.gun.y;
          this.bulletspeed = this.gunspeed;
        } 
      } 
      if (this.bulletcount == 0)
        this.gameStarted = false; 
    } 
    repaint();
  }
  
  public void keyTyped(KeyEvent e) {
    if (this.gameStarted && 
      e.getKeyChar() == 'w')
      this.shoot = true; 
    if (!this.gameStarted && 
      e.getKeyChar() == '1') {
      this.score = 0;
      this.bulletcount = 10;
      this.gameStarted = true;
    } 
  }
  
  public void keyPressed(KeyEvent arg0) {}
  
  public void keyReleased(KeyEvent arg0) {}
  
  public static void main(String[] args) {
    JFrame frame = new JFrame("Shoot Me if U can!");
    frame.getContentPane().add(new mainGameV4());
    frame.setDefaultCloseOperation(3);
    frame.setVisible(true);
    frame.setResizable(true);
    frame.pack();
  }
}

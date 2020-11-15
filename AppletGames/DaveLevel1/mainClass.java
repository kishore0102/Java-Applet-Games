package DaveL1_v1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class mainGame extends JPanel implements ActionListener, KeyListener {
  private static final long serialVersionUID = 1L;
  
  public int[][] map = new int[][] { { 
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
        1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1, 1, 1, 1, 1 }, { 1, 1 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1 }, { 
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
        1, 1 }, { 1, 2, 1, 3, 1 }, { 
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
        1, 1, 1, 1, 1, 1, 1, 1, 1 } };
  
  Timer timer;
  
  int width;
  
  int height;
  
  boolean up;
  
  boolean right;
  
  boolean left;
  
  boolean gameStarted;
  
  ArrayList<Rectangle> brick;
  
  int row;
  
  int col;
  
  ArrayList<Rectangle> pipe;
  
  ArrayList<Rectangle> door;
  
  Rectangle ball;
  
  int ballyvel;
  
  int ballxvel;
  
  public mainGame() {
    this.width = 760;
    this.height = 400;
    this.up = false;
    this.right = false;
    this.left = false;
    this.gameStarted = false;
    this.ballyvel = 2;
    this.ballxvel = 50;
    setPreferredSize(new Dimension(this.width, this.height));
    addKeyListener(this);
    this.brick = new ArrayList<>();
    this.door = new ArrayList<>();
    this.pipe = new ArrayList<>();
    this.row = this.map.length;
    this.col = (this.map[0]).length;
    System.out.println(String.valueOf(this.row) + "," + this.col);
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        if (this.map[i][j] == 1)
          this.brick.add(new Rectangle(j * this.width / this.col, i * this.height / this.row, this.width / this.col, this.height / this.row)); 
        if (this.map[i][j] == 2)
          this.pipe.add(new Rectangle(j * this.width / this.col, i * this.height / this.row, this.width / this.col, this.height / this.row)); 
        if (this.map[i][j] == 3)
          this.door.add(new Rectangle(j * this.width / this.col, i * this.height / this.row, this.width / this.col, this.height / this.row)); 
      } 
    } 
    this.ball = new Rectangle(80, 80, 40, 40);
    this.timer = new Timer(8, this);
    this.timer.start();
  }
  
  public void addNotify() {
    super.addNotify();
    requestFocus();
  }
  
  public void paintComponent(Graphics g) {
    g.setColor(Color.black);
    g.fillRect(0, 0, this.width, this.height);
    paintMap(g);
    Image image = (new ImageIcon("redball.png")).getImage();
    g.drawImage(image, this.ball.x, this.ball.y, this);
  }
  
  public void paintMap(Graphics g) {
    for (Rectangle r : this.brick) {
      Image image = (new ImageIcon("bricks.png")).getImage();
      g.drawImage(image, r.x, r.y, this);
    } 
    for (Rectangle r : this.door) {
      Image img = (new ImageIcon("door.png")).getImage();
      g.drawImage(img, r.x, r.y, this);
    } 
    for (Rectangle r : this.pipe) {
      Image img1 = (new ImageIcon("pipe.png")).getImage();
      g.drawImage(img1, r.x, r.y, this);
    } 
  }
  
  public static void main(String[] args) {
    JFrame frame = new JFrame("Dave Level 1");
    frame.getContentPane().add(new mainGame());
    frame.setDefaultCloseOperation(3);
    frame.setVisible(true);
    frame.setResizable(true);
    frame.pack();
  }
  
  public void keyPressed(KeyEvent arg0) {}
  
  public void keyReleased(KeyEvent arg0) {}
  
  public void keyTyped(KeyEvent e) {
    if (e.getKeyChar() == '1')
      this.gameStarted = true; 
    if (e.getKeyChar() == 'w')
      this.up = true; 
    if (e.getKeyChar() == 'a')
      this.left = true; 
    if (e.getKeyChar() == 'd')
      this.right = true; 
  }
  
  public void actionPerformed(ActionEvent arg0) {
    this.ball.y += this.ballyvel;
    if (this.up) {
      this.ball.y -= 80;
      this.up = false;
      this.ballyvel = 2;
    } 
    if (this.left) {
      this.ball.x -= this.ballxvel;
      this.left = false;
    } 
    if (this.right) {
      this.ball.x += this.ballxvel;
      this.right = false;
    } 
    for (Rectangle r : this.brick) {
      if (r.intersects(this.ball))
        this.ballyvel = 0; 
    } 
    repaint();
  }
}

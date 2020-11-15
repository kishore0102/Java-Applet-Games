package Pong_V2;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
  double x;
  
  double y;
  
  double velX;
  
  double velY;
  
  int score = 0;
  
  public Ball(int a, int b) {
    this.x = a;
    this.y = b;
    this.velX = randomSpeedX() * randomDir();
    this.velY = randomSpeedY() * randomDir();
  }
  
  public double randomSpeedX() {
    return Math.random() * 3.0D + 3.0D;
  }
  
  public void setX() {
    this.velX = randomSpeedX() * randomDir();
  }
  
  public double randomSpeedY() {
    return Math.random() * 3.0D + 2.0D;
  }
  
  public double randomDir() {
    int rand = (int)Math.random() * 2;
    if (rand == 1)
      return 1.0D; 
    return -1.0D;
  }
  
  public void draw(Graphics g) {
    g.setColor(Color.white);
    g.fillOval((int)this.x - 10, (int)this.y - 10, 20, 20);
  }
  
  public void move() {
    this.x += this.velX;
    this.y += this.velY;
    if (this.y < 11.0D)
      this.velY = -this.velY; 
    if (this.y > 461.0D)
      this.velY = -this.velY; 
  }
  
  public void checkBallCollision(paddle p1, paddle p2) {
    if (this.x <= 50.0D) {
      if (this.y - 10.0D >= (p2.getY() - 1) && this.y - 10.0D <= (p2.getY() + 81)) {
        setX();
        this.velX = -this.velX;
      } 
    } else if (this.x >= 650.0D && 
      this.y + 10.0D >= (p1.getY() - 1) && this.y + 10.0D <= (p1.getY() + 81)) {
      this.velX = -this.velX;
      setX();
      this.score++;
    } 
  }
  
  public int getX() {
    return (int)this.x;
  }
  
  public int getY() {
    return (int)this.y;
  }
}

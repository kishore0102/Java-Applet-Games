package Pong_V2;

import java.awt.Color;
import java.awt.Graphics;

public class aiPaddle implements paddle {
  boolean upaccel = false;
  
  boolean downaccel = false;
  
  double y = 210.0D;
  
  double yvel = 0.0D;
  
  int x;
  
  public aiPaddle(int p) {
    if (p == 0) {
      this.x = 20;
    } else if (p == 1) {
      this.x = 660;
    } 
  }
  
  public void draw(Graphics g) {
    g.setColor(Color.white);
    g.fillRect(this.x, (int)this.y, 15, 80);
  }
  
  public int getY() {
    return (int)this.y;
  }
  
  public void move() {}
  
  public void sety(int x) {
    this.y = (x - 20);
    if (this.y < 0.0D)
      this.y = 0.0D; 
    if (this.y > 391.0D)
      this.y = 391.0D; 
  }
}

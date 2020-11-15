package Pong_V2;

import java.awt.Color;
import java.awt.Graphics;

public class humanPaddle implements paddle {
  boolean upaccel = false;
  
  boolean downaccel = false;
  
  double y = 210.0D;
  
  double yvel = 0.0D;
  
  int x;
  
  public humanPaddle(int p) {
    if (p == 0) {
      this.x = 20;
    } else if (p == 1) {
      this.x = 654;
    } 
  }
  
  public void draw(Graphics g) {
    g.setColor(Color.white);
    g.fillRect(this.x, (int)this.y, 15, 80);
  }
  
  public void setUpaccel(boolean in) {
    this.upaccel = in;
  }
  
  public void setDownaccel(boolean in) {
    this.downaccel = in;
  }
  
  public int getY() {
    return (int)this.y;
  }
  
  public void sety(int e) {
    this.y = (e - 20);
    if (this.y < 0.0D)
      this.y = 0.0D; 
    if (this.y > 391.0D)
      this.y = 391.0D; 
  }
}

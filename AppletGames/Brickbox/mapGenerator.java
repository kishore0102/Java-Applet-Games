package BreakBox;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class mapGenerator {
  public int[][] map;
  
  public int brickWidth;
  
  public int brickHeight;
  
  public mapGenerator(int row, int column) {
    this.map = new int[row][column];
    for (int i = 0; i < this.map.length; i++) {
      for (int j = 0; j < (this.map[i]).length; j++)
        this.map[i][j] = 1; 
    } 
    this.brickWidth = 700 / column;
    this.brickHeight = 140 / row;
  }
  
  public void draw(Graphics2D g) {
    for (int i = 0; i < this.map.length; i++) {
      for (int j = 0; j < (this.map[i]).length; j++) {
        if (this.map[i][j] > 0) {
          g.setColor(Color.white);
          g.fillRect(j * this.brickWidth + 50, i * this.brickHeight + 40, this.brickWidth, this.brickHeight);
          g.setStroke(new BasicStroke(3.0F));
          g.setColor(Color.black);
          g.drawRect(j * this.brickWidth + 50, i * this.brickHeight + 40, this.brickWidth, this.brickHeight);
        } 
      } 
    } 
  }
  
  public void setBrickValue(int value, int row, int col) {
    this.map[row][col] = value;
  }
}

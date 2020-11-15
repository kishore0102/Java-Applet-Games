package Pong_V2;

import javax.swing.JFrame;

public class mainClass {
  JFrame frame;
  
  int width = 700, height = 500;
  
  public static void main(String[] args) {
    (new mainClass()).gui();
  }
  
  public void gui() {
    this.frame = new JFrame("Tennis Pong");
    mainPanel panel = new mainPanel();
    this.frame.setDefaultCloseOperation(3);
    this.frame.setSize(this.width, this.height);
    this.frame.setVisible(true);
    this.frame.setResizable(false);
    this.frame.add(panel);
  }
}

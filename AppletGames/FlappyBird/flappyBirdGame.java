package FlappyBird_V2;

import javax.swing.JFrame;

public class flappyBirdgame {
  JFrame frame;
  
  int width = 600;
  
  int height = 600;
  
  panel p;
  
  public static void main(String[] args) {
    (new flappyBirdgame()).gui();
  }
  
  public void gui() {
    this.frame = new JFrame("Tennis Pong");
    this.p = new panel(this.width, this.height);
    this.frame.setDefaultCloseOperation(3);
    this.frame.setSize(594, 571);
    this.frame.setVisible(true);
    this.frame.setResizable(true);
    this.frame.add(this.p);
  }
}

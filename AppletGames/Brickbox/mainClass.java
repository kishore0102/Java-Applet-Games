package BreakBox;

import javax.swing.JFrame;

public class mainClass {
  public static void main(String[] args) {
    JFrame frame = new JFrame("BreakBox Game by Tekbus");
    Gameplay gameplay = new Gameplay();
    frame.setBounds(10, 10, 800, 600);
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(3);
    frame.add(gameplay);
  }
}

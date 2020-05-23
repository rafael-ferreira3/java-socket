package utils.view;

import javax.swing.*;

public class CustomFrame extends JFrame {
    public CustomFrame(String title){
        super(title);
        this.setSize(600,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void setCustomSize(int width, int height){
        this.setSize(width,height);
    }
}

package src;

import javax.swing.JFrame;

public class Window extends JFrame {
    Canvas canvas = new Canvas();

    public Window() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // this.setUndecorated(true);

        this.add(canvas);
        this.pack();
    }
}

package horsman14.v2ch12.print;

import module java.desktop;

/**
 * This program demonstrates how to print 2D graphics
 */
class PrintDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new PrintDemoFrame();
            frame.setTitle("PrintTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

package horsman14.v2ch12.composite;

import module java.desktop;

/**
 * This program demonstrates the Porter-Duff composition rules.
 */
class CompositeDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new CompositeDemoFrame();
            frame.setTitle("CompositeTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

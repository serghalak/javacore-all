package horsman14.v2ch12.tree;

import module java.desktop;

/**
 * This program shows a simple tree.
 */
class SimpleTree {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new SimpleTreeFrame();
            frame.setTitle("SimpleTree");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

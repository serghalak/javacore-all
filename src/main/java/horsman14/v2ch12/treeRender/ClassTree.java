package horsman14.v2ch12.treeRender;

import module java.desktop;

/**
 * This program demonstrates cell rendering and listening to tree selection events.
 */
class ClassTree {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new ClassTreeFrame();
            frame.setTitle("ClassTree");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

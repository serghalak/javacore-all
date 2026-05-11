package horsman14.v2ch12.treeEdit;

import module java.desktop;

/**
 * This program demonstrates tree editing.
 */
class TreeEditDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new TreeEditFrame();
            frame.setTitle("TreeEditTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

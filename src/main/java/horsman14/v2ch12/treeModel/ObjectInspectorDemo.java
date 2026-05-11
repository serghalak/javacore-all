package horsman14.v2ch12.treeModel;

import module java.desktop;

/**
 * This program demonstrates how to use a custom tree model. It displays the fields of an
 * object.
 */
class ObjectInspectorDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new ObjectInspectorFrame();
            frame.setTitle("ObjectInspectorTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

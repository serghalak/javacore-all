package horsman14.v2ch12.imageIO;

import module java.desktop;

/**
 * This program lets you read and write image files in the formats that the JDK supports.
 * Multi-file images are supported.
 */
class ImageIODemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new ImageIOFrame();
            frame.setTitle("CompositeTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

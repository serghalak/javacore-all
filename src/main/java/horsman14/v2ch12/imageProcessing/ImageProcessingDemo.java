package horsman14.v2ch12.imageProcessing;

import module java.desktop;

/**
 * This program demonstrates various image processing operations.
 */
class ImageProcessingDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new ImageProcessingFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

package horsman14.v2ch12.rasterImage;

import module java.desktop;

/**
 * This program demonstrates how to build up an image from individual pixels.
 */
class RasterImageDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new RasterImageFrame();
            frame.setTitle("RasterImageTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

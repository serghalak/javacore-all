package horsman14.v2ch12.book;

import module java.desktop;

/**
 * This program demonstrates the printing of a multi-page book. It prints a "banner", by blowing
 * up a text string to fill the entire page vertically. The program also contains a generic
 * print preview dialog.
 */
class BookDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new BookDemoFrame();
            frame.setTitle("BookTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

package horsman14.v2ch11.border;

import module java.desktop;

class BorderDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new BorderFrame();
            frame.setTitle("BorderTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

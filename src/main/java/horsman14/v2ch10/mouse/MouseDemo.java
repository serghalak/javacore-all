package horsman14.v2ch10.mouse;

import module java.desktop;

class MouseDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new MouseFrame();
            frame.setTitle("MouseTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

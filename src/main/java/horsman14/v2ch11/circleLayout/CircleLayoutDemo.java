package horsman14.v2ch11.circleLayout;

import module java.desktop;

class CircleLayoutDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new CircleLayoutFrame();
            frame.setTitle("CircleLayoutTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

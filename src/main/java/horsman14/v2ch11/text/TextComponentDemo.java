package horsman14.v2ch11.text;

import module java.desktop;

class TextComponentDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new TextComponentFrame();
            frame.setTitle("TextComponentTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

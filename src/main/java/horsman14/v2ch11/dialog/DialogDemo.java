package horsman14.v2ch11.dialog;

import module java.desktop;

class DialogDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new DialogFrame();
            frame.setTitle("DialogTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

package horsman14.v2ch10.action;

import module java.desktop;

class ActionDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new ActionFrame();
            frame.setTitle("ActionTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

package horsman14.v2ch11.gridbag;

import module java.desktop;

class GridBagLayoutDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new FontFrame();
            frame.setTitle("GridBagLayoutTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

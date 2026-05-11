package horsman14.v2ch11.comboBox;

import module java.desktop;

class ComboBoxDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new ComboBoxFrame();
            frame.setTitle("ComboBoxTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

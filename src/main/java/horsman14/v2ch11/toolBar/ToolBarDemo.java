package horsman14.v2ch11.toolBar;

import module java.desktop;

class ToolBarDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new ToolBarFrame();
            frame.setTitle("ToolBarTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

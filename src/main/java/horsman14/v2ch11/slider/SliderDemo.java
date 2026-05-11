package horsman14.v2ch11.slider;

import module java.desktop;

class SliderDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new SliderFrame();
            frame.setTitle("SliderTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

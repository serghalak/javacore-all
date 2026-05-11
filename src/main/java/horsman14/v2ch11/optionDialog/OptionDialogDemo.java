package horsman14.v2ch11.optionDialog;

import module java.desktop;

class OptionDialogDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new OptionDialogFrame();
            frame.setTitle("OptionDialogTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

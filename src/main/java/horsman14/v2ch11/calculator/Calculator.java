package horsman14.v2ch11.calculator;

import module java.desktop;

class Calculator {
    void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new CalculatorFrame();
            frame.setTitle("Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

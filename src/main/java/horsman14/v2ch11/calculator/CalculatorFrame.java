package horsman14.v2ch11.calculator;

import module java.desktop;

/**
 * A frame with a calculator panel.
 */
public class CalculatorFrame extends JFrame {
    public CalculatorFrame() {
        add(new CalculatorPanel());
        pack();
    }
}

package horsman14.v1ch06;

import module java.base;
import module java.desktop;

import javax.swing.Timer;

/**
 * This program demonstrates how to use a Timer.
 */
class TimerDemo {
    void main() {
        var listener = new TimePrinter();

        // construct a timer that calls the listener once every second
        var timer = new Timer(1000, listener);
        timer.start();

        // keep program running until the user selects "OK"
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }

}

class TimePrinter implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        IO.println("At the tone, the time is " + Instant.ofEpochMilli(event.getWhen()));
        Toolkit.getDefaultToolkit().beep();
    }
}

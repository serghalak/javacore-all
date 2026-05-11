package horsman14.v1ch06.innerClass;

import module java.desktop;

/**
 * This program demonstrates the use of inner classes.
 */
class InnerClassDemo {
    void main() {
        var clock = new TalkingClock(1000, true);
        clock.start();

        // keep program running until the user selects "OK"
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

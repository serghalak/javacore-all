package horsman14.v1ch06.localInnerClass;

import module java.desktop;

/**
 * This program demonstrates the use of local inner classes.
 */
class LocalInnerClassDemo {
    void main() {
        var clock = new TalkingClock();
        clock.start(1000, true);

        // keep program running until the user selects "Ok"
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

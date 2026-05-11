package horsman14.v1ch06.anonymousInnerClass;

import module java.desktop;

/**
 * This program demonstrates anonymous inner classes.
 */
class AnonymousInnerClassDemo {
    void main() {
        var clock = new TalkingClock();
        clock.start(1000, true);

        // keep program running until the user selects "OK"
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

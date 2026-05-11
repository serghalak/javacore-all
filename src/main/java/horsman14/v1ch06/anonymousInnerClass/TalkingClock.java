package horsman14.v1ch06.anonymousInnerClass;

import module java.base;
import module java.desktop;
import javax.swing.Timer;

/**
 * A clock that prints the time in regular intervals.
 */
public class TalkingClock {
    /**
     * Starts the clock.
     * @param interval the interval between messages (in milliseconds)
     * @param beep true if the clock should beep
     */
    public void start(int interval, boolean beep) {
        var listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                IO.println(
                        "At the tone, the time is " + Instant.ofEpochMilli(event.getWhen()));
                if (beep) Toolkit.getDefaultToolkit().beep();
            }
        };
        var timer = new Timer(interval, listener);
        timer.start();
    }
}

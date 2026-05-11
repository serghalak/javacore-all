package horsman14.v2ch10.mouse;

import module java.desktop;

/**
 * A frame containing a panel for testing mouse operations
 */
public class MouseFrame extends JFrame {
    public MouseFrame() {
        add(new MouseComponent());
        pack();
    }
}

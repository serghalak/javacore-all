package horsman14.v2ch12.tableRowColumn;

import module java.desktop;

/**
 * This program demonstrates how to work with rows and columns in a table.
 */
class TableRowColumnDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new PlanetTableFrame();
            frame.setTitle("TableRowColumnTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

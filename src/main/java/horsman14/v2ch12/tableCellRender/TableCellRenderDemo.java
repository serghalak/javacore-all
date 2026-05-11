package horsman14.v2ch12.tableCellRender;

import module java.desktop;

/**
 * This program demonstrates cell rendering and editing in a table.
 */
class TableCellRenderDemo {
    void main() {
        EventQueue.invokeLater(() -> {

            var frame = new TableCellRenderFrame();
            frame.setTitle("TableCellRenderTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

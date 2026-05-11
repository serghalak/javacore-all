package horsman14.v2ch11.dialog;

import module java.desktop;

/**
 * A frame with a menu whose File->About action shows a dialog.
 */
public class DialogFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    private AboutDialog dialog;

    public DialogFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // construct a File menu

        var menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        var fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // add About and Exit menu items

        // the About item shows the About dialog

        var aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(_ -> {
            if (dialog == null) // first time
                dialog = new AboutDialog(DialogFrame.this);
            dialog.setVisible(true); // pop up dialog
        });
        fileMenu.add(aboutItem);

        // the Exit item exits the program

        var exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(_ -> System.exit(0));
        fileMenu.add(exitItem);
    }
}

package horsman14.v2ch12.print;

import module java.desktop;

/**
 * This frame shows a panel with 2D graphics and buttons to print the graphics and to set up
 * the page format.
 */
public class PrintDemoFrame extends JFrame {
    private PrintComponent canvas;
    private PrintRequestAttributeSet attributes;

    public PrintDemoFrame() {
        canvas = new PrintComponent();
        add(canvas, BorderLayout.CENTER);

        attributes = new HashPrintRequestAttributeSet();

        var buttonPanel = new JPanel();
        var printButton = new JButton("Print");
        buttonPanel.add(printButton);
        printButton.addActionListener(_ -> {
            try {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintable(canvas);
                if (job.printDialog(attributes)) job.print(attributes);
            }
            catch (PrinterException e) {
                JOptionPane.showMessageDialog(PrintDemoFrame.this, e);
            }
        });

        var pageSetupButton = new JButton("Page setup");
        buttonPanel.add(pageSetupButton);
        pageSetupButton.addActionListener(_ -> {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.pageDialog(attributes);
        });

        add(buttonPanel, BorderLayout.NORTH);
        pack();
    }
}

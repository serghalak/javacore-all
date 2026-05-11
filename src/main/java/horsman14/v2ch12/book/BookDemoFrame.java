package horsman14.v2ch12.book;

import module java.desktop;

/**
 * This frame has a text field for the banner text and buttons for printing, page setup, and
 * print preview.
 */
public class BookDemoFrame extends JFrame {
    private JTextField text;
    private PageFormat pageFormat;
    private PrintRequestAttributeSet attributes;

    public BookDemoFrame() {
        text = new JTextField();
        add(text, BorderLayout.NORTH);

        attributes = new HashPrintRequestAttributeSet();

        var buttonPanel = new JPanel();

        var printButton = new JButton("Print");
        buttonPanel.add(printButton);
        printButton.addActionListener(_ -> {
            try {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPageable(makeBook());
                if (job.printDialog(attributes)) {
                    job.print(attributes);
                }
            }
            catch (PrinterException e) {
                JOptionPane.showMessageDialog(BookDemoFrame.this, e);
            }
        });

        var pageSetupButton = new JButton("Page setup");
        buttonPanel.add(pageSetupButton);
        pageSetupButton.addActionListener(_ -> {
            PrinterJob job = PrinterJob.getPrinterJob();
            pageFormat = job.pageDialog(attributes);
        });

        var printPreviewButton = new JButton("Print preview");
        buttonPanel.add(printPreviewButton);
        printPreviewButton.addActionListener(_ -> {
            var dialog = new PrintPreviewDialog(makeBook());
            dialog.setVisible(true);
        });

        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    /**
     * Makes a book that contains a cover page and the pages for the banner.
     */
    public Book makeBook() {
        if (pageFormat == null) {
            PrinterJob job = PrinterJob.getPrinterJob();
            pageFormat = job.defaultPage();
        }
        var book = new Book();
        String message = text.getText();
        var banner = new Banner(message);
        int pageCount = banner.getPageCount((Graphics2D) getGraphics(), pageFormat);
        book.append(new CoverPage(message + " (" + pageCount + " pages)"), pageFormat);
        book.append(banner, pageFormat, pageCount);
        return book;
    }
}

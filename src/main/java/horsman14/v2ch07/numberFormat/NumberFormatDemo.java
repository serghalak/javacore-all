package horsman14.v2ch07.numberFormat;

import module java.base;
import module java.desktop;
import horsman14.v2ch11.gridbag.GBC;

/**
 * This program demonstrates formatting numbers under various locales.
 */
class NumberFormatDemo {
    void main() {
        EventQueue.invokeLater(() -> {
            var frame = new NumberFormatFrame();
            frame.setTitle("NumberFormatTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * This frame contains radio buttons to select a number format, a combo box to pick a locale, a
 * text field to display a formatted number, and a button to parse the text field contents.
 */
class NumberFormatFrame extends JFrame {
    Locale[] locales;
    double currentNumber;
    JComboBox<String> localeCombo = new JComboBox<>();
    JButton parseButton = new JButton("Parse");
    JTextField numberText = new JTextField(30);
    JRadioButton numberRadioButton = new JRadioButton("Number");
    JRadioButton compactShortRadioButton = new JRadioButton("Compact Short");
    JRadioButton compactLongRadioButton = new JRadioButton("Compact Long");
    JRadioButton currencyRadioButton = new JRadioButton("Currency");
    JRadioButton percentRadioButton = new JRadioButton("Percent");
    ButtonGroup rbGroup = new ButtonGroup();
    NumberFormat currentNumberFormat;

    NumberFormatFrame() {
        setLayout(new GridBagLayout());

        ActionListener listener = _ -> updateDisplay();

        var p = new JPanel();
        addRadioButton(p, numberRadioButton, rbGroup, listener);
        addRadioButton(p, compactShortRadioButton, rbGroup, listener);
        addRadioButton(p, compactLongRadioButton, rbGroup, listener);
        addRadioButton(p, currencyRadioButton, rbGroup, listener);
        addRadioButton(p, percentRadioButton, rbGroup, listener);

        add(new JLabel("Locale:"), new GBC(0, 0).setAnchor(GBC.EAST));
        add(p, new GBC(1, 1));
        add(parseButton, new GBC(0, 2).setInsets(2));
        add(localeCombo, new GBC(1, 0).setAnchor(GBC.WEST));
        add(numberText, new GBC(1, 2).setFill(GBC.HORIZONTAL));
        locales = (Locale[]) NumberFormat.getAvailableLocales().clone();
        Arrays.sort(locales, Comparator.comparing(Locale::getDisplayName));
        for (Locale loc : locales)
            localeCombo.addItem(loc.getDisplayName());
        localeCombo.setSelectedItem(Locale.getDefault().getDisplayName());
        currentNumber = 123456.78;
        updateDisplay();

        localeCombo.addActionListener(listener);

        parseButton.addActionListener(_ -> {
            String s = numberText.getText().strip();
            try {
                Number n = currentNumberFormat.parse(s);
                currentNumber = n.doubleValue();
                updateDisplay();
            }
            catch (ParseException e) {
                numberText.setText(e.getMessage());
            }
        });
        pack();
    }

    /**
     * Adds a radio button to a container.
     * @param p the container into which to place the button
     * @param b the button
     * @param g the button group
     * @param listener the button listener
     */
    void addRadioButton(Container p, JRadioButton b, ButtonGroup g, ActionListener listener) {
        b.setSelected(g.getButtonCount() == 0);
        b.addActionListener(listener);
        g.add(b);
        p.add(b);
    }

    /**
     * Updates the display and formats the number according to the user settings.
     */
    void updateDisplay() {
        Locale currentLocale = locales[localeCombo.getSelectedIndex()];
        currentNumberFormat = null;
        if (numberRadioButton.isSelected())
            currentNumberFormat = NumberFormat.getNumberInstance(currentLocale);
        else if (compactShortRadioButton.isSelected())
            currentNumberFormat = NumberFormat.getCompactNumberInstance(currentLocale,
                    NumberFormat.Style.SHORT);
        else if (compactLongRadioButton.isSelected())
            currentNumberFormat = NumberFormat.getCompactNumberInstance(currentLocale,
                    NumberFormat.Style.LONG);
        else if (currencyRadioButton.isSelected())
            currentNumberFormat = NumberFormat.getCurrencyInstance(currentLocale);
        else if (percentRadioButton.isSelected())
            currentNumberFormat = NumberFormat.getPercentInstance(currentLocale);
        String formatted = currentNumberFormat.format(currentNumber);
        numberText.setText(formatted);
    }
}

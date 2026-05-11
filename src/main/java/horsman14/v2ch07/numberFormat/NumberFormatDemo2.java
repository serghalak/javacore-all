package horsman14.v2ch07.numberFormat;

import module java.base;
import horsman14.v2ch07.util.Choices;

/**
 * This program demonstrates formatting numbers under various locales.
 */
class NumberFormatDemo2 {
    void main() {
        var locales = (Locale[]) NumberFormat.getAvailableLocales().clone();
        Arrays.sort(locales, Comparator.comparing(Locale::getDisplayName));
        Locale locale = Choices.choose(locales, Locale::getDisplayName);

        var formatters = new LinkedHashMap<NumberFormat, String>();
        formatters.put(NumberFormat.getNumberInstance(locale), "Number");
        formatters.put(NumberFormat.getCompactNumberInstance(locale, NumberFormat.Style.SHORT),
                "Compact Short");
        formatters.put(NumberFormat.getCompactNumberInstance(locale, NumberFormat.Style.LONG),
                "Compact Long");
        formatters.put(NumberFormat.getPercentInstance(locale), "Percent");
        formatters.put(NumberFormat.getCurrencyInstance(locale), "Currency");
        NumberFormat formatter = Choices.choose(formatters);

        String operation = Choices.choose("Format", "Parse");
        if (operation.equals("Format")) {
            double number = Double.parseDouble(
                    IO.readln("Enter a floating-point number to format: "));
            System.out.println(formatter.format(number));
        }
        else {
            String text = IO.readln("Enter a floating-point number to parse: ");
            try {
                System.out.println(formatter.parse(text));
            }
            catch (ParseException e) {
                System.out.println("ParseException " + e.getMessage());
            }
        }
    }
}

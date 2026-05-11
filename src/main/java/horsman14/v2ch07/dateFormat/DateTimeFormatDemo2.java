package horsman14.v2ch07.dateFormat;

import module java.base;
import horsman14.v2ch07.util.*;

/**
 * This program demonstrates formatting dates under various locales.
 */
class DateTimeFormatDemo2 {
    void main() {
        var locales = (Locale[]) NumberFormat.getAvailableLocales().clone();
        Arrays.sort(locales, Comparator.comparing(Locale::getDisplayName));
        Locale locale = Choices.choose(locales, Locale::getDisplayName);
        FormatStyle style = Choices.choose(FormatStyle.class,
                "Short", "Medium", "Long", "Full");
        String type = Choices.choose("Date", "Time", "Date and Time");

        if (type.equals("Date")) {
            DateTimeFormatter formatter
                = DateTimeFormatter.ofLocalizedDate(style).withLocale(locale);
            System.out.println(formatter.format(LocalDate.now()));
            String input = IO.readln("Enter another date: ");
            LocalDate date = LocalDate.parse(input, formatter);
            System.out.println(formatter.format(date));
        } else if (type.equals("Time")) {
            DateTimeFormatter formatter
                = DateTimeFormatter.ofLocalizedTime(style).withLocale(locale);
            System.out.println(formatter.format(LocalTime.now()));
            String input = IO.readln("Enter another time: ");
            LocalTime time = LocalTime.parse(input, formatter);
            System.out.println(formatter.format(time));
        } else {
            DateTimeFormatter formatter
                = DateTimeFormatter.ofLocalizedDateTime(style).withLocale(locale);
            System.out.println(formatter.format(ZonedDateTime.now()));
            String input = IO.readln("Enter another date and time: ");
            ZonedDateTime dateTime = ZonedDateTime.parse(input, formatter);
            System.out.println(formatter.format(dateTime));
        }
    }
}

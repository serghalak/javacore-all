package horsman14.v2ch06;

/**
 * This program demonstrates time formatting.
 */

import module java.base;

class Formatting {
    void main() {
        ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0,
                ZoneId.of("America/New_York"));

        String formatted = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(apollo11launch);
        // 1969-07-16T09:32:00-04:00
        IO.println(formatted);

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        formatted = formatter.format(apollo11launch);
        // July 16, 1969 9:32:00 AM EDT
        IO.println(formatted);
        formatted = formatter.withLocale(Locale.FRENCH).format(apollo11launch);
        // 16 juillet 1969 09:32:00 EDT
        IO.println(formatted);

        formatter = DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm");
        formatted = formatter.format(apollo11launch);
        IO.println(formatted);

        LocalDate churchsBirthday = LocalDate.parse("1903-06-14");
        IO.println("churchsBirthday: " + churchsBirthday);
        apollo11launch = ZonedDateTime.parse("1969-07-16 03:32:00-0400",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssxx"));
        IO.println("apollo11launch: " + apollo11launch);

        for (DayOfWeek w : DayOfWeek.values())
            IO.print(w.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " ");
    }
}

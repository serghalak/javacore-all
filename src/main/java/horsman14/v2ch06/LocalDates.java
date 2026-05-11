package horsman14.v2ch06;

/**
 * This program demonstrates local dates.
 */
import module java.base;

class LocalDates {
    void main() {
        LocalDate today = LocalDate.now(); // Today’s date
        IO.println("today: " + today);

        LocalDate alonzosBirthday = LocalDate.of(1903, 6, 14);
        alonzosBirthday = LocalDate.of(1903, Month.JUNE, 14);
        // Uses the Month enumeration
        IO.println("alonzosBirthday: " + alonzosBirthday);

        LocalDate programmersDay = LocalDate.of(2018, 1, 1).plusDays(255);
        // September 13, but in a leap year it would be September 12
        IO.println("programmersDay: " + programmersDay);

        LocalDate independenceDay = LocalDate.of(2018, Month.JULY, 4);
        LocalDate christmas = LocalDate.of(2018, Month.DECEMBER, 25);

        IO.println("Until christmas: " + independenceDay.until(christmas));
        IO.println("Until christmas: " + independenceDay.until(christmas, ChronoUnit.DAYS));

        IO.println(LocalDate.of(2016, 1, 31).plusMonths(1));
        IO.println(LocalDate.of(2016, 3, 31).minusMonths(1));

        DayOfWeek startOfLastMillennium = LocalDate.of(1900, 1, 1).getDayOfWeek();
        IO.println("startOfLastMillennium: " + startOfLastMillennium);
        IO.println(startOfLastMillennium.getValue());
        IO.println(DayOfWeek.SATURDAY.plus(3));

        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDate endExclusive = LocalDate.now();
        Stream<LocalDate> firstDaysInMonth = start.datesUntil(endExclusive,
                Period.ofMonths(1));
        IO.println("firstDaysInMonth: " + firstDaysInMonth.toList());
    }
}

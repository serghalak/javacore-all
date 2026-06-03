package horsman14.v1ch04;

/*
Задача: програма, яка за введеним місяцем і роком виведе календар цього місяця в консоль
і позначить поточний день зірочкою (*), якщо він належить цьому місяцю/року.

План (коротко):
- Прийняти місяць та рік (аргументи командного рядка або від користувача через Scanner).
- Використати java.time.YearMonth/LocalDate щоб обчислити перший день тижня і число днів.
- Надрукувати заголовок (Mon ... Sun) та числа, вирівняні в стовпцях; поточний день — з '*'.

Checklist:
- [x] Працює для будь-якого валідного місяця/року
- [x] Позначає сьогоднішній день зірочкою, якщо він у тому самому місяці/році
- [x] Перший день тижня — Monday (Mon .. Sun), як у прикладі
*/

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

public class CalendarByMonth {

    public static void main(String[] args) {
        int month;
        int year;

        if (args.length >= 2) {
            // Виклик: java CalendarByMonth 5 2026
            month = parsePositiveIntOrExit(args[0], "month (1-12)");
            year = parsePositiveIntOrExit(args[1], "year");
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter month (1-12): ");
            month = sc.nextInt();
            System.out.print("Enter year (e.g. 2026): ");
            year = sc.nextInt();
            sc.close();
        }

        printMonth(month, year);
    }

    private static int parsePositiveIntOrExit(String s, String name) {
        try {
            int v = Integer.parseInt(s);
            if (v <= 0) throw new NumberFormatException();
            return v;
        } catch (NumberFormatException ex) {
            System.err.println("Invalid " + name + ": " + s);
            System.exit(1);
            return -1;
        }
    }

    private static void printMonth(int month, int year) {
        YearMonth ym;
        try {
            ym = YearMonth.of(year, month);
        } catch (Exception e) {
            System.err.println("Invalid month/year: " + month + "/" + year);
            return;
        }

        LocalDate today = LocalDate.now();
        boolean isCurrentMonth = (today.getYear() == year && today.getMonthValue() == month);

        // Заголовок: повна назва місяця та рік
        String monthName = ym.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
        System.out.println("\n  " + monthName + " " + year + "\n");

        // Друкуємо заголовки днів (Monday .. Sunday)
        String[] headers = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
        for (String h : headers) {
            System.out.printf("%4s", h);
        }
        System.out.println();

        // Знаходимо індекс дня тижня для 1-го числа (DayOfWeek: MONDAY=1 .. SUNDAY=7)
        DayOfWeek firstDow = ym.atDay(1).getDayOfWeek();
        int indent = firstDow.getValue() - 1; // кількість порожніх клітинок перед першим числом

        // Друкуємо початкові відступи
        for (int i = 0; i < indent; i++) {
            System.out.printf("%4s", "");
        }

        int days = ym.lengthOfMonth();
        for (int day = 1; day <= days; day++) {
            String s = Integer.toString(day);
            if (isCurrentMonth && day == today.getDayOfMonth()) {
                s = s + "*";
            }
            System.out.printf("%4s", s);

            // Перехід на новий рядок після неділі
            if ((indent + day) % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println("\n");
    }
}

/*
Що спростив і чому:
- Замінив складні/власні обчислення дат на стандартні класи java.time (YearMonth, LocalDate, DayOfWeek).
  Це робить код коротшим, надійним і читабельним.
- Форматування виводу зробив через printf("%4s"), щоб вирівняти стовпці без складних рядкових операцій.
- Логіку вводу зробив простою: або аргументи командного рядка (month year), або консольний ввід.
- Видалив будь-які сторонні/внутрішні допоміжні класи — все в одному простому класі для зручності тестування.
- Підтримка Monday як першого дня тижня реалізована через DayOfWeek.getValue() (MONDAY=1), що відповідає прикладу.

Як запускати:
- З консольного середовища:
  java horsman14.v1ch04.CalendarByMonth 5 2026
  або
  java horsman14.v1ch04.CalendarByMonth
  (тоді введіть місяць та рік вручну)

Примітка: цей файл — повністю самодостатній приклад. Якщо у вашому проєкті вже є клас з іншим ім'ям/пакетом,
переіменуйте відповідно або вставте логіку у потрібне місце.
*/



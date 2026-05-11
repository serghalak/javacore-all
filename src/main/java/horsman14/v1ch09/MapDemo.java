package horsman14.v1ch09;

import module java.base;

/**
 * This program demonstrates the use of a map.
 */
class MapDemo {
    void main() {
        var birthdays = new HashMap<String, LocalDate>();
        birthdays.put("Grace Hopper", LocalDate.of(1906, 12, 9));
        birthdays.put("Ada Lovelace", LocalDate.of(1915, 12, 10));
        birthdays.put("John von Neumann", LocalDate.of(1903, 12, 3));
        birthdays.put("Konrad Zuse", LocalDate.of(1910, 6, 22));

        // print all entries
        IO.println(birthdays);

        // remove an entry
        birthdays.remove("Konrad Zuse");

        // replace an entry
        birthdays.put("Ada Lovelace", LocalDate.of(1815, 12, 10));

        // look up a value
        IO.println(birthdays.get("Ada Lovelace"));

        // iterate through all entries
        birthdays.forEach((k, v) -> IO.println("key=" + k + ", value=" + v));
    }
}

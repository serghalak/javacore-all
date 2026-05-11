package horsman14.v1ch08;

import module java.base;
import horsman14.com.horstmann.corejava.Pair;

class PairDemo2 {
    void main() {
        LocalDate[] birthdays = {
                LocalDate.of(1906, 12, 9), // G. Hopper
                LocalDate.of(1815, 12, 10), // A. Lovelace
                LocalDate.of(1903, 12, 3), // J. von Neumann
                LocalDate.of(1910, 6, 22), // K. Zuse
        };
        Pair<LocalDate> mm = minmax(birthdays);
        IO.println("min = " + mm.getFirst());
        IO.println("max = " + mm.getSecond());
    }

    /**
     * Gets the minimum and maximum of an array of objects of type T.
     * @param a an array of objects of type T
     * @return a pair with the min and max values, or null if a is null or empty
     */
    <T extends Comparable> Pair<T> minmax(T[] a) {
        if (a == null || a.length == 0) return null;
        T smallest = a[0];
        T largest = a[0];
        for (int i = 1; i < a.length; i++) {
            if (smallest.compareTo(a[i]) > 0) smallest = a[i];
            if (largest.compareTo(a[i]) < 0) largest = a[i];
        }
        return new Pair<>(smallest, largest);
    }
}

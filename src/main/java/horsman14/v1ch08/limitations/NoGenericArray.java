package horsman14.v1ch08.limitations;

import module java.base;

public class NoGenericArray {
    void main(String[] args) {
        String[] mm;
        // mm = badMinmax("Tom", "Dick", "Harry"); // ClassCastException

        mm = modernMinmax(String[]::new, "Tom", "Dick", "Harry");
        IO.println(Arrays.toString(mm));

        mm = oldFashionedMinmax("Tom", "Dick", "Harry");
        IO.println(Arrays.toString(mm));
    }

    public static <T extends Comparable<? super T>> T[] badMinmax(T... a) {
        // var mm = new T[2]; // Compiler error
        var mm = new Comparable[2];
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        mm[0] = min;
        mm[1] = max;
        return (T[]) mm; // compiles with warning
    }

    public static <T extends Comparable<? super T>> T[] modernMinmax(IntFunction<T[]> constr, T... a) {
        T[] mm = constr.apply(2);
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        mm[0] = min;
        mm[1] = max;
        return mm;
    }

    public static <T extends Comparable<? super T>> T[] oldFashionedMinmax(T... a) {
        @SuppressWarnings("unchecked") T[] mm = (T[]) Array.newInstance(a.getClass().getComponentType(), 2);
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        mm[0] = min;
        mm[1] = max;
        return (T[]) mm;
    }
}

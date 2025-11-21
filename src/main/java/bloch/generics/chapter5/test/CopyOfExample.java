package bloch.generics.chapter5.test;

import java.util.Arrays;

public class CopyOfExample {
    public static void main(String[] args) {
        int[] original = {1, 2, 3, 4, 5};

        // Увеличение размера массива
        int[] largerCopy = Arrays.copyOf(original, 7);
        System.out.println(Arrays.toString(largerCopy)); // [1, 2, 3, 4, 5, 0, 0]

        // Уменьшение размера массива
        int[] smallerCopy = Arrays.copyOf(original, 3);
        System.out.println(Arrays.toString(smallerCopy)); // [1, 2, 3]

        // Копирование массива объектов
        String[] strings = {"A", "B", "C"};
        String[] copiedStrings = Arrays.copyOf(strings, 5);
        System.out.println(Arrays.toString(copiedStrings)); // [A, B, C, null, null]
    }
}
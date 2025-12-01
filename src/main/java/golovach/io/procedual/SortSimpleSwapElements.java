package golovach.io.procedual;

import java.util.Arrays;
import java.util.Collections;

public class SortSimpleSwapElements {

    public static void main(String[] args) {
        Integer[] arr = {9, 4, 8, 1, 2, 3};
        //int[] arr = {9, 4};
        //int[]  arrSorted = sortElements(arr);
        //inverseArray(arr);
        Integer[] arrSorted = sortElements(arr);
        System.out.println(Arrays.toString(arr));
        //Collections.sort();
        //Arrays.sort(arr);

        // Масив цілих чисел
        Integer[] intArray = {9, 4, 8, 1, 2, 3};
        Integer[] sortedIntArray = sortElements(intArray);
        System.out.println(Arrays.toString(sortedIntArray));

        // Масив рядків
        String[] stringArray = {"banana", "apple", "cherry", "date"};
        String[] sortedStringArray = sortElements(stringArray);
        System.out.println(Arrays.toString(sortedStringArray));
    }

//    private static void <E extends E> E sortSimpleSwapElements<E extends Comparable<E>>(List<E> original) {
//
//    }

//    private static int[] sortElements(int[] arr) {
//        for (int i = 0; i < arr.length - 1; i++) {
//            for (int j = i + 1; j < arr.length; j++) {
//                if (arr[i] > arr[j]) {
//                    int temp = arr[i];
//                    arr[i] = arr[j];
//                    arr[j] = temp;
//                }
//            }
//        }
//        return arr;
//    }

    private static <E extends Comparable<? super E>> E[] sortElements(E[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if ( arr[i].compareTo(arr[j])>0) {
                    E temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    private static void inverseArray(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}

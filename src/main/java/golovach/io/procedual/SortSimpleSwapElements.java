package golovach.io.procedual;

import java.util.Arrays;
import java.util.Collections;

public class SortSimpleSwapElements {

    public static void main(String[] args) {
        Integer[] arr = {9, 4, 8, 1, 2, 3};

        int[] arr1 = {9, 4, 8, 1, 2, 3};
        inverseArrayRec(arr1, 0);
        System.out.println("Inverse Rec: " + Arrays.toString(arr1));

        int[] arrReversion = {9, 4, 8, 1, 2, 3};
        sortElements(arrReversion);
        System.out.println("Reversion sort: " + Arrays.toString(arrReversion));

        int[] arrB = {9, 4, 8, 1, 2, 3};
        bubleSort(arrB);
        System.out.println("Buble sort: " + Arrays.toString(arrB));

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

        //fibonachi
        int i = 6;
        System.out.println("Fibonachi: " + i + " = " + fibonachi(i));

    }

//    private static void <E extends E> E sortSimpleSwapElements<E extends Comparable<E>>(List<E> original) {
//
//    }

    private static void sortElements(int[] arr) {
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.println(Arrays.toString(arr) + ": i=" + i);
            for (int j = i + 1; j < arr.length; j++) {
                System.out.println(Arrays.toString(arr) + ": i=" + i + " j=" + j);
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    System.out.println(Arrays.toString(arr) + ": i=" + i + " j=" + j);
                }
            }
            System.out.println("-------------------");
            System.out.println(Arrays.toString(arr));
        }
    }

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

    private static void inverseArrayRec(int[] arr, int k) {
        //for (int i = 0; i < arr.length / 2; i++) {
            if (k < arr.length /2) {
                int temp = arr[k];
                arr[k] = arr[arr.length - 1 - k];
                arr[arr.length - k -1] = temp;
                inverseArrayRec(arr, ++k);
            } else {
                return;
            }
        //}
    }

    private static void bubleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i -1 ; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    private static int fibonachi(int i) {
        if (i < 2) {
            return i;
        } else {
            return fibonachi(i - 1) + fibonachi(i - 2);
        }
    }
}

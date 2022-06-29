package bobo.interview.mockinterview;

import java.util.Arrays;

public class task01 {
    public static void main(String[] args) {
        reverseArray(new int[] {1,2,3,4,5});
    }

    private static void reverseArray(int[] arr) {

        int length = arr.length;
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr [length - i -1];
            arr[length -i -1] = temp;
        }

        System.out.println(Arrays.toString(arr));
    }

}

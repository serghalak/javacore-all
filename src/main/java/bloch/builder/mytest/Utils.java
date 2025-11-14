package bloch.builder.mytest;

public class Utils {

    public static void main(String[] args) {
        System.out.println(convertToTitleCase("hello word"));
    }

    public static String convertToTitleCase (String input) {

        if (input == null) {
            return "";
        }

        char[] arr = input.trim().replaceAll("\\s{2,}", " ").toCharArray();
        boolean isLetter = true;
        for (int i=0; i< arr.length; i++) {
            if (Character.isLetter(arr[i]) && i == 0) {
                arr[i] = Character.toUpperCase(arr[i]);
            }
            if (Character.isLetter(arr[i])) {
                if (!isLetter) {
                    arr[i] = Character.toUpperCase(arr[i]);
                } else {
                    arr[i] = Character.toLowerCase(arr[i]);
                    isLetter = true;
                }
            } else {
                isLetter = false;
            }
        }
        return new String(arr);
    }
}

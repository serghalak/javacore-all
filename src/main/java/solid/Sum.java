package solid;

import java.util.Scanner;

/**
 * Created by serg on 06.05.20.
 */
public class Sum {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("eneter number a = ");
        int a = scanner.nextInt();

        System.out.print("eneter number b = ");
        int b = scanner.nextInt();

        int sum = a+b;
        System.out.println("sum="+sum);

    }
}

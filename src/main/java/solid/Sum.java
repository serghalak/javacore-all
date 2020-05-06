package solid;

import solid.io.Data;

import java.util.Scanner;

/**
 * Created by serg on 06.05.20.
 */
public class Sum {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Data data=new Data(scanner);

        //System.out.print("eneter number a = ");
        data.print("eneter number a = ");
        //int a = scanner.nextInt();
        int a=data.read();
        //System.out.print("eneter number b = ");
        data.print("eneter number b = ");
        //int b = scanner.nextInt();
        int b=data.read();
        int sum = a+b;
        System.out.println("sum="+sum);

    }
}

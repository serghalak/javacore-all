package solid;

import solid.io.Data;
import solid.io.IData;

import java.util.Scanner;

/**
 * Created by serg on 06.05.20.
 */
public class Sum {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        IData data=new Data(scanner);

        //System.out.print("eneter number a = ");
        int a=data.input("eneter number a = ");
        //int a = scanner.nextInt();
        //int a=data.read();
        //System.out.print("eneter number b = ");
        int b=data.input("eneter number b = ");
        //int b = scanner.nextInt();
        //int b=data.read();
        int sum = a+b;
        System.out.println("sum="+sum);

    }
}

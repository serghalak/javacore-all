package solidvolosatov;

import solidvolosatov.io.Data;
import solidvolosatov.service.ServiceSum;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        //
        //System.out.println("Enter a: " );
        Data data=new Data(scanner);
        //data.print("Entere a: ");
        //end

        //
        //int a = scanner.nextInt();
        //int a = data.read();
        //end

        //
        //System.out.println("Enter b: " );
        //data.print("Enter b: ");
        //end


        //
        //int b = scanner.nextInt();
        //int b=data.read();
        //end


        int a = data.input("Enter a : ");
        int  b = data.input("Enter b: ");



        //int sum=a+b;
        ServiceSum serviceSum=new ServiceSum();
        int sum=serviceSum.calculate(a,b);
        //end

        //
        //System.out.println("Result: " );
        //System.out.println(sum);
        data.print("Result: ");
        data.print(sum);

    }

}

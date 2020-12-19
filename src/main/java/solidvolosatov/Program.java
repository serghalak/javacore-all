package solidvolosatov;

import solidvolosatov.io.*;
import solidvolosatov.model.Model;
import solidvolosatov.service.IService;
import solidvolosatov.service.ServiceSum;

import java.io.*;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        BufferedReader scanner=null;

        try {
            scanner=new BufferedReader(new FileReader("./numers.txt"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Scanner scanner=new Scanner(System.in);


        //IReader reader=new ReaderConsole(scanner);
        IReader reader=new ReaderFile(scanner);
        IPrinter printer=new PrinterConsole();
        //
        //System.out.println("Enter a: " );
        IData data=new Data(reader,printer);
        //data.print("Entere a: ");
        //end

        //int sum=a+b;
        IService serviceSum=new ServiceSum();


        Model model=new Model();
        model.init(data);
        model.calc(serviceSum);
        model.done(data);

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


        //int a = data.input("Enter a : ");
        //int  b = data.input("Enter b: ");
        //int sum=serviceSum.calculate(a,b);




        //
        //System.out.println("Result: " );
        //System.out.println(sum);
        //data.print("Result: ");
        //data.print(sum);

    }

}

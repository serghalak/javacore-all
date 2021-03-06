package solid.io;

import java.io.InputStream;
import java.util.Scanner;
import java.util.stream.Stream;


public class Data implements IData{

    private Scanner scanner;

    public Data(Scanner scanner) {
        this.scanner = scanner;
    }

    public void print (String text){
        System.out.print(text+" = ");
    }
    public void print(int number){
        System.out.println(number);
    }
    public int read(){
        return scanner.nextInt();
    }

    public int input(String text){
        print(text);
        return read();
    }
}

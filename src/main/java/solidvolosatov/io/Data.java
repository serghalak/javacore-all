package solidvolosatov.io;

import java.util.Scanner;

public class Data implements IData{

    //private Scanner scanner;

    private IReader reader;
    private IPrinter printer;

    public Data(IReader reader, IPrinter printer) {
        this.reader = reader;
        this.printer = printer;
    }

//    public Data(Scanner scanner) {
//        this.scanner = scanner;
//    }

    @Override
    public void print(String text){
        //System.out.print(text);
        printer.print(text);
    }

    @Override
    public void print(int number){
        //System.out.println(number);
        printer.print(number);
    }
    @Override
    public int read(){
        //return scanner.nextInt();
        return reader.read();
    }
//    @Override
//    public int input(String text){
//        print(text);
//        return read();
//    }
}

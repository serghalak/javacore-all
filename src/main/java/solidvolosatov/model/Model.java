package solidvolosatov.model;

import solidvolosatov.io.IData;
import solidvolosatov.io.IPrinter;
import solidvolosatov.service.IService;

public class Model {

    int a,b,sum;

    public void init(IData data){
        a=data.input("Enter a: ");
        b=data.input("Enter b: ");
    }
    public void calc(IService service){
        sum=service.calculate(a,b);
    }
    public void done(IPrinter printer){
        printer.print("Result: ");
        printer.print(sum);
    }
}

package horsman14.v1ch08.genericReflection;

import module java.base;

class GenericReflectionDemo {
    void main(String[] args) throws Exception {
        // read class name from command line args or user input
        String name = args.length > 0 ? args[0]
                : IO.readln("Enter class name (e.g., java.util.Collections): ");

        // print generic info for class and public methods
        Class<?> cl = Class.forName(name);
        Printer.printClass(cl);
        for (Method m : cl.getDeclaredMethods())
            Printer.printMethod(m);
    }
}
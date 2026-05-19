package horsman14.v1ch03;

public class IOExample {

    void main() {
        IO.println("Hello, world!");
        String name = IO.readln("What is your name?");
        IO.println(name);
        int age = Integer.parseInt(IO.readln("What is your age?"));
        IO.println(age);

//        String username = System.console().readLine("User name: ");
//        char[] passwd = System.console().readPassword("Password: ");
    }
}

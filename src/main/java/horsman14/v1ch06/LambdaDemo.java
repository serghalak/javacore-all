package v1ch06;

import module java.base;
import module java.desktop;

import javax.swing.Timer;

/**
 * This program demonstrates the use of lambda expressions.
 */
class LambdaDemo {
    void main() {
        var planets = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn",
                "Uranus", "Neptune"};
        IO.println(Arrays.toString(planets));
        IO.println("Sorted in dictionary order:");
        Arrays.sort(planets);
        IO.println(Arrays.toString(planets));
        IO.println("Sorted by length:");
        Arrays.sort(planets, (first, second) -> first.length() - second.length());
        IO.println(Arrays.toString(planets));

//        var timer = new Timer(1000, _ -> IO.println("The time is " + Instant.now()));
//        timer.start();
        Timer timer = new Timer(1000, IO::println);
        timer.start();
        additionTests();
        Greeter greeter = new Greeter();
        greeter.greet();

        // keep program running until user selects "OK"
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }

    private static void additionTests() {
        var dates = new Date[100];
        Arrays.setAll(dates, i -> new Date(i));
        IO.println(Arrays.toString(dates));
    }

    class Greeter {
        private String text;
        public void delayMessage(int delay) {
            ActionListener listener = event -> IO.println(text);
            // Ok to access field
            new Timer(delay, listener).start();
        }
        public void greet() {
            text = "Hello";
            delayMessage(1000);
            text = "Goodbye";
            delayMessage(2000);
        }
    }
}

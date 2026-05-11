package horsman14.v1ch06;

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

        var timer = new Timer(1000, _ -> IO.println("The time is " + Instant.now()));
        timer.start();

        // keep program running until user selects "OK"
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

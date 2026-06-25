package horsman14.v1ch05;

import horsman14.com.horstmann.corejava.*;

import java.util.Random;

class TypePatternDemo {
    void main() {
        int r = (int) (4 * Math.random());
        Employee e = switch (r) {
            case 0 -> new Employee("Harry Hacker", 50000, 1989, 10, 1);
            case 1 -> new Manager("Carl Cracker", 80000, 1987, 12, 15);
            case 2 -> new Executive("Sue Striver", "Senior Associate Vice President",
                    200000, 1995,1, 20);
            default -> null;
        };
//        String description = switch (e) {
//            case Executive exec when exec.getTitle().length() >= 20
//                    -> "An executive with an impressive title";
//            case Executive exec
//                    -> "An executive with a title of " + exec.getTitle();
//            case Manager m -> {
//                    m.setBonus(10000);
//                    yield "A manager who just got a bonus";
//                }
//            case null -> "No employee";
//            default -> "A lowly employee with a salary of " + e.getSalary();
//        };
//        IO.println(description);

        String description = switch (e) {
            case Executive exec -> "An executive with a title of " + exec.getTitle();
            case Manager _ -> "A manager who deserves a bonus ";
            case null -> "No employee";
            default -> "A lowly employee with a salary of " + e.getSalary();
        };
        IO.println(description);

        Point p = new Point(1,1);
        String descriptionPoint = switch (p) {
            case Point(var x, var y) when x == 0 && y == 0 -> "origin";
            case Point(var x, _) when x == 0 -> "on x-axis";
            case Point(_, var y) when y == 0 -> "on y-axis";
            default -> "not on either axis";
        };
        IO.println(descriptionPoint);

        int randomNum = new Random().nextInt(5) + 1;
        System.out.printf("Generated number is: %d.%n", randomNum);
        switch (randomNum) {
            case 1 -> System.out.println("One");
            case 2 -> System.out.println("Two");
            case 3 -> System.out.println("Three");
            case 4 -> System.out.println("Four");
            case 5 -> System.out.println("Five");
            default -> System.out.println("Invalid number");
        }
    }
}

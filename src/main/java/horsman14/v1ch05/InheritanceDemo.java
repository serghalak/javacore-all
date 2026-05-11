package horsman14.v1ch05;

import horsman14.com.horstmann.corejava.*;

/**
 * This program demonstrates inheritance.
 */
class InheritanceDemo {
    void main() {
        // construct a Manager object
        var boss = new Manager("Carl Cracker", 80000, 1987, 12, 15);
        boss.setBonus(5000);

        var staff = new Employee[3];

        // fill the staff array with Manager and Employee objects

        staff[0] = boss;
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tommy Tester", 40000, 1990, 3, 15);

        // print out information about all Employee objects
        for (Employee e : staff)
            IO.println("name=" + e.getName() + ",salary=" + e.getSalary());
    }
}

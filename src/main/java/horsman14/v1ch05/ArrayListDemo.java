package horsman14.v1ch05;

import module java.base;
import horsman14.com.horstmann.corejava.*;

/**
 * This program demonstrates the ArrayList class.
 */
class ArrayListDemo {
    void main() {
        // fill the staff array list with three Employee objects
        var staff = new ArrayList<Employee>();

        staff.add(new Employee("Carl Cracker", 75000, 1987, 12, 15));
        staff.add(new Employee("Harry Hacker", 50000, 1989, 10, 1));
        staff.add(new Employee("Tony Tester", 40000, 1990, 3, 15));

        // raise everyone's salary by 5%
        for (Employee e : staff)
            e.raiseSalary(5);

        // print out information about all Employee objects
        for (Employee e : staff)
            IO.println("name=" + e.getName() + ",salary=" + e.getSalary()
                    + ",hireDay=" + e.getHireDay());
    }
}

package horsman14.v1ch06.interfaces;

import module java.base;

/**
 * This program demonstrates the use of the Comparable interface.
 */
class InterfacesDemo {
    void main() {
        var staff = new Employee[3];

        staff[0] = new Employee("Harry Hacker", 35000);
        staff[1] = new Employee("Carl Cracker", 75000);
        staff[2] = new Employee("Tony Tester", 38000);

        Arrays.sort(staff);

        // print out information about all Employee objects
        for (Employee e : staff)
            IO.println("name=" + e.getName() + ",salary=" + e.getSalary());
    }
}

package horsman14.com.horstmann.corejava;
// the classes in this file are part of this package

import module java.base;
// import statements come after the package statement

/**
 * This class is almost identical to the initial Employee class, but it is inside
 * a package. Note that the class and its methods are public.
 */
public class Employee {
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        hireDay = LocalDate.of(year, month, day);
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }
}

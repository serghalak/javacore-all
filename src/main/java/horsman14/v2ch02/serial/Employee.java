package horsman14.v2ch02.serial;

import module java.base;

/**
 * The familiar Employee class, implementing the Serializable interface and with 
 * hireDay of type Date, not LocalDate.
 */
public class Employee implements Serializable {
    private String name;
    private double salary;
    private Date hireDay;

    public Employee(String n, double s, int year, int month, int day) {
        name = n;
        salary = s;
        hireDay = Date.from(LocalDate.of(year, month, day).atStartOfDay(ZoneId.systemDefault())
                .toInstant());
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Date getHireDay() {
        return hireDay;
    }

    /**
     * Raises the salary of this employee.
     * 
     * @param byPercent the percentage of the raise
     */
    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    public String toString() {
        return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay="
                + hireDay + "]";
    }
}

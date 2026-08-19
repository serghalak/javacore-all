package v1ch06.clone;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class EmployeeMy {

    private String name;
    private double salary;
    private Date hireDay;

    public EmployeeMy(String name, double salary) {
        this.name = name;
        this.salary = salary;
        hireDay = new Date();
    }

//    public EmployeeMy clone() throws CloneNotSupportedException {
//        // call Object.clone()
//        EmployeeMy cloned = (EmployeeMy) super.clone();
//
//        // clone mutable fields
//        cloned.hireDay = (Date) hireDay.clone();
//
//        return cloned;
//    }

    /**
     * Set the hire day to a given date.
     * @param year the year of the hire day
     * @param month the month of the hire day
     * @param day the day of the hire day
     */

    protected EmployeeMy getDeepCopy(String name, double salary) {
        return new EmployeeMy(name, salary);
    }

    public void setHireDay(int year, int month, int day) {
        long epochMillis = LocalDate.of(year, month, day).atStartOfDay(ZoneId.systemDefault())
                .toEpochSecond() * 1000;

        // example of instance field mutation
        hireDay.setTime(epochMillis);
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    public String toString() {
        return "Employee[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
    }
}

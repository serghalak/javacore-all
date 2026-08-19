package v1ch06.clone;

import java.util.Date;

/**
 * This program demonstrates cloning.
 */
class CloneDemo {
    void main() throws Exception {
        var original = new Employee("John Q. Public", 50000);
        Date hireDay = (Date) original.getHireDay().clone();

        hireDay.setYear(2026);
        hireDay.setMonth(1);
        hireDay.setDate(1);
        IO.println("hireDay=" + hireDay);
        //original.setHireDay(2000, 1, 1);
        Employee copy = original.clone();
        copy.raiseSalary(10);
        copy.setHireDay(hireDay);
        //copy.setHireDay(2002, 12, 31);
        IO.println("original=" + original);
        IO.println("copy=" + copy);
    }
}

package horsman14.v1ch06.clone;

/**
 * This program demonstrates cloning.
 */
class CloneDemo {
    void main() throws Exception {
        var original = new Employee("John Q. Public", 50000);
        original.setHireDay(2000, 1, 1);
        Employee copy = original.clone();
        copy.raiseSalary(10);
        copy.setHireDay(2002, 12, 31);
        IO.println("original=" + original);
        IO.println("copy=" + copy);
    }
}

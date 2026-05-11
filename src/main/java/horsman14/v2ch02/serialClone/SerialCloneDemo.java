package horsman14.v2ch02.serialClone;

class SerialCloneDemo {
    void main() throws Exception {
        var harry = new Employee("Harry Hacker", 35000, 1989, 10, 1);
        // clone harry
        var harry2 = (Employee) harry.clone();

        // mutate harry
        harry.raiseSalary(10);

        // now harry and the clone are different
        IO.println(harry);
        IO.println(harry2);
    }
}



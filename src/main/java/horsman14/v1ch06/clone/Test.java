package v1ch06.clone;

public class Test {

    static void main() {
        EmployeeMy e = new EmployeeMy("John Q. Public", 50000);
        EmployeeMy s = e.getDeepCopy("S", 1);
        
        IO.println(s);
        ManagerMy managerMy = new ManagerMy("Manager", 75000, 10000);
        managerMy.getDeepCopy("Ss", 11);
        IO.println(managerMy);
    }
}

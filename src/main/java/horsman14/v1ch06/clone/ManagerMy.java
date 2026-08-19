package v1ch06.clone;

public class ManagerMy extends EmployeeMy{
    private double bonus;

    public ManagerMy(String name, double salary, double bonus) {
        super(name, salary);
        this.bonus = bonus;
    }

//    protected ManagerMy getDeepCopy(String name, double salary) {
//        return new ManagerMy(name, salary, bonus);
//    }
}

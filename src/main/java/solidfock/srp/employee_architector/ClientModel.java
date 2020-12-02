package solidfock.srp.employee_architector;

public class ClientModel {
    public static void hireNewEmployee(Employee employee){
        EmployeeDao employeeDao=new EmployeeDao();
        employeeDao.saveEmployee(employee);
    }
    public static void terminateEmployee(Employee employee){
        EmployeeDao employeeDao=new EmployeeDao();
        employeeDao.deleteEmployee(employee);
    }


}

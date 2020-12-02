package solidfock.srp.employee_architector;

public class ClientModule {
    public static void main(String[] args) throws Exception {
        Employee e1=new Employee(1,"Andrew", "surgery", true);
        ClientModule.hireNewEmployee(e1);
        ClientModule.printEmployeeReport(e1,FormatType.CSV);
    }

    public static void hireNewEmployee(Employee employee){
        EmployeeDao employeeDao=new EmployeeDao();
        employeeDao.saveEmployee(employee);
    }

    public static void terminateEmployee(Employee employee){
        EmployeeDao employeeDao=new EmployeeDao();
        employeeDao.deleteEmployee(employee);
    }

    public static void printEmployeeReport(
            Employee employee,FormatType formatType) throws Exception {
        EmployeeReportFormatter formatter=new EmployeeReportFormatter(employee,formatType);
        System.out.println(formatter.getFormatterEmployee());
    }
}

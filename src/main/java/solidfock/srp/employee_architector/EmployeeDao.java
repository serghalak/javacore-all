package solidfock.srp.employee_architector;

public class EmployeeDao {

    public void saveEmployee(Employee employee){
        System.out.println(employee +" saved !!!");
    }

    public void deleteEmployee(Employee employee){
        System.out.println(employee + " deleted !!!!");
    }

    public static void printEmployeeReport(
            Employee employee,FormatType formatType) throws Exception {
        EmployeeReportFormatter formatter=new EmployeeReportFormatter(employee,formatType);
        System.out.println(formatter.getFormatterEmployee());
    }
}

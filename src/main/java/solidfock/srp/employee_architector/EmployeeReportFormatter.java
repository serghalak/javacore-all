package solidfock.srp.employee_architector;

public class EmployeeReportFormatter extends ReportFormatter {

    public EmployeeReportFormatter(Object o, FormatType formatType) throws Exception {
        super(o, formatType);
    }

    public String getFormatterEmployee(){
        return formatedOutput;
    }
}

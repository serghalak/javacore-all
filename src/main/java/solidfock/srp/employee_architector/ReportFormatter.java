package solidfock.srp.employee_architector;



public class ReportFormatter {

    String formatedOutput;


    public ReportFormatter(Object o , FormatType formatType) throws Exception {

        switch (formatType){
            case CSV:
                formatedOutput=convertedObjectToCSV(o);
                break;
            case XML:
                formatedOutput=convertObjectToXML(o);
                break;
            default:
                throw new Exception(formatType.name() + " not supported");
        }

    }

    private String convertObjectToXML(Object o){
        return "converted to XML " + o.toString();
    }

    private String convertedObjectToCSV(Object o){
        return "converted to CSV " + o.toString();
    }
}

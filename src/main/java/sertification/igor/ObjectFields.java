package sertification.igor;

public class ObjectFields {
    int iVar;
    static int cVar;

    void setFields(){
        this.iVar=22;
        this.cVar=22;
    }

    public static void main(String[] args) {
        ObjectFields of=new ObjectFields();
        of.setFields();
        System.out.println("iVar="+of.iVar+", cVar=" + of.cVar);
    }
}

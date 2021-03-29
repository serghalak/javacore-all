package sertification.igor;

public class TheSameNameFieldAndMethod {

    private static void sumaRozr(int i){

        int suma=0;
        int rizn=i;

        while(rizn>0){
            int ost=rizn%10;
            suma +=ost;
            rizn=(rizn-ost)/10;
        }
        System.out.println(suma);
    }


    static{
        a=1;
    }
    final static int a;

    int age;

    public TheSameNameFieldAndMethod(int age) {
        //a=10;
        this.age = age;
    }

    public static void main(String[] args) {
//        boolean True=1<2;
//        System.out.println(True);
//        int i=-5;
//        System.out.println(Integer.toBinaryString(i));
//        byte j= (byte) 0b10000101;
//        System.out.println((byte)0b11111011);
//        System.out.println(j);
//
//        System.out.println(0b101100);//44
//        System.out.println(0b11101);//29
//        System.out.println(0b1001111);//79   15
          System.out.println(Integer.toBinaryString(64));

        //sumaRozr(5);

        System.out.println((byte)0b10000000);

    }

    public static void main(Object args) {

    }

    public void age(int age){}
}

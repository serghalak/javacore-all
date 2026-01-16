package sertification.reactivejava.lesson01;


class Outer{

    static int a = 42;

    // static nested class
    static class Stinner{

        void run(){
            System.out.println(a);
        }
    }
}

//class Outer{
//
//    int a = 42;
//
//    // inner member class
//    class Inner{
//        void run(){
//            System.out.println(a);
//        }
//    }
//}



public class InnerClassesDemo {
    public static void main(String[] args) {

        //Outer.Inner inner = new Outer().new Inner();
//        inner.run();
        //System.out.println(inner);

        Outer.Stinner stinner = new Outer.Stinner();
        stinner.run();
        System.out.println(stinner);

    }
}

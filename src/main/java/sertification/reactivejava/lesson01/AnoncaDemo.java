package sertification.reactivejava.lesson01;

//@FunctionalInterface

interface Inter{
    void run();   // abs + instance
   // void doIt();
//    boolean equals(Object obj);
}



public class AnoncaDemo {

    public static void main(String[] args) {

        Inter inter1 = () -> System.out.println("Hello from lambda");
        inter1.run();

        Inter inter2 = () -> System.out.println("?!!!!!!!!!!!!");
        inter2.run();

//        Inter inter3 = new Inter() {
//            @Override
//            public void run() {
//                System.out.println("run()");
//            }
//
//            @Override
//            public void doIt() {
//                System.out.println("doIt()");
//            }
//        };

//        inter3.run();
//        inter3.doIt();


//        Inter inter1 = new Inter(){
//            @Override
//            public void run() {
//                System.out.println("running in Winter!");
//            }
//        };
//
//        Inter inter2 = new Inter(){
//            @Override
//            public void run() {
//                System.out.println("running in Winter!");
//            }
//        };
//
//
//        System.out.println(inter1);
//        System.out.println(inter2);
//        inter.run();


//        class Winter implements Inter{
//            @Override
//            public void run() {
//                System.out.println("running in Winter!");
//            }
//        }
//
//        Inter inter = new Winter();
//        inter.run();





    }
}

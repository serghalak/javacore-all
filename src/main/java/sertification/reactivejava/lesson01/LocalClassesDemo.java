package sertification.reactivejava.lesson01;

public class LocalClassesDemo {

    void walk(){
        class Local{
            int mlv = -19;
            void run(){
                System.out.println(mlv);
            }
        }
        System.out.println(new Local());

    }

    public static void main(String[] args) {
//        final
        int a = 12345;
        class Local{
            int mlv = -19;
            void run(){
                System.out.println(a);
            }
        }
        new Local().run();
//        new Local().run();
//        System.out.println(new Local());
//
        new LocalClassesDemo().walk();


    }
}


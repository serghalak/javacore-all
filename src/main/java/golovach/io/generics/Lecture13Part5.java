package golovach.io.generics;

import java.util.ArrayList;

public class Lecture13Part5 {
    public static void main(String[] args) {

    }

    private static void example01() {
        ArrayList<? extends Number> ref = new ArrayList<Integer>();
        Number n = null;
        //ref.add(n);
        ref.add(null);
        Number number = ref.get(0);
    }

    private static void example02() {
        ArrayList<? extends Integer> ref0 = new ArrayList<Integer>();
        ArrayList<? extends Number> ref1 = new ArrayList<Number>();

        //ref0 = ref1;
        ref1 = ref0;
    }

    private static void example03() {
        ArrayList<? super Integer> ref0 = new ArrayList<Integer>();
        ArrayList<? super Number> ref1 = new ArrayList<Number>();

        ref0 = ref1;
        //ref1 = ref0;
    }
}

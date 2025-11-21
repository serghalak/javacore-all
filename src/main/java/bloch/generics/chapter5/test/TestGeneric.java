package bloch.generics.chapter5.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestGeneric {
    public static void useGeneric() {
        List<? extends Number> list = List.of(1, 2, 3);
        //list.add(4); error
        System.out.println(list);



    }

    public static void listAndArrays() {
        Object[] objectArray = new Long[1];
        objectArray[0] = "I don't fit in";

        //List<Object> objectList = new ArrayList<Long>();
        //objectList.add("I don't fit in");
    }

    public static void useGenericExtends() {
        List<? extends Number> list = List.of(1, 2, 3);
        //list.add(4);
        System.out.println(list);
    }

    public static void main(String[] args) {
        //useGeneric();
        //listAndArrays();
        useGenericExtends();
    }
}

package bloch.generics.chapter5.item26;

import java.util.ArrayList;
import java.util.List;

// Fails at runtime - unsafeAdd method uses a raw type (List)!  (Page 119)
public class Raw {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("str1");
        strings.add("str2");
        List list =new ArrayList();
        list.add("str1");
        list.add("str2");
//        unsafeAdd(strings, Integer.valueOf(42));
//        String s = strings.get(0); // Has compiler-generated cast
        List<Object> objectList = new ArrayList<>();

        list = strings;
        list.add(42);
        System.out.println(list.get(2));
        final Class<List> listClass = List.class;


        //objectList = strings;//error

        //objectList.add("string");
        //objectList.add(5);
        //System.out.println(objectList.get(0));
        //System.out.println(objectList.get(1));



    }

    private static void unsafeAdd(List list, Object o) {
        list.add(o);
    }
}


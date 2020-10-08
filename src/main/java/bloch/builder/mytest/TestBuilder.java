package bloch.builder.mytest;

import bloch.builder.mytest.MyBuilder;

public class TestBuilder {

    public static void main(String[] args) {
        MyBuilder myBuilder=new MyBuilder.BuilderClass("Serg")
                .setLastName("Khalak")
                .setAge(46)
                .build();
        System.out.println(myBuilder);
    }
}

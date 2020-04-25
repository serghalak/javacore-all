package shild.ch15.lambda;

interface StringFunc{
    String func(String n);
}

class MyStringOps {
    /*static*/ String strReverse(String str){
        String result ="";
        int i;
        for ( i = str.length()-1; i >=0 ; i--) {
            result += str.charAt(i);
        }
        return result;
    }
}

public class MethodRefDemo{

    static String stringOp(StringFunc sf,String s){
        return sf.func(s);
    }

    public static void main(String[]args){
        String inStr=
            "Lambda expressions improve efficient java";
        String outStr;
        outStr=
                stringOp(
                    new MyStringOps()::strReverse,inStr);
        System.out.println("input string: " + inStr);
        System.out.println("inverse string: " + outStr);
    }
}

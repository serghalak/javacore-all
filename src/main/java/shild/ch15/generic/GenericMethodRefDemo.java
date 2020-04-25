package shild.ch15.generic;


public class GenericMethodRefDemo {

    static <T> int myOp(MyFunc<T> f, T[] vars,T var){
        return f.func(vars,var);
    }

    public static void main(String[] args) {
        Integer[] arrInt={1,2,4,4,8,6,5};
        String[] arrStr={"One", "Two","Two","Three","Two"};
        System.out.println("countInt: "
                + myOp(MyArrayOps::<Integer>countMatching
                ,arrInt,4));
        System.out.println("countStr: "
                +myOp(MyArrayOps::<String>countMatching
                ,arrStr,"Two"));

    }
}

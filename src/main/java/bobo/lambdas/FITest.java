package bobo.lambdas;

import java.util.function.*;
import java.util.stream.IntStream;

public class FITest {
    public static void main(String[] args) {
        Upper upper=s -> s.toUpperCase();
        System.out.println(upper.toUpperCase("hello"));

        Function<String, String>upperFunction=s -> s.toUpperCase();
        UnaryOperator<String>stringUnaryOperator=s -> s.toUpperCase();
        System.out.println(upperFunction.apply("hello!"));
        System.out.println(stringUnaryOperator.apply("hello"));

        Predicate<String>stringPredicate=s -> s.isEmpty();
        BiPredicate<String, String>stringBiPredicate=(s, s2) -> s.equals(s2);
        System.out.println(stringPredicate.test(""));
        System.out.println(stringBiPredicate.test("Hello","HEllo"));

        Consumer<String>stringConsumer=s -> System.out.println(s);
        stringConsumer.accept("Hi");

        Supplier<String>stringSupplier=() -> "Hello";
        System.out.println(stringSupplier.get());

        //IntStream.iterate(10, i -> i+1).filter().reduce(1,(left, right) -> left*right);
        int reduce = IntStream.rangeClosed(10, 20)
                .filter(a -> IntStream.range(2, a-1 ).anyMatch(i -> a % i == 0))
                .reduce(1, (x, y) -> x * y);
        System.out.println(reduce);
        int mult=1;
        for (int i=10;i<=20;i++){
            mult *=i;
        }
        System.out.println(mult);
    }
}

package bobo.lambdas;

import java.util.function.*;

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
    }
}

package bloch.chapter6.item34;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

// Enum type with constant-specific class bodies and data (Pages 163-4)
public enum Operation {
    PLUS("+", "plus") {
        public double apply(double x, double y) { return x + y; }
    },
    MINUS("-", "minus") {
        public double apply(double x, double y) { return x - y; }
    },
    TIMES("*", "times") {
        public double apply(double x, double y) { return x * y; }
    },
    DIVIDE("/", "divide") {
        public double apply(double x, double y) { return x / y; }
    };

    private final String symbol;
    private final String description;

    Operation(String symbol, String description) {
        this.symbol = symbol;
        this.description = description;
    }

    @Override public String toString() { return symbol + ": "  + description; }

    public abstract double apply(double x, double y);

    // Implementing a fromString method on an enum type (Page 164)
    private static final Map<String, Operation> stringToEnum =
            Stream.of(values()).collect(
                    toMap(Object::toString, e -> e));

    // Returns Operation for string, if any
    public static Optional<Operation> fromString(String symbol) {
        //return Optional.of(valueOf(symbol));
        return Optional.ofNullable(stringToEnum.get(symbol));
    }

    public static void main(String[] args) {
        double x = 2.0; //Double.parseDouble(args[0]);
        double y = 4.0; //Double.parseDouble(args[1]);
        for (Operation op : Operation.values()) {
            System.out.println(op + " " + valueOf(op.name()) );
            System.out.printf("%f %s %f = %f%n",
                    x, op, y, op.apply(x, y));
        }
    }
}

package horsman14.v1ch07;

/**
 * A program that displays a trace feature of a recursive method call.
 */
class StackTraceDemo {
    void main() {
        int n = Integer.parseInt(IO.readln("Enter n: "));
        factorial(n);
    }

    /**
     * Computes the factorial of a number
     * @param n a non-negative integer
     * @return n! = 1 * 2 * . . . * n
     */
    int factorial(int n) {
        IO.println("factorial(" + n + "):");
        var walker = StackWalker.getInstance();
        walker.forEach(IO::println);
        int r;
        if (n <= 1)
            r = 1;
        else
            r = n * factorial(n - 1);
        IO.println("return " + r);
        return r;
    }
}

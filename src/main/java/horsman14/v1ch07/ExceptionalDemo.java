package horsman14.v1ch07;

import module java.base;

class ExceptionalDemo {
    void main() {
        int i = 0;
        int ntry = 10000000;
        var stack = new Stack<String>();

        // test a stack for emptiness ntry times
        IO.println("Testing for empty stack");
        long start = System.nanoTime();
        for (i = 0; i <= ntry; i++)
            if (!stack.empty()) stack.pop();
        long end = System.nanoTime();
        IO.println("%.3f seconds".formatted((end - start) * 1E-9));

        // pop an empty stack ntry times and catch the resulting exception
        IO.println("Catching EmptyStackException");
        start = System.nanoTime();
        for (i = 0; i <= ntry; i++) {
            try {
                stack.pop();
            } catch (EmptyStackException e) {
            }
        }
        end = System.nanoTime();
        IO.println("%.3f seconds".formatted((end - start) * 1E-9));
    }
}

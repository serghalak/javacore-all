package horsman14.v1ch07;

import module java.base;

class ExceptDemo {
    void main() {
        int thousand = 1000;
        double[] a = { 1000, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        performAction("Integer divide by zero", () -> 1 / (a.length - a.length));

        performAction("Floating point divide by zero", () -> a[2] / (a[3] - a[3]));

        performAction("Integer overflow", () -> thousand * thousand * thousand * thousand);

        performAction("Square root of negative number", () -> Math.sqrt(-1));

        performAction("Array index out of bounds", () -> a[1] - a[100]);

        performAction("Bad cast", () -> (int[]) (Object) a);

        performAction("Null pointer", () -> System.getProperty("woozle").toString());

        performAction("No such file", () -> Files.readString(Path.of("woozle.txt")));
    }

    /**
     * Performs the given action and reports the result or failure.
     *
     * @param description the description of the action
     * @param action      the action to be carried out
     */
    void performAction(String description, Callable<Object> action) {
        IO.println(description);
        try {
            IO.println(action.call());
        }
        catch (Throwable t) {
            IO.println(t.getClass().getName() + ": " + t.getMessage());
        }
    }
}

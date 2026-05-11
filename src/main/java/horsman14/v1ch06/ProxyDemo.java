package horsman14.v1ch06;

import module java.base;
import java.lang.reflect.Proxy;

/**
 * This program demonstrates the use of proxies.
 */
class ProxyDemo {
    void main() {
        var elements = new Object[1000];

        // fill elements with proxies for the integers 1 . . . 1000
        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;
            var handler = new TraceHandler(value);
            Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                    new Class[]{Comparable.class}, handler);
            elements[i] = proxy;
        }

        // construct a random integer
        Integer key = (int) (Math.random() * elements.length) + 1;

        // search for the key
        int result = Arrays.binarySearch(elements, key);

        // print match if found
        if (result >= 0) IO.println(elements[result]);
    }
}

/**
 * An invocation handler that prints out the method name and parameters, then
 * invokes the original method.
 */
class TraceHandler implements InvocationHandler {
    private Object target;

    /**
     * Constructs a TraceHandler.
     *
     * @param t the implicit parameter of the method call
     */
    public TraceHandler(Object t) {
        target = t;
    }

    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        // print implicit argument
        IO.print(target);
        // print method name
        IO.print("." + m.getName() + "(");
        // print explicit arguments
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                IO.print(args[i]);
                if (i < args.length - 1) IO.print(", ");
            }
        }
        IO.println(")");

        // invoke actual method
        return m.invoke(target, args);
    }
}

package horsman14.v1ch08.limitations;

import module java.base;

import horsman14.com.horstmann.corejava.*;

public class NoGenericInstance {
    void main() throws Exception {
        Pair<Date> p = makePair(Date::new);
        IO.println(p.getFirst() + ", " + p.getSecond());

        p = makePair(Date.class);
        IO.println(p.getFirst() + ", " + p.getSecond());
        
        p = makePair();
        IO.println(p.getFirst() + ", " + p.getSecond());
    }

    // <T> Pair<T> makePair() { return new Pair<T>(new T(); new T()); }
    // new T() doesn't compile

    <T> Pair<T> makePair(Supplier<T> constr) {
        return new Pair<>(constr.get(), constr.get());
    }

    <T> Pair<T> makePair(Class<T> cl) throws ReflectiveOperationException {
        Constructor<T> constr = cl.getConstructor();
        return new Pair<>(constr.newInstance(), constr.newInstance());
    }
    
    @SuppressWarnings("unchecked")
    @SafeVarargs // requires static
    static <T> Pair<T> makePair(T... dummy) throws ReflectiveOperationException {
        assert dummy.length == 0;
        Constructor<?> constr = dummy.getClass().getComponentType().getConstructor();
        return new Pair<>((T) constr.newInstance(), (T) constr.newInstance());
    }    
}

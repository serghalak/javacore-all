package horsman14.v2ch02.serialClone;

import module java.base;

class CircularReferenceProblem {
    void main() throws Exception {
        var a = new ArrayList<Object>();
        var s = new HashSet<Object>();
        a.add(s);
        s.add(a); // Cyclic references between a and s
        IO.println(SerialCloneable.serialClone(s).size()); // 1
        IO.println(SerialCloneable.serialClone(a).size()); // NullPointerException
    }
}

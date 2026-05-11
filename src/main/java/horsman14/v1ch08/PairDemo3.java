package horsman14.v1ch08;

import horsman14.com.horstmann.corejava.*;

class PairDemo3 {
    void main() {
        var ceo = new Executive("Gus Greedy", "CEO",
                800000, 2003, 12, 15);
        var savp = new Executive("Sue Striver", "Senior Associate Vice President",
                200000, 1995,1, 20);;
        var buddies = new Pair<Manager>(ceo, savp);
        printBuddies(buddies);

        Executive[] executives = { ceo, savp };

        var result = new Pair<Employee>();
        IO.println("hasNulls: " + hasNulls(result));
        minmaxTitle(executives, result);
        IO.println("first: " + result.getFirst().getName() + ", second: "
                + result.getSecond().getName());
        maxminTitle(executives, result);
        IO.println("first: " + result.getFirst().getName() + ", second: "
                + result.getSecond().getName());
    }

    void printBuddies(Pair<? extends Employee> p) {
        Employee first = p.getFirst();
        Employee second = p.getSecond();
        IO.println(first.getName() + " and " + second.getName() + " are buddies.");
    }

    void minmaxTitle(Executive[] a, Pair<? super Executive> result) {
        if (a.length == 0) return;
        Executive smallest = a[0];
        Executive largest = a[0];
        for (int i = 1; i < a.length; i++) {
            if (smallest.getTitle().length() > a[i].getTitle().length()) smallest = a[i];
            if (largest.getTitle().length() < a[i].getTitle().length()) largest = a[i];
        }
        result.setFirst(smallest);
        result.setSecond(largest);
    }

    void maxminTitle(Executive[] a, Pair<? super Executive> result) {
        minmaxTitle(a, result);
        swap(result); // OK--swapHelper captures wildcard type
    }

    boolean hasNulls(Pair<?> p) {
        return p.getFirst() == null || p.getSecond() == null;
    }

    void swap(Pair<?> p) {
        swapHelper(p);
    }

    <T> void swapHelper(Pair<T> p) {
        T t = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
    }
}

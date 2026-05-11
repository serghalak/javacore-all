package horsman14.v1ch09;

import module java.base;

/**
 * This program demonstrates operations on linked lists.
 */
class LinkedListDemo {
    void main() {
        var a = new LinkedList<String>();
        a.add("Amy");
        a.add("Carl");
        a.add("Erica");

        var b = new LinkedList<String>();
        b.add("Bob");
        b.add("Doug");
        b.add("Frances");
        b.add("Gloria");

        // merge the elements from b into a

        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();

        while (bIter.hasNext()) {
            if (aIter.hasNext()) aIter.next();
            aIter.add(bIter.next());
        }

        IO.println(a);

        // remove every second element from b

        bIter = b.iterator();
        while (bIter.hasNext()) {
            bIter.next(); // skip one element
            if (bIter.hasNext()) {
                bIter.next(); // skip next element
                bIter.remove(); // remove that element
            }
        }

        IO.println(b);

        // bulk operation: remove all elements in b from a

        a.removeAll(b);

        IO.println(a);
    }
}

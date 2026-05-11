package horsman14.v1ch09;

import module java.base;

/**
 * This program sorts a set of Item objects by comparing their part numbers, 
 * then their descriptions.
 */
class TreeSetDemo {
    void main() {
        var parts = new TreeSet<Item>();
        parts.add(new Item("Toaster", 1234));
        parts.add(new Item("Widget", 4562));
        parts.add(new Item("Router", 9912));
        IO.println(parts);

        var sortByDescription = new TreeSet<Item>(Comparator.comparing(Item::description));

        sortByDescription.addAll(parts);
        IO.println(sortByDescription);
    }

    record Item(String description, int partNumber) implements Comparable<Item> {
        public int compareTo(Item other) {
            return Integer.compare(partNumber, other.partNumber);
        }
    }
}

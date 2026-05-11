package horsman14.v1ch09;

import module java.base;

/**
 * This program demonstrates the use of a priority queue.
 */
class PriorityQueueDemo {
    void main() {
        var pq = new PriorityQueue<LocalDate>();
        pq.add(LocalDate.of(1906, 12, 9)); // G. Hopper
        pq.add(LocalDate.of(1815, 12, 10)); // A. Lovelace
        pq.add(LocalDate.of(1903, 12, 3)); // J. von Neumann
        pq.add(LocalDate.of(1910, 6, 22)); // K. Zuse

        IO.println("Iterating over elements . . .");
        for (LocalDate date : pq)
            IO.println(date);
        IO.println("Removing elements . . .");
        while (!pq.isEmpty())
            IO.println(pq.remove());
    }
}

package horsman14.v1ch09.sieve;

import module java.base;

/**
 * This program runs the Sieve of Erathostenes benchmark. It computes all primes
 * up to 2,000,000.
 */
public class Sieve {
    void main() {
        int n = 2000000;
        long start = System.nanoTime();
        var bitSet = new BitSet(n + 1);
        int i;
        for (i = 2; i <= n; i++)
            bitSet.set(i);
        i = 2;
        while (i * i <= n) {
            if (bitSet.get(i)) {
                int k = i * i;
                while (k <= n) {
                    bitSet.clear(k);
                    k += i;
                }
            }
            i++;
        }
        long end = System.nanoTime();
        IO.println(bitSet.cardinality() + " primes");
        IO.println((end - start) / 1000 + " milliseconds");
    }
}

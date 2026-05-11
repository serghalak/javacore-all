package horsman14.v1ch08;

import horsman14.com.horstmann.corejava.Pair;

class PairDemo1 {
    void main() {
        String[] words = { "Mary", "had", "a", "little", "lamb" };
        Pair<String> mm = minmax(words);
        IO.println("min = " + mm.getFirst());
        IO.println("max = " + mm.getSecond());
    }

    /**
     * Gets the minimum and maximum of an array of strings.
     * @param a an array of strings
     * @return a pair with the min and max values, or null if a is null or empty
     */
    Pair<String> minmax(String[] a) {
        if (a == null || a.length == 0) return null;
        String smallest = a[0];
        String largest = a[0];
        for (int i = 1; i < a.length; i++) {
            if (smallest.compareTo(a[i]) > 0) smallest = a[i];
            if (largest.compareTo(a[i]) < 0) largest = a[i];
        }
        return new Pair<>(smallest, largest);
    }
}

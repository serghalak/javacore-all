package horsman14.v1ch09;

import module java.base;

/**
 * This program demonstrates the random shuffle and sort algorithms.
 */
class ShuffleDemo {
    void main() {
        var numbers = new ArrayList<Integer>();
        for (int i = 1; i <= 49; i++)
            numbers.add(i);
        Collections.shuffle(numbers, RandomGenerator.getDefault());
        List<Integer> winningCombination = numbers.subList(0, 6);
        Collections.sort(winningCombination);
        IO.println(winningCombination);
    }
}

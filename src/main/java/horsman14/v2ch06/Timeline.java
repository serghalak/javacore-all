package horsman14.v2ch06;

/**
 * This program demonstrates instants and durations.
 */

import module java.base;

public class Timeline {
    RandomGenerator generator = RandomGenerator.getDefault();

    void main() {
        Instant start = Instant.now();
        runAlgorithm();
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        long millis = timeElapsed.toMillis();
        IO.println("%d milliseconds".formatted(millis));

        Instant start2 = Instant.now();
        runAlgorithm2();
        Instant end2 = Instant.now();
        Duration timeElapsed2 = Duration.between(start2, end2);
        IO.println("%d milliseconds".formatted(timeElapsed2.toMillis()));
        boolean overTenTimesFaster = timeElapsed.multipliedBy(10).minus(timeElapsed2)
                .isNegative();
        IO.println("The first algorithm is %smore than ten times faster".formatted(
                overTenTimesFaster ? "" : "not "));
    }

    void runAlgorithm() {
        int size = 10;
        ArrayList<Integer> list = generator.ints().map(i -> i % 100).limit(size).boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.sort(list);
        IO.println(list);
    }

    void runAlgorithm2() {
        int size = 10;
        List<Integer> list = generator.ints().map(i -> i % 100).limit(size).boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        while (!IntStream.range(1, list.size())
                .allMatch(i -> list.get(i - 1).compareTo(list.get(i)) <= 0))
            Collections.shuffle(list);
        IO.println(list);
    }
}

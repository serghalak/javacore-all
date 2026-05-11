package horsman14.v2ch01.collecting;

import module java.base;

class CollectingResults {
    void main() throws Exception {
        // Turning a stream into an iterator
        Iterator<Integer> iter = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while (iter.hasNext())
            IO.println(iter.next());

        Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
        IO.println("Object array: " + numbers + " " + Arrays.toString(numbers));
        // Note it's an Object[] array

        try {
            var number = (Integer) numbers[0]; // OK
            IO.println("number: " + number);
            IO.println("The following statement throws an exception:");
            var _ = (Integer[]) numbers; // Throws exception
        }
        catch (ClassCastException e) {
            IO.println(e);
        }

        Integer[] numbers3 = Stream.iterate(0, n -> n + 1).limit(10).toArray(Integer[]::new);
        IO.println("Integer array: " + numbers3 + " " + Arrays.toString(numbers3));
        // Note it's an Integer[] array

        String result = noVowels().limit(10).collect(Collectors.joining());
        IO.println("Joining: " + result);
        result = noVowels().limit(10).collect(Collectors.joining(", "));
        IO.println("Joining with commas: " + result);

        IntSummaryStatistics summary = noVowels()
                .collect(Collectors.summarizingInt(String::length));
        double averageWordLength = summary.getAverage();
        double maxWordLength = summary.getMax();
        IO.println("Average word length: " + averageWordLength);
        IO.println("Max word length: " + maxWordLength);
        IO.println("forEach:");
        noVowels().limit(10).forEach(IO::println);
    }

    Stream<String> noVowels() throws IOException {
        String contents = Files.readString(Path.of("gutenberg", "alice30.txt"));
        Stream<String> words = Stream.of(contents.split("\\PL+"));
        return words.map(s -> s.replaceAll("[aeiouAEIOU]", ""));
    }

    <T> void show(String label, Set<T> set) {
        IO.print(label + ": " + set.getClass().getName());
        IO.println("["
            + set.stream().limit(10).map(Object::toString).collect(Collectors.joining(", "))
            + "]");
    }
}

package horsman14.v2ch01.parallel;

import static java.util.stream.Collectors.*;

import module java.base;

class ParallelStreams {
    void main() throws Exception {
        String contents = Files.readString(Path.of("gutenberg", "alice30.txt"));
        List<String> wordList = List.of(contents.split("\\PL+"));

        // Very bad code ahead
        var shortWords = new int[10];
        wordList.parallelStream().forEach(s -> {
            if (s.length() < 10) shortWords[s.length()]++;
        });
        IO.println(Arrays.toString(shortWords));

        // Try again--the result will likely be different (and also wrong)
        Arrays.fill(shortWords, 0);
        wordList.parallelStream().forEach(s -> {
            if (s.length() < 10) shortWords[s.length()]++;
        });
        IO.println(Arrays.toString(shortWords));

        // Remedy: Group and count
        Map<Integer, Long> shortWordCounts = wordList.parallelStream()
                .filter(s -> s.length() < 10).collect(groupingBy(String::length, counting()));

        IO.println(shortWordCounts);

        // Downstream order not deterministic
        Map<Integer, List<String>> result = wordList.parallelStream()
                .collect(Collectors.groupingByConcurrent(String::length));

        IO.println(result.get(14));

        result = wordList.parallelStream()
                .collect(Collectors.groupingByConcurrent(String::length));

        IO.println(result.get(14));

        Map<Integer, Long> wordCounts = wordList.parallelStream()
                .collect(groupingByConcurrent(String::length, counting()));

        IO.println(wordCounts);
    }
}

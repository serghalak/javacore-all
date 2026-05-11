package horsman14.v1ch09;

import module java.base;

/**
 * This program compares insertion into a hash set and a tree set. You can specify
 * a filename and a repetition count on the command line.
 */
class SetDemo {
    void main(String[] args) throws Exception {
        List<String> words = new ArrayList<>();
        String filename = args.length > 0 ? args[0] : "gutenberg/crsto10.txt";
        int repetitions = args.length > 1 ? Integer.parseInt(args[1]) : 1;
        try (var in = new Scanner(Path.of(filename))) {
            while (in.hasNext()) {
                String word = in.next();
                words.add(word);
            }
        }
        time(new HashSet<>(), words, repetitions);
        time(new TreeSet<>(), words, repetitions);
    }

    void time(Set<String> wordSet, List<String> wordList, int repetitions) {
        long totalTime = 0;
        for (int i = 1; i <= repetitions; i++) {
            for (String word : wordList) {
                long start = System.nanoTime();
                wordSet.add(word);
                long end = System.nanoTime();
                totalTime += end - start;
            }
        }
        Iterator<String> iter = wordSet.iterator();
        for (int i = 1; i <= 20 && iter.hasNext(); i++)
            IO.print(iter.next() + " ");
        IO.println("...");
        IO.println("%s: %d words, %d distinct, %.3f seconds.".formatted(
                wordSet.getClass().getSimpleName(), wordList.size(), wordSet.size(),
                totalTime * 1E-9));
    }

}

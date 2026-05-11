package horsman14.v1ch10;

import module java.base;

/**
 * This program demonstrates concurrent hash maps.
 */
class ConcurrentHashMapDemo {
    ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

    void main() throws Exception {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        Path pathToRoot = Path.of(".");
        for (Path p : descendants(pathToRoot)) {
            if (p.getFileName().toString().endsWith(".java"))
                executor.execute(() -> process(p));
        }
        executor.close();
        map.forEach((k, v) -> {
            if (v >= 10) IO.println(k + " occurs " + v + " times");
        });
    }

    /**
     * Adds all words in the given file to the concurrent hash map.
     * @param file a file
     */
    void process(Path file) {
        try (var in = new Scanner(file)) {
            while (in.hasNext()) {
                String word = in.next();
                map.merge(word, 1L, Long::sum);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns all descendants of a given directory--see Chapters 1 and 2 of Volume II
     * @param rootDir the root directory
     * @return a set of all descendants of the root directory
     */
    Set<Path> descendants(Path rootDir) throws IOException {
        try (Stream<Path> entries = Files.walk(rootDir)) {
            return entries.collect(Collectors.toSet());
        }
    }
}

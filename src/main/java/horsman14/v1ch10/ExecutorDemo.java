package horsman14.v1ch10;

import module java.base;

/**
 * This program demonstrates the Callable interface and executors.
 */
class ExecutorDemo {
    void main() throws Exception {
        String start = IO.readln("Enter base directory (e.g. /opt/jdk-25-src): ");
        String word = IO.readln("Enter keyword (e.g. volatile): ");

        Set<Path> files = descendants(Path.of(start));
        var tasks = new ArrayList<Callable<Long>>();
        for (Path file : files) {
            Callable<Long> task = () -> occurrences(word, file);
            tasks.add(task);
        }
        ExecutorService executor = Executors.newCachedThreadPool();
        // use a single thread executor instead to see if multiple threads speed up the search
        // ExecutorService executor = Executors.newSingleThreadExecutor();
        // Or try virtual threads
        // ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        Instant startTime = Instant.now();
        List<Future<Long>> results = executor.invokeAll(tasks);
        Instant endTime = Instant.now();
        long total = 0;
        for (Future<Long> result : results)
            total += result.get();
        IO.println("Occurrences of " + word + ": " + total);
        IO.println(
                "Time elapsed: " + Duration.between(startTime, endTime).toMillis() + " ms");

        var searchTasks = new ArrayList<Callable<Path>>();
        for (Path file : files)
            searchTasks.add(searchForTask(word, file));
        startTime = Instant.now();
        Path found = executor.invokeAny(searchTasks);
        endTime = Instant.now();
        IO.println(word + " occurs in: " + found);
        IO.println(
                "Time elapsed: " + Duration.between(startTime, endTime).toMillis() + " ms");

        executor.close();
    }

    /**
     * Counts occurrences of a given word in a file.
     * @return the number of times the word occurs in the given word
     */
    long occurrences(String word, Path path) {
        try (var in = new Scanner(path)) {
            int count = 0;
            while (in.hasNext())
                if (in.next().equals(word)) count++;
            return count;
        }
        catch (IOException ex) {
            return 0;
        }
    }

    /**
     * Returns all descendants of a given directory--see Chapters 1 and 2 of Volume II.
     * @param rootDir the root directory
     * @return a set of all descendants of the root directory
     */
    Set<Path> descendants(Path rootDir) throws IOException {
        try (Stream<Path> entries = Files.walk(rootDir)) {
            return entries.filter(Files::isRegularFile).collect(Collectors.toSet());
        }
    }

    /**
     * Yields a task that searches for a word in a file.
     * @param word the word to search
     * @param path the file in which to search
     * @return the search task that yields the path upon success
     */
    Callable<Path> searchForTask(String word, Path path) {
        return () -> {
            try (var in = new Scanner(path)) {
                while (in.hasNext()) {
                    if (in.next().equals(word)) return path;
                    if (Thread.currentThread().isInterrupted()) {
                        IO.println("Search in " + path + " canceled.");
                        return null;
                    }
                }
                throw new NoSuchElementException();
            }
        };
    }

}

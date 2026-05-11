package horsman14.v1ch10;

import module java.base;

class BlockingQueueDemo {
    final int FILE_QUEUE_SIZE = 10;
    final int SEARCH_THREADS = 100;
    final Path TERMINATION = Path.of("");

    BlockingQueue<Path> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    void main() {
        String directory = IO.readln("Enter base directory (e.g. /tmp/jdk-21-src): ");
        String keyword = IO.readln("Enter keyword (e.g. volatile): ");

        Runnable enumerator = () -> {
            try {
                enumerate(Path.of(directory));
                queue.put(TERMINATION);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (InterruptedException e) {
            }
        };

        Thread.ofPlatform().start(enumerator);
        for (int i = 1; i <= SEARCH_THREADS; i++) {
            Runnable searcher = () -> {
                try {
                    boolean done = false;
                    while (!done) {
                        Path file = queue.take();
                        if (file == TERMINATION) {
                            queue.put(file);
                            done = true;
                        } else
                            search(file, keyword);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch (InterruptedException e) {
                }
            };
            Thread.ofPlatform().start(searcher);
        }
    }

    /**
     * Recursively enumerates all files in a given directory and its subdirectories.
     * See Chapters 1 and 2 of Volume II for the stream and file operations.
     * @param directory the directory in which to start
     */
    void enumerate(Path directory) throws IOException, InterruptedException {
        try (Stream<Path> children = Files.list(directory)) {
            for (Path child : children.toList()) {
                if (Files.isDirectory(child))
                    enumerate(child);
                else
                    queue.put(child);
            }
        }
    }

    /**
     * Searches a file for a given keyword and prints all matching lines.
     * @param file the file to search
     * @param keyword the keyword to search for
     */
    void search(Path file, String keyword) throws IOException {
        try (var in = new Scanner(file)) {
            int lineNumber = 0;
            while (in.hasNextLine()) {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword))
                    IO.println("%s:%d:%s".formatted(file, lineNumber, line));
            }
        }
    }
}

package horsman14.v2ch02.findDirectories;

import module java.base;

public class FindDirectories {
    void main(String[] args) throws Exception {
        Path dir = Path.of(args.length == 0 ? System.getProperty("user.home") : args[0]);
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                if (attrs.isDirectory()) IO.println(file);
                return FileVisitResult.CONTINUE;
            }

            public FileVisitResult visitFileFailed(Path file, IOException e)
                    throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }
}

package horsman14.v2ch02.zip;

import module java.base;

class ZipDemo {
    void main(String[] args) throws Exception {
        String zipname = args[0];
        showContentsClassic(zipname);
        IO.println("---");
        showContentsZipFS(zipname);
    }

    void showContentsClassic(String zipname) throws IOException {
        // Here, we use the classic zip API
        try (var zin = new ZipInputStream(new FileInputStream(zipname))) {
            boolean done = false;
            while (!done) {
                ZipEntry entry = zin.getNextEntry();
                if (entry == null)
                    done = true;
                else {
                    IO.println(entry.getName());
                    String content = new String(zin.readAllBytes());
                    content.lines().forEach(line -> IO.println("    " + line));
                    // DO NOT CLOSE zin
                    zin.closeEntry();
                }
            }
        }
    }

    void showContentsZipFS(String zipname) throws IOException {
        FileSystem fs = FileSystems.newFileSystem(Path.of(zipname));
        Files.walkFileTree(fs.getPath("/"), new SimpleFileVisitor<Path>() {
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs)
                    throws IOException {
                IO.println(path);
                Files.lines(path).forEach(line -> IO.println("    " + line));
                return FileVisitResult.CONTINUE;
            }
        });
    }
}

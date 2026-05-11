package horsman14.v2ch02.memoryMap;

import module java.base;

/**
 * This program computes the CRC checksum of a file in four ways.
 */
public class MemoryMapDemo {
    void main(String[] args) throws Exception {
        Path filename = Path.of(args.length > 0 ? args[0]
                : IO.readln("Enter the path to a large file: "));
        IO.println("Input Stream:");
        long start = System.nanoTime();
        long crcValue = checksumInputStream(filename);
        long end = System.nanoTime();
        IO.println(Long.toHexString(crcValue));
        IO.println("%.3f seconds".formatted((end - start) * 1E-9));

        IO.println("Buffered Input Stream:");
        start = System.nanoTime();
        crcValue = checksumBufferedInputStream(filename);
        end = System.nanoTime();
        IO.println(Long.toHexString(crcValue));
        IO.println("%.3f seconds".formatted((end - start) * 1E-9));

        IO.println("Random Access File:");
        start = System.nanoTime();
        crcValue = checksumRandomAccessFile(filename);
        end = System.nanoTime();
        IO.println(Long.toHexString(crcValue));
        IO.println("%.3f seconds".formatted((end - start) * 1E-9));

        IO.println("Mapped File:");
        start = System.nanoTime();
        crcValue = checksumMappedFile(filename);
        end = System.nanoTime();
        IO.println(Long.toHexString(crcValue));
        IO.println("%.3f seconds".formatted((end - start) * 1E-9));
    }

    long checksumInputStream(Path filename) throws IOException {
        try (InputStream in = Files.newInputStream(filename)) {
            var crc = new CRC32();
            boolean done = false;
            while (!done) {
                int c = in.read();
                if (c == -1)
                    done = true;
                else
                    crc.update(c);
            }
            return crc.getValue();
        }
    }

    long checksumBufferedInputStream(Path filename) throws IOException {
        try (var in = new BufferedInputStream(Files.newInputStream(filename))) {
            var crc = new CRC32();

            boolean done = false;
            while (!done) {
                int c = in.read();
                if (c == -1)
                    done = true;
                else
                    crc.update(c);
            }
            return crc.getValue();
        }
    }

    long checksumRandomAccessFile(Path filename) throws IOException {
        try (var file = new RandomAccessFile(filename.toFile(), "r")) {
            long length = file.length();
            var crc = new CRC32();

            for (long p = 0; p < length; p++) {
                int c = file.readByte();
                crc.update(c);
            }
            return crc.getValue();
        }
    }

    long checksumMappedFile(Path filename) throws IOException {
        try (FileChannel channel = FileChannel.open(filename)) {
            var crc = new CRC32();
            int length = (int) channel.size();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);

            for (int p = 0; p < length; p++) {
                int c = buffer.get(p);
                crc.update(c);
            }
            return crc.getValue();
        }
    }
}

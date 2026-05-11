package horsman14.v2ch01.streams;

import module java.base;

class CountLongWords {
    void main() throws Exception {
        String contents = Files.readString(Path.of("gutenberg", "alice30.txt"));
        String[] words = contents.split("\\PL+");

        long count = 0;
        for (String w : words) {
            if (w.length() > 12) count++;
        }
        IO.println(count);

        count = Stream.of(words).filter(w -> w.length() > 12).count();
        IO.println(count);

        count = Stream.of(words).parallel().filter(w -> w.length() > 12).count();
        IO.println(count);
    }
}

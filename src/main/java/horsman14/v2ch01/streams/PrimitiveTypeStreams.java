package horsman14.v2ch01.streams;

import module java.base;

class PrimitiveTypeStreams {
    void main() throws Exception {
        IntStream is1 = IntStream.generate(() -> (int) (Math.random() * 100));
        show("is1", is1);
        IntStream is2 = IntStream.range(5, 10);
        show("is2", is2);
        IntStream is3 = IntStream.rangeClosed(5, 10);
        show("is3", is3);

        String contents = Files.readString(Path.of("gutenberg", "alice30.txt"));

        Stream<String> words = Stream.of(contents.split("\\PL+"));
        IntStream is4 = words.mapToInt(String::length);
        show("is4", is4);
        String sentence = "\uD835\uDD46 is the set of octonions.";
        IO.println(sentence);
        IntStream codes = sentence.codePoints();
        IO.println(codes.mapToObj(c -> "%X ".formatted(c)).collect(Collectors.joining()));

        Stream<Integer> integers = IntStream.range(0, 100).boxed();
        IntStream is5 = integers.mapToInt(Integer::intValue);
        show("is5", is5);
    }

    void show(String title, IntStream stream) {
        final int SIZE = 10;
        int[] firstElements = stream.limit(SIZE + 1).toArray();
        IO.print(title + ": ");
        for (int i = 0; i < firstElements.length; i++) {
            if (i > 0) IO.print(", ");
            if (i < SIZE)
                IO.print(firstElements[i]);
            else
                IO.print("...");
        }
        IO.println();
    }
}

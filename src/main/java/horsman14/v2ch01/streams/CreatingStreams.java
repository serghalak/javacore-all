package horsman14.v2ch01.streams;

import module java.base;

class CreatingStreams {
    void main() throws Exception {
        Path path = Path.of("gutenberg", "alice30.txt");
        String contents = Files.readString(path);
        Stream<String> words = Stream.of(contents.split("\\PL+"));

        show("words", words);
        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        show("song", song);
        Stream<String> silence = Stream.empty();
        show("silence", silence);

        Stream<String> echos = Stream.generate(() -> "Echo");
        show("echos", echos);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms", randoms);

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE,
                n -> n.add(BigInteger.ONE));
        show("integers", integers);

        Stream<String> greetings = "Hello\nGuten Tag\nBonjour".lines();
        show("greetings", greetings);

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);
        show("wordsAnotherWay", wordsAnotherWay);

        try (Stream<String> lines = Files.lines(path)) {
            show("lines", lines);
        }

        Iterable<Path> iterable = FileSystems.getDefault().getRootDirectories();
        Stream<Path> rootDirectories = StreamSupport.stream(iterable.spliterator(), false);
        show("rootDirectories", rootDirectories);

        Iterator<Path> iterator = Path.of("/usr/share/dict/words").iterator();
        Stream<Path> pathComponents = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
        show("pathComponents", pathComponents);
    }

    <T> void show(String title, Stream<T> stream) {
        final int SIZE = 10;
        List<T> firstElements = stream.limit(SIZE + 1).toList();
        IO.print(title + ": ");
        for (int i = 0; i < firstElements.size(); i++) {
            if (i > 0) IO.print(", ");
            if (i < SIZE)
                IO.print(firstElements.get(i));
            else
                IO.print("...");
        }
        IO.println();
    }

}

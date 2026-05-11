package horsman14.v2ch01.streams;

import module java.base;

class FilterMapDemo {
    void main() throws Exception {
        String contents = Files.readString(Path.of("gutenberg", "alice30.txt"));
        List<String> words = List.of(contents.split("\\PL+"));
        Stream<String> longWords = words.stream().filter(w -> w.length() > 12);
        show("longWords", longWords);

        Stream<String> lowercaseWords = words.stream().map(String::toLowerCase);
        show("lowercaseWords", lowercaseWords);

        String[] song = { "row", "row", "row", "your", "boat", "gently", "down", "the",
                "stream" };
        Stream<Character> firstCodeUnits = Stream.of(song).filter(w -> w.length() > 0)
                .map(s -> s.charAt(0));
        show("firstCodeUnits", firstCodeUnits);

        Stream<String> letters = Stream.of(song).flatMap(w -> graphemeClusters(w));
        show("letters", letters);

        BreakIterator iter = BreakIterator.getCharacterInstance();
        Stream<String> result = words.stream().mapMulti((s, collector) -> {
            iter.setText(s);
            int start = iter.first();
            int end = iter.next();
            while (end != BreakIterator.DONE) {
                String gc = s.substring(start, end);
                start = end;
                end = iter.next();
                collector.accept(gc);
            }
        });
        show("result", result);
    }

    <T> void show(String title, Stream<T> stream) {
        final int SIZE = 10;
        List<T> firstElements = stream.limit(SIZE + 1).toList();
        IO.print(title + ": ");
        if (firstElements.size() <= SIZE)
            IO.println(firstElements);
        else {
            String out = firstElements.subList(0, SIZE).toString();
            IO.println(out.substring(0, out.length() - 1) + ", ...]");
        }
    }

    Stream<String> graphemeClusters(String s) {
        return Stream.of(s.split("\\b{g}"));
    }
}

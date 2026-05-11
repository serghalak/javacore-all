package horsman14.v2ch01.collecting;

import module java.base;

class CollectingIntoCollections {
    void main() throws Exception {
        Set<String> noVowelSet = noVowels().collect(Collectors.toSet());
        show("noVowelSet", noVowelSet);

        TreeSet<String> noVowelTreeSet = noVowels()
                .collect(Collectors.toCollection(TreeSet::new));
        show("noVowelTreeSet", noVowelTreeSet);
    }

    Stream<String> noVowels() throws IOException {
        String contents = Files.readString(Path.of("gutenberg", "alice30.txt"));
        Stream<String> words = Stream.of(contents.split("\\PL+"));
        return words.map(s -> s.replaceAll("[aeiouAEIOU]", ""));
    }

    <T> void show(String label, Set<T> set) {
        IO.print(label + ": " + set.getClass().getName());
        IO.println("["
            + set.stream().limit(10).map(Object::toString).collect(Collectors.joining(", "))
            + "]");
    }
}

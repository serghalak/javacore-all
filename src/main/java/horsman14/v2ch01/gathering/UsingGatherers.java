package horsman14.v2ch01.gathering;

import module java.base;

class UsingGatherers {
    void main() {
        IO.println(IntStream.range(0, 10).boxed().gather(Gatherers.windowFixed(4)).toList());
        IO.println(IntStream.range(0, 10).boxed().gather(Gatherers.windowSliding(4)).toList());
        
        int max = 10; // Maximumum number of concurrent threads
        List<String> contents = IntStream.range(0, 20).mapToObj(_ -> "https://horstmann.com/random/word")
            .gather(Gatherers.mapConcurrent(max, this::read))
            .toList();
        IO.println(contents);
        
        int n = Stream.of(1, 7, 2, 9)
                .gather(Gatherers.fold(() -> 0, (x, y) -> x * 10 + y))
                .findFirst()
                .orElse(0);
        IO.println(n);
        List<Integer> intermediateResults = Stream.of(1, 7, 2, 9)
                .gather(Gatherers.scan(() -> 0, (x, y) -> x * 10 + y))
                .toList();
        IO.println(intermediateResults);
    }
    
    String read(String url) {
        try {
            return new String(URI.create(url).toURL().openStream().readAllBytes());
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }    
}

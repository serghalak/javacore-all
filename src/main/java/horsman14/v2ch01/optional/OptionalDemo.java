package horsman14.v2ch01.optional;

import module java.base;

class OptionalDemo {
    void main() throws Exception {
        String contents = Files.readString(Path.of("gutenberg", "alice30.txt"));
        List<String> wordList = List.of(contents.split("\\PL+"));
    
        Optional<String> optionalValue = wordList
                .stream()
                .filter(s -> s.contains("fred"))
                .findFirst();
        IO.println(optionalValue.orElse("No word") + " contains fred");
    
        Optional<String> optionalString = Optional.empty();
        String result = optionalString.orElse("N/A");
        IO.println("result: " + result);
        result = optionalString.orElseGet(() -> Locale.getDefault().getDisplayName());
        IO.println("result: " + result);
        try {
            result = optionalString.orElseThrow(IllegalStateException::new);
            IO.println("result: " + result);
        }
        catch (Throwable t) {
            t.printStackTrace(System.out);
        }
    
        optionalValue = wordList.stream().filter(s -> s.contains("red")).findFirst();
        optionalValue.ifPresent(s -> IO.println(s + " contains red"));
    
        var results = new HashSet<String>();
        optionalValue.ifPresent(results::add);
        Optional<Boolean> added = optionalValue.map(results::add);
        IO.println(added);
    
        IO.println(inverse(4.0).flatMap(this::squareRoot));
        IO.println(inverse(-1.0).flatMap(this::squareRoot));
        IO.println(inverse(0.0).flatMap(this::squareRoot));
        Optional<Double> result2 = Optional.of(-4.0)
                .flatMap(this::inverse)
                .flatMap(this::squareRoot);
        IO.println(result2);
    }
    
    Optional<Double> inverse(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }
    
    Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }
}

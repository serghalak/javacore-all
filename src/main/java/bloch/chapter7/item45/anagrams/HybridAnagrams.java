package bloch.chapter7.item45.anagrams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

// Tasteful use of streams enhances clarity and conciseness (Page 205)
public class HybridAnagrams {
    public static void main(String[] args) throws IOException {
        //Path dictionary = Paths.get(args[0]);
        Path dictionary = Paths.get("src/main/java/bloch/chapter7/item45/anagrams/words.txt");
        //args[1] = "5";
        int minGroupSize = 2; //Integer.parseInt(args[1]);

        try (Stream<String> words = Files.lines(dictionary)) {
//            Map<String, List<String>> collect = words.collect(groupingBy(word -> alphabetize(word)));
//            System.out.println(collect.values());
//
//            System.out.println(collect);

//                    .values().stream()
//                    .filter(group -> group.size() >= minGroupSize)
//                    .forEach(g -> System.out.println(g.size() + ": " + g));

            words.collect(groupingBy(word -> alphabetize(word)))
                    .values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .forEach(g -> System.out.println(g.size() + ": " + g));
        }
    }

    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}

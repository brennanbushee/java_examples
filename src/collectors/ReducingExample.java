package collectors;

import java.util.*;
import java.util.stream.*;

public class ReducingExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("demonstration", "stream", "Java", "Collectors", "example", "aardvark");
//      These two are equivalent.
        words.stream().max(Comparator.comparing(String::length)).ifPresent(System.out::println);
        Optional<String> longestWord = words.stream()
                .collect(Collectors.reducing((s1, s2) -> s1.length() > s2.length() ? s1 : s2));
//        Which word is last alphabetically?
        words.stream().map(String::toLowerCase).max(String::compareTo).ifPresent(System.out::println);
//        words.stream().map(String::toLowerCase).map(String::compareTo).toList();
        longestWord.ifPresent(System.out::println); // Output: collectors
    }
}

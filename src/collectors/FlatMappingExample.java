package collectors;

import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

public class FlatMappingExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "blueberry", "avocado", "cherry", "cherry");
        System.out.println(words);
//      First letters mapped to endings, three different ways.
        Map<Character, List<Character>> result = words.stream()
                .collect(groupingBy(
                        word -> word.charAt(0),
                        Collectors.flatMapping(word -> word.substring(1).chars().mapToObj(c -> (char) c),
                                Collectors.toList()
                        )
                ));
        // Output: {a=[p, p, l, e, v, o, c, a, d, o], b=[a, n, a, n, a], c=[h, e, r, r, y]}
        System.out.println("The flat mapping way: " + result);
        Map<String, String> joinedWords = words.stream().collect(
                Collectors.toMap(
                        word -> word.substring(0, 1),
                        word -> word.substring(1),
                        (end1, end2) -> end1.concat(",".concat(end2))
                )
        );
        System.out.println("May include duplicates: " + joinedWords);
        Map<String, Set<String>> endingsByLetter =
                words.stream().collect(Collectors.groupingBy(
                                word -> word.substring(0,1),
                                TreeMap::new,
                                mapping(word -> word.substring(1), toSet())
                        )
                );
        System.out.println("The grouping by way:" + endingsByLetter);
//        {a=[vocado, pple], b=[lueberry, anana], c=[herry]}

    }
}

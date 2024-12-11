package parallel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.*;

public class ParallelStreamExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "apple", "cherry", "banana", "apple");
//        The following are equivalent.
        ConcurrentMap<String, Long> wordCounts = words.parallelStream()
                .collect(Collectors.toConcurrentMap(
                        Function.identity(),
                        w -> 1L, Long::sum
                        )
                );
        Map<String, Long> counts = words.stream().collect(
                Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                )
        );
        System.out.println(counts);
        System.out.println(wordCounts); // Output: {banana=2, cherry=1, apple=3}
    }
}

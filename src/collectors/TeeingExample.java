package collectors;

import java.util.*;
import java.util.stream.*;

public class TeeingExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        IntSummaryStatistics stats = numbers.stream().collect(Collectors.summarizingInt(Integer::intValue));
        stats.accept(6);
        System.out.println(stats);
        IntStream.of(7,8,9,10).forEach(stats);
//        Stats Should now be computed up to 10
        System.out.println(stats);
//      Double everything
        stats.combine(stats);
        System.out.println(stats);
        Map<String, Double> result = numbers.stream()
                .collect(Collectors.teeing(
                        Collectors.averagingInt(Integer::intValue),
                        Collectors.summingDouble(Double::valueOf),
                        (average, sum) -> {
                            Map<String, Double> map = new HashMap<>();
                            map.put("average", average);
                            map.put("sum", sum);
                            return map;
                        }
                ));

        System.out.println(result); // Output: {average=3.0, sum=15.0}
    }
}

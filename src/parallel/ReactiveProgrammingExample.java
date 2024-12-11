package parallel;
import reactor.core.publisher.Flux;

public class ReactiveProgrammingExample {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .map(i -> i * 2)
                .filter(i -> i % 3 == 0)
                .subscribe(System.out::println);
    }
}

package reactive;

import reactor.core.publisher.Flux;

public class ErrorHandlingExample {

    public static void main(String[] args) {
        Flux<Integer> numbers = Flux.range(1, 5)
                .map(i -> 10 / (i - 3)) // Will throw an exception when i == 3
                .onErrorResume(e -> Flux.just(-1)); // Fallback on error

        numbers.subscribe(System.out::println);
    }
}

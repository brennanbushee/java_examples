package reactive;


import reactor.core.publisher.Mono;

public class AsyncTaskCombinationExample {

    public static void main(String[] args) {
        Mono<String> task1 = Mono.fromCallable(() -> {
            Thread.sleep(1000);
            return "Task 1";
        });

        Mono<String> task2 = Mono.fromCallable(() -> {
            Thread.sleep(1500);
            return "Task 2";
        });
        Mono<String> combined = task1.zipWith(task2, (result1, result2) -> result1 + " + " + result2);

        combined.subscribe(System.out::println);
    }
}


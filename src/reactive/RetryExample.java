package reactive;
import reactor.core.publisher.Flux;
import java.time.Duration;

public class RetryExample {

    public static void main(String[] args) {
        Flux<String> unreliableSource = Flux.<String>generate(sink -> {
            if (Math.random() < 0.7) {
                System.out.println("Error occurred");
                sink.error(new RuntimeException("Temporary issue"));
            } else {
                sink.next("Success");
                sink.complete();
            }
        });

        unreliableSource
                .retry(5)
                .timeout(Duration.ofSeconds(1))
//                .retryBackoff(5, Duration.ofSeconds(1))
                .subscribe(
                        System.out::println,
                        e -> System.err.println("Failed: " + e.getMessage())
                );
    }
}


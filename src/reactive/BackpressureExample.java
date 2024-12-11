package reactive;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class BackpressureExample {

    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 100)
                .onBackpressureDrop()
                .onBackpressureBuffer(10) // Buffer the first 10 elements, then apply backpressure
                .publishOn(Schedulers.parallel())
                .subscribe(
                        i -> {
                            try {
                                Thread.sleep(100); // Simulate slow consumer
                                System.out.println("Consumed " + i);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        },
                        e -> System.err.println("Error: " + e),
                        () -> System.out.println("Done")
                );

        Thread.sleep(5000); // Allow time for all items to be processed
    }
}


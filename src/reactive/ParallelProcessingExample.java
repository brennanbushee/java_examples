package reactive;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ParallelProcessingExample {

    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 10)
                .parallel()
                .runOn(Schedulers.parallel())
                .map(i -> Math.pow(i,2))
                .collectSortedList(Double::compareTo)
//                .sequential() // Convert back to sequential to subscribe
                .subscribe(System.out::println);

        Thread.sleep(1000); // Allow time for all items to be processed
    }
}


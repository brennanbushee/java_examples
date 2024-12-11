package reactive;

import reactor.core.publisher.Flux;

public class EventProcessingExample {

    public static void main(String[] args) {
        Flux<String> events = Flux.just("Event1", "Event2", "ErrorEvent", "Event3", "Event4");
        events.filter(event -> !event.contains("Error"))
                .map(String::toUpperCase)
                .buffer(2)
                .subscribe(
                        batch -> System.out.println("Processed batch: " + batch),
                        e -> System.err.println("Error: " + e),
                        () -> System.out.println("Processing complete")
                );
    }
}


import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class SchedulerManagementExample {

    public static void main(String[] args) {
        Mono.fromCallable(() -> {
            System.out.println("Executing in thread: " + Thread.currentThread().getName());
            return "Result";
        })
        .subscribeOn(Schedulers.boundedElastic())
        .subscribe(result -> System.out.println("Received: " + result));

        // Keep the application running
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

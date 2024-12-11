package parallel;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Double> future1 = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running task
            sleep(1000);
            return 10.0;
        });

        CompletableFuture<Double> future2 = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running task
            sleep(2000);
            return 2.0;
        });

        CompletableFuture<Double> combinedFuture = future1.thenCombine(future2, Math::pow);
        System.out.println("Combined result: " + combinedFuture.get()); // Outputs 30
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


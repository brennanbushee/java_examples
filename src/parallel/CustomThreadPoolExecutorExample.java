package parallel;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPoolExecutorExample {
    public static void main(String[] args) {
        try (ThreadPoolExecutor executor = new ThreadPoolExecutor(
                4, // corePoolSize
                10, // maximumPoolSize
                60, // keepAliveTime
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100) // workQueue
        )) {
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
            for (int i = 0; i < 200; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    System.out.println("Executing task " + taskId + " on thread " + Thread.currentThread().getName());
                    sleep(500);
                });
            }

            executor.shutdown();
        }
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


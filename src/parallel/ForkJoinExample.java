package parallel;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinExample {

    public static void main(String[] args) {
        Integer result;
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            FibonacciTask task = new FibonacciTask(30);
            result = forkJoinPool.invoke(task);
        }
        System.out.println("Fibonacci number: " + result);
    }

    static class FibonacciTask extends RecursiveTask<Integer> {
        private final int n;

        FibonacciTask(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }

            FibonacciTask task1 = new FibonacciTask(n - 1);
            task1.fork(); // asynchronously execute the subtask

            FibonacciTask task2 = new FibonacciTask(n - 2);
            int result2 = task2.compute();

            int result1 = task1.join(); // wait for the subtask to complete

            return result1 + result2;
        }
    }
}


package parallel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class CachedFibonacciForkJoin {

    private static final ConcurrentHashMap<Integer, Integer> cache = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FibonacciTask task = new FibonacciTask(30);
        Integer result = forkJoinPool.invoke(task);
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

            // Check if value is already cached
            if (cache.containsKey(n)) {
                return cache.get(n);
            }

            // Compute n-2 directly
            FibonacciTask task2 = new FibonacciTask(n - 2);
            int result2 = task2.compute();

            // Fork n-1 to allow parallel execution
            FibonacciTask task1 = new FibonacciTask(n - 1);
            task1.fork();

            int result1 = task1.join(); // Wait for the n-1 task to complete

            int result = result1 + result2;

            // Cache the computed result
            cache.put(n, result);

            return result;
        }
    }
}



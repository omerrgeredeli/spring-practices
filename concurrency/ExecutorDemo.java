package concurrency;

import java.util.List;
import java.util.concurrent.*;

public class ExecutorDemo {
    public void runExecutorExample() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        try {
            List<Future<Integer>> futures = executor.invokeAll(List.of(
                    callable(1, 200),
                    callable(2, 300),
                    callable(3, 400),
                    callable(4, 500),
                    callable(5, 600)
            ));

            for (Future<Integer> f : futures) {
                try {
                    System.out.println("Result: " + f.get());
                } catch (ExecutionException ee) {
                    System.err.println("Task failed: " + ee.getCause());
                }
            }

            Future<String> f = executor.submit(() -> {
                TimeUnit.MILLISECONDS.sleep(200);
                return "done";
            });
            System.out.println("Future single: " + f.get(1, TimeUnit.SECONDS));

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } catch (TimeoutException  e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }
    }

    static Callable<Integer> callable(int id, int sleepMs) {
        return () -> {
            TimeUnit.MILLISECONDS.sleep(sleepMs);
            System.out.println("Task " + id + " finished on " + Thread.currentThread().getName());
            return id * 100; // basit örnek
        };
    }
}

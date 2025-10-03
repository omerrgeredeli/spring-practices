package concurrency2;

import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceExample {

    // Callable üreten yardımcı method (1'den n'e kadar sayıları toplar)
    static Callable<Integer> callable(int id, int n) {
        return () -> {
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += i;
                System.out.println("Callable " + id + " adding: " + i + " on " + Thread.currentThread().getName());
                TimeUnit.MILLISECONDS.sleep(200); // Simülasyon amaçlı bekleme
            }
            System.out.println("Callable " + id + " finished on " + Thread.currentThread().getName());
            return sum;
        };
    }

    // Runnable üreten yardımcı method (1'den n'e kadar yazdırır)
    static Runnable runnable(int id, int n) {
        return () -> {
            for (int i = 1; i <= n; i++) {
                System.out.println("Runnable " + id + " prints: " + i + " on " + Thread.currentThread().getName());
                try {
                    TimeUnit.MILLISECONDS.sleep(150); // Simülasyon amaçlı bekleme
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Runnable " + id + " was interrupted");
                    return;
                }
            }
            System.out.println("Runnable " + id + " finished on " + Thread.currentThread().getName());
        };
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            // Callable task’ler
            List<Callable<Integer>> callables = List.of(
                    callable(1, 5),
                    callable(2, 3)
            );

            List<Future<Integer>> callableResults = executor.invokeAll(callables);

            // Runnable task’ler
            Future<?> r1 = executor.submit(runnable(1, 5));
            Future<?> r2 = executor.submit(runnable(2, 5));

            // Runnable task’lerin bitmesini bekleyelim
            r1.get(); // null döner
            r2.get();

            // Callable sonuçlarını yazdır
            for (Future<Integer> f : callableResults) {
                System.out.println("Callable Result: " + f.get());
            }

        } finally {
            executor.shutdown();
        }
    }
}

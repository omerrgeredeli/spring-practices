package concurrency2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample{

    // Runnable tarzı görev: 1’den n’e kadar yazdırır
    static void runTask(int id, int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println("Runnable Task " + id + " prints: " + i + " on " + Thread.currentThread().getName());
            try {
                TimeUnit.MILLISECONDS.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Runnable Task " + id + " was interrupted");
                return;
            }
        }
        System.out.println("Runnable Task " + id + " finished on " + Thread.currentThread().getName());
    }

    // Callable tarzı görev: 1’den n’e kadar toplam hesaplar ve döndürür
    static int computeSum(int id, int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
            System.out.println("Callable Task " + id + " adding: " + i + " on " + Thread.currentThread().getName());
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Callable Task " + id + " was interrupted");
                return 0;
            }
        }
        System.out.println("Callable Task " + id + " finished on " + Thread.currentThread().getName());
        return sum;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // Runnable görevleri başlat
        CompletableFuture<Void> runnable1 = CompletableFuture.runAsync(() -> runTask(1, 5));
        CompletableFuture<Void> runnable2 = CompletableFuture.runAsync(() -> runTask(2, 5));

        // Callable görevleri başlat
        CompletableFuture<Integer> callable1 = CompletableFuture.supplyAsync(() -> computeSum(1, 5));
        CompletableFuture<Integer> callable2 = CompletableFuture.supplyAsync(() -> computeSum(2, 3));

        // Tüm Runnable görevlerin bitmesini bekle
        CompletableFuture.allOf(runnable1, runnable2).join();

        // Callable sonuçlarını al ve yazdır
        int result1 = callable1.get();
        int result2 = callable2.get();

        System.out.println("Callable Task 1 Result: " + result1);
        System.out.println("Callable Task 2 Result: " + result2);

        // Tüm görevlerin bitmesini bekle
        CompletableFuture.allOf(runnable1, runnable2, callable1, callable2).join();
        System.out.println("All tasks completed!");
    }
}

package concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableDemo {
    private final Account account;

    public CompletableDemo(Account account) {
        this.account = account;
    }

    public void runCompletableFutureExample() {
        CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(300); // Simülasyon: DB’den balance çekme
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Balance fetched on " + Thread.currentThread().getName());
                    return account.getBalance();  // 🔑 Account’tan balance çağırıyoruz
                })
                .thenApply(balance -> {
                    int withBonus = (int) (balance * 1.1);
                    System.out.println("Bonus applied: " + withBonus);
                    return withBonus;
                })
                .thenAccept(finalBalance ->
                        System.out.println("Final balance: " + finalBalance)
                )
                .exceptionally(ex -> {
                    System.err.println("Hata yakalandı: " + ex.getMessage());
                    return null;
                });

        // Main hemen kapanmasın diye bekletiyoruz
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

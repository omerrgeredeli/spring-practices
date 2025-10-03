package concurrency;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("=== Multithreading & Concurrency Playground ===");

        // 1. Runnable & Thread
        Account account = new Account(1000);
        Thread t1 = new Thread(new AccountTask(account, true, 200));
        Thread t2 = new Thread(new AccountTask(account, false, 150));
        // TODO: t1 ve t2 başlat

        // 2. ExecutorService & Future
        ExecutorDemo executorDemo = new ExecutorDemo();
        executorDemo.runExecutorExample();

        // 3. CompletableFuture
        CompletableDemo cfDemo = new CompletableDemo(account);
        cfDemo.runCompletableFutureExample();

        // 4. Race Condition
        RaceConditionDemo raceDemo = new RaceConditionDemo();
        raceDemo.runRaceCondition();

        // 5. Lock
        LockDemo lockDemo = new LockDemo();
        lockDemo.runWithLock();

        // 6. Deadlock
        DeadlockDemo deadlockDemo = new DeadlockDemo();
        deadlockDemo.createDeadlock();
        // deadlockDemo.preventDeadlock(); // Çözüm için

        System.out.println("Deneme");
    }
}

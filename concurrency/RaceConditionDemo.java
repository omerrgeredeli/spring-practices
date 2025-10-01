package concurrency;

public class RaceConditionDemo {
    private int counter = 0;

    public void runRaceCondition() {
        System.out.println("Starting Race Condition Demo...");

        // 🔹 Thread 1: increment 1000 kere
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter++; // race condition olacak
            }
        }, "IncrementThread");

        // 🔹 Thread 2: decrement 1000 kere
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter--; // race condition olacak
            }
        }, "DecrementThread");

        // Start threads
        t1.start();
        t2.start();

        // Wait for threads to finish
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Final counter (without sync, race condition): " + counter);

        // 🔹 Synchronized version
        counter = 0; // reset
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) increment();
        }, "IncrementThreadSync");

        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) decrement();
        }, "DecrementThreadSync");

        t3.start();
        t4.start();

        try {
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Final counter (with synchronized): " + counter);
    }

    public synchronized void increment() {
        counter++;
    }

    public synchronized void decrement() {
        counter--;
    }
}

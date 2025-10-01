package concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    private int counter = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void runWithLock() {
        System.out.println("Starting Lock Demo...");

        // 🔹 Thread 1: increment 1000 kere
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                increment();
            }
        }, "IncrementThread");

        // 🔹 Thread 2: decrement 1000 kere
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                decrement();
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

        System.out.println("Final counter with lock: " + counter);
    }

    public void increment() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            counter--;
        } finally {
            lock.unlock();
        }
    }
}

package concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class DeadlockDemo {
    private final Object lockA = new Object();
    private final Object lockB = new Object();

    private final Lock rLockA = new ReentrantLock();
    private final Lock rLockB = new ReentrantLock();

    // 🔹 Deadlock yaratacak yöntem
    public void createDeadlock() {
        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("Thread1: locked lockA");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (lockB) {
                    System.out.println("Thread1: locked lockB");
                }
            }
        }, "Thread1");

        Thread t2 = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("Thread2: locked lockB");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (lockA) {
                    System.out.println("Thread2: locked lockA");
                }
            }
        }, "Thread2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Deadlock demo finished (actually may hang)!");
    }

    // 🔹 Deadlock önleme: lock ordering / tryLock
    public void preventDeadlock() {
        Thread t1 = new Thread(() -> {
            try {
                if (rLockA.tryLock(500, TimeUnit.MILLISECONDS)) {
                    System.out.println("Thread1: locked rLockA");
                    try { Thread.sleep(100); } catch (InterruptedException e) {}
                    if (rLockB.tryLock(500, TimeUnit.MILLISECONDS)) {
                        System.out.println("Thread1: locked rLockB");
                        rLockB.unlock();
                    }
                    rLockA.unlock();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Thread1");

        Thread t2 = new Thread(() -> {
            try {
                if (rLockA.tryLock(500, TimeUnit.MILLISECONDS)) { // lock ordering: A -> B
                    System.out.println("Thread2: locked rLockA");
                    try { Thread.sleep(100); } catch (InterruptedException e) {}
                    if (rLockB.tryLock(500, TimeUnit.MILLISECONDS)) {
                        System.out.println("Thread2: locked rLockB");
                        rLockB.unlock();
                    }
                    rLockA.unlock();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Thread2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Deadlock prevented successfully!");
    }
}

package concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private int balance;
    private final ReentrantLock lock = new ReentrantLock();

    public Account(int balance){
        this.balance = balance;
    }
    // synchronized versiyon
    public synchronized void deposit(int amount) {
        balance = balance + amount;
    }

    public synchronized void withdraw(int amount) {
        balance = balance - amount;
    }

    // Lock versiyonu
    public void depositWithLock(int amount) {
        lock.lock();
        try{
            balance = balance + amount;
        } finally {
            lock.unlock();
        }
    }

    public void withdrawWithLock(int amount) {
        lock.lock();
        try {
            balance = balance - amount;
        } finally {
            lock.unlock();
        }
    }

    public int getBalance() {
        return balance;
    }
}

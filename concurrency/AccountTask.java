package concurrency;

public class AccountTask implements Runnable{
    private final Account account;
    private final boolean depositOperation; // true=deposit, false=withdraw
    private final int amount;

    public AccountTask(Account account, boolean depositOperation, int amount) {
        this.account = account;
        this.depositOperation = depositOperation;
        this.amount = amount;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        if (depositOperation) {
            account.deposit(amount);
            System.out.println(threadName + " deposited " + amount
                    + " | Balance: " + account.getBalance());
        } else {
            account.withdraw(amount);
            System.out.println(threadName + " withdrew " + amount
                    + " | Balance: " + account.getBalance());
        }
    }
}

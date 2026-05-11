package horsman14.v1ch10.synch2;

import module java.base;

/**
 * A bank with a number of bank accounts that uses synchronization primitives.
 */
public class Bank {
    private final double[] accounts;

    /**
     * Constructs the bank.
     * @param n the number of accounts
     * @param initialBalance the initial balance for each account
     */
    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    /**
     * Transfers money from one account to another.
     * @param from the account to transfer from
     * @param to the account to transfer to
     * @param amount the amount to transfer
     */
    public synchronized void transfer(int from, int to, double amount)
            throws InterruptedException {
        while (accounts[from] < amount)
            wait();
        IO.print(Thread.currentThread());
        accounts[from] -= amount;
        IO.print(" %10.2f from %d to %d".formatted(amount, from, to));
        accounts[to] += amount;
        IO.println(" Total Balance: %10.2f".formatted(getTotalBalance()));
        notifyAll();
    }

    /**
     * Gets the sum of all account balances.
     * @return the total balance
     */
    public synchronized double getTotalBalance() {
        double sum = 0;

        for (double a : accounts)
            sum += a;

        return sum;
    }

    /**
     * Gets the number of accounts in the bank.
     * @return the number of accounts
     */
    public int size() {
        return accounts.length;
    }
}

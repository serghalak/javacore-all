package horsman14.v1ch10.unsynch;

import module java.base;

/**
 * A bank with a number of bank accounts.
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
    public void transfer(int from, int to, double amount) {
        if (accounts[from] < amount) return;
        IO.print(Thread.currentThread());
        accounts[from] -= amount;
        IO.print(" %10.2f from %d to %d".formatted(amount, from, to));
        accounts[to] += amount;
        IO.println(" Total Balance: %10.2f".formatted(getTotalBalance()));
    }

    /**
     * Gets the sum of all account balances.
     * @return the total balance
     */
    public double getTotalBalance() {
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

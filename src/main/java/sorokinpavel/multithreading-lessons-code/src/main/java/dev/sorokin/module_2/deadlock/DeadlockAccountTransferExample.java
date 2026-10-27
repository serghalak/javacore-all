package dev.sorokin.module_2.deadlock;

import dev.sorokin.utils.Logger;
import dev.sorokin.utils.ThreadJoinUtils;


public class DeadlockAccountTransferExample {

    public static class Account {
        private int id;
        private int balance;
        private final Object lock = new Object();

        public Account(
                int id,
                int balance
        ) {
            this.id = id;
            this.balance = balance;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var account1 = new Account(1, 100);
        var account2 = new Account(2, 100);

        for (int i = 0; i < 100000; ++i) {
            System.out.println("Iteration " + i + " started");
            Thread thread1 = new Thread(() -> makeTransfer(account1, account2, 100), "worker-1");
            Thread thread2 = new Thread(() -> makeTransfer(account2, account1, 100), "worker-2");

            thread1.start();
            thread2.start();
            ThreadJoinUtils.safeJoin(thread1, thread2);

            System.out.println("Iteration " + i + " ended");
        }

        System.out.println("All iterations ended");
    }

    public static void makeTransfer(
            Account accountFrom,
            Account accountTo,
            int amount
    ) {
        var firstLock = accountFrom.lock.hashCode() <= accountTo.lock.hashCode()
                ? accountFrom : accountTo;
        var secondLock = firstLock == accountFrom
                ? accountTo : accountFrom;

        synchronized (firstLock.lock) {
            synchronized (secondLock.lock) {
                Logger.log("Transfer from acc %s to acc %s", accountFrom.id, accountTo.id);
                accountFrom.balance -= amount;
                accountTo.balance += amount;
            }
        }
    }
}
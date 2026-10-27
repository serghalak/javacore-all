package dev.sorokin.module_2.deadlock;

import dev.sorokin.utils.Logger;
import dev.sorokin.utils.ThreadJoinUtils;
import dev.sorokin.utils.ThreadSleepUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockTryLockFixedExample {

    public static class Account {
        int id;
        int balance;
        final ReentrantLock lock = new ReentrantLock();

        public Account(int id, int balance) {
            this.id = id;
            this.balance = balance;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var account1 = new Account(1, 100);
        var account2 = new Account(2, 100);

        for (int i = 0; i < 10000; ++i) {
            System.out.println("Iteration " + i + " started");
            Thread thread1 = new Thread(() -> makeTransfer(account1, account2, 100));
            Thread thread2 = new Thread(() -> makeTransfer(account2, account1, 100));
            thread1.start();
            thread2.start();
            ThreadJoinUtils.safeJoin(thread1, thread2);
            System.out.println("Iteration " + i + " ended");
        }

        Logger.log("All iterations ended");
        Logger.log(
                "Final balance acc-1: %s acc-2: %s",
                account1.balance,
                account2.balance
        );
    }

    public static void makeTransfer(Account accountFrom, Account accountTo, int amount) {
        while (true) {
            try {
                if (!accountFrom.lock.tryLock(10, TimeUnit.MILLISECONDS)) {
                    Logger.log("Не удалось взять лок с acc %s. Retry..", accountFrom.id);
                    ThreadSleepUtil.safeSleepRandomMillis(5, 15);
                    continue;
                }

                if (!accountTo.lock.tryLock(10, TimeUnit.MILLISECONDS)) {
                    Logger.log("Не удалось взять лок с acc %s. Откатываем и retry..", accountTo.id);
                    accountFrom.lock.unlock();
                    ThreadSleepUtil.safeSleepRandomMillis(5, 15);
                    continue;
                }

                Logger.log("Transfer from acc %s to acc %s", accountFrom.id, accountTo.id);
                accountFrom.balance -= amount;
                accountTo.balance += amount;
                return;

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            } finally {
                if (accountTo.lock.isHeldByCurrentThread()) {
                    accountTo.lock.unlock();
                }
                if (accountFrom.lock.isHeldByCurrentThread()) {
                    accountFrom.lock.unlock();
                }
            }
        }
    }
}

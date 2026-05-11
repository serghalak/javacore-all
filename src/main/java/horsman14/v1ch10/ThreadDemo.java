package horsman14.v1ch10;

import horsman14.v1ch10.unsynch.Bank;

/**
 * This program demonstrates the use of two concurrent threads.
 */

class ThreadDemo {
    final int NACCOUNTS = 100;
    final double INITIAL_BALANCE = 1000;
    final double MAX_AMOUNT = 1000;
    final int DELAY = 10;
    final int STEPS = 100;

    void main() {
        Runnable task1 = () -> {
            var bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
            try {
                for (int i = 0; i < STEPS; i++) {
                    int fromAccount = (int) (bank.size() * Math.random());
                    int toAccount = (int) (bank.size() * Math.random());
                    double amount = MAX_AMOUNT * Math.random();
                    bank.transfer(fromAccount, toAccount, amount);
                    Thread.sleep((int) (DELAY * Math.random()));
                }
            }
            catch (InterruptedException e) {
            }
        };

        Runnable task2 = () -> {
            var bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
            try {
                for (int i = 0; i < STEPS; i++) {
                    int fromAccount = (int) (bank.size() * Math.random());
                    int toAccount = (int) (bank.size() * Math.random());
                    double amount = MAX_AMOUNT * Math.random();
                    bank.transfer(fromAccount, toAccount, amount);
                    Thread.sleep((int) (DELAY * Math.random()));
                }
            }
            catch (InterruptedException e) {
            }
        };

        new Thread(task1).start();
        new Thread(task2).start();
    }
}

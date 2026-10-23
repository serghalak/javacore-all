package dev.sorokin.module_2.multithreading_signaling;

import dev.sorokin.utils.Logger;
import dev.sorokin.utils.ThreadSleepUtil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockOfficeDemo {

    static class Office {
        private final Lock lock = new ReentrantLock();
        private final Condition docsReadyCondition = lock.newCondition();
        private final Condition moneyReadyCondition = lock.newCondition();

        private boolean hasDocs = false;
        private boolean hasMoney = false;

        // Приносим что-то в офис
        public void bring(String type) {
            lock.lock();
            try {
                if (type.equals("docs")) {
                    hasDocs = true;
                    Logger.log("принес документы 📄");
                    docsReadyCondition.signalAll();
                } else if (type.equals("money")) {
                    hasMoney = true;
                    Logger.log("принес деньги 💰");
                    moneyReadyCondition.signalAll();
                }
            } finally {
                lock.unlock();
            }
        }

        // Хотим что-то забрать
        public void take(String type){
            lock.lock();
            try {
                if (type.equals("docs")) {
                    while (!hasDocs) {
                        Logger.log(Thread.currentThread().getName() + " ждёт документы 📄 ...");
                        docsReadyCondition.await();
                    }
                    hasDocs = false;
                    Logger.log(Thread.currentThread().getName() + " забрал документы ✅");
                } else if (type.equals("money")) {
                    while (!hasMoney) {
                        Logger.log("ждёт деньги 💰 ...");
                        moneyReadyCondition.await();
                    }
                    hasMoney = false;
                    Logger.log("забрал деньги ✅");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Office office = new Office();

        // Потоки, которые ждут
        new Thread(() -> office.take("docs"),"Employee-Docs")
                .start();
        new Thread(() -> office.take("money"), "Employee-Money")
                .start();

        // Через паузы приносим ресурсы
        ThreadSleepUtil.safeSleepWithoutThrow(1000);
        new Thread(() -> office.bring("docs"), "Courier-1").start();

        ThreadSleepUtil.safeSleepWithoutThrow(1000);
        new Thread(() -> office.bring("money"), "Courier-2").start();
    }
}

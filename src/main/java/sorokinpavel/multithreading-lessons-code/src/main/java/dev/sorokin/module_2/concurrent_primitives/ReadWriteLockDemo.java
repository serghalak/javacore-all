package dev.sorokin.module_2.concurrent_primitives;

import dev.sorokin.utils.BusyCpuUtil;
import dev.sorokin.utils.Logger;
import dev.sorokin.utils.ThreadSleepUtil;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    //public static volatile int counter=0;
    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        var reader = new ReaderWorker(lock);
        var writer = new WriterWorker(lock);

        // 3 читателя + 1 писатель
        var reader1 = new Thread(reader::doWorkInLoop, "reader-1");
        var reader2 = new Thread(reader::doWorkInLoop, "reader-2");
        var reader3 = new Thread(reader::doWorkInLoop, "reader-3");
        reader1.start();
        reader2.start();
        reader3.start();

        var writer1 = new Thread(writer::doWorkInLoop, "writer-3");
        writer1.start();

        ThreadSleepUtil.safeSleepWithoutThrow(30_000);
        writer1.interrupt();
        reader1.interrupt();
        reader2.interrupt();
        reader3.interrupt();
    }

    private static class ReaderWorker {
        private final ReadWriteLock lock;

        public ReaderWorker(
                ReadWriteLock lock
        ) {
            this.lock = lock;
        }

        public void doWorkInLoop() {
            while (!Thread.currentThread().isInterrupted()) {
               //counter++;
                ThreadSleepUtil.safeSleepWithJitter(500);
                try {
                    lock.readLock().lock();
                    Logger.log("Reader working..");
                    BusyCpuUtil.spinOnCpuMillis(500);
                    Logger.log("Reader go rest..");
                } finally {
                    lock.readLock().unlock();
                }
            }
            Logger.log("Reader-loop worker exiting..");
        }
    }

    private static class WriterWorker {
        private final ReadWriteLock lock;

        public WriterWorker(
                ReadWriteLock lock
        ) {
            this.lock = lock;
        }

        public void doWorkInLoop() {
            while (!Thread.currentThread().isInterrupted()) {
                ThreadSleepUtil.safeSleepWithoutThrow(5000);
                try {
                    Logger.log("---BIG BOSS HERE---");
                    lock.writeLock().lock();
                    //
                    Logger.log("Writer (boss) working..");
                    BusyCpuUtil.spinOnCpuMillis(5000);
                    Logger.log("Writer (boss) go rest..");
                    //
                } finally {
                    lock.writeLock().unlock();
                }
            }
            Logger.log("Reader-work worker exiting..");
        }
    }
}

package sorokinpavel.threads.lesson04;

import sorokinpavel.threads.utils.Logger;
import sorokinpavel.threads.utils.ThreadSleepUtil;

/**
 * Недетерминированность планировщика: логи разных потоков перемешиваются по-разному
 */
public class UnpredictableOrderingDemo {

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(
                    UnpredictableOrderingDemo::runSteps,
                    "worker-" + i
            );
            threads[i].start();
        }
        for (Thread thread : threads) {
            join(thread);
        }
        Logger.log("ordering depends on scheduler and is not guaranteed");
    }

    private static void runSteps() {
        Logger.log("started");
        for (int step = 0; step < 3; step++) {
            Logger.log("step %d", step);
            ThreadSleepUtil.safeSleepWithoutThrow(10);
        }
        Logger.log("ended");
    }

    private static void join(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

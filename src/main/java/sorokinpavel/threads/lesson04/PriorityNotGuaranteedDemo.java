package sorokinpavel.threads.lesson04;

import sorokinpavel.threads.utils.Logger;
import sorokinpavel.threads.utils.ThreadSleepUtil;

/**
 * Приоритет потока — лишь подсказка планировщику и не гарантирует честность
 */
public class PriorityNotGuaranteedDemo {

    public static void main(String[] args) {
        Thread low = new Thread(
                () -> busyLoop("LOW"),
                "low-worker"
        );
        low.setPriority(Thread.MIN_PRIORITY);

        Thread high = new Thread(
                () -> busyLoop("HIGH"),
                "high-worker"
        );
        high.setPriority(Thread.MAX_PRIORITY);

        low.start();
        high.start();

        ThreadSleepUtil.safeSleepWithoutThrow(1000);

        low.interrupt();
        high.interrupt();
    }

    private static void busyLoop(String label) {
        long counter = 0;
        while (!Thread.currentThread().isInterrupted()) {
            counter++;
            if (counter % 1_000_000 == 0) {
                Logger.log("%s counter=%d", label, counter);
            }
        }
        Logger.log(label + " ended work");
    }
}

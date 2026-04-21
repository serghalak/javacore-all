package sorokinpavel.threads.lesson04;

import sorokinpavel.threads.utils.Logger;
import sorokinpavel.threads.utils.ThreadSleepUtil;

/**
 * Демонстрирует, что {@link Thread#yield()} — всего лишь подсказка планировщику,
 * порядок не гарантируется. Сравнение с небольшим {@code sleep()}.
 */
public class YieldNoGuaranteesDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> run(true), "yield-A");
        Thread t2 = new Thread(() -> run(false), "not-yield-B");
        t1.start();
        t2.start();
        join(t1);
        join(t2);
        Logger.log("ordering is not guaranteed");
    }

    private static void run(boolean yield) {
        for (int i = 0; i < 50; i++) {
            Logger.log("step %d", i);
            if (yield) {
                Thread.yield();
            }
            ThreadSleepUtil.safeSleepWithoutThrow(50);
        }
    }

    private static void join(Thread t) {
        try {
            t.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


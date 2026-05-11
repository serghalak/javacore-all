package sorokinpavel.threads.lesson05;


import sorokinpavel.threads.utils.Logger;
import sorokinpavel.threads.utils.ThreadSleepUtil;

/**
 * Если поток, выполняющий join(), сам прерывается — выбрасывается InterruptedException.
 * Показываем корректную обработку и восстановление флага.
 */
public class InterruptDuringJoinDemo {

    public static void main(String[] args) {
        Thread worker = new Thread(
                () -> ThreadSleepUtil.safeSleepWithoutThrow(100),
                "sleepy-worker"
        );

        Thread joiner = new Thread(() -> {
            try {
                Logger.log("joiner: joining worker...");
                worker.join(); // блокировка на join
                Logger.log("joiner: joined successfully");
            } catch (InterruptedException e) {
                Logger.log("joiner: Interrupted during join, restore flag and exit");
                Thread.currentThread().interrupt();
            }
        }, "joiner-worker");


        worker.start();
        joiner.start();

        ThreadSleepUtil.safeSleepWithoutThrow(1000);
        Logger.log("main: interrupt joiner");

        joiner.interrupt(); // нет эффекта если joiner terminated

        join(worker);
        join(joiner);
        Logger.log("done");
    }

    private static void join(Thread t) {
        try {
            t.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

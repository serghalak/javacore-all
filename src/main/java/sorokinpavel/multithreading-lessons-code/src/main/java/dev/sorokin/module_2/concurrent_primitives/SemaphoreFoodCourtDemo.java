package dev.sorokin.module_2.concurrent_primitives;

import dev.sorokin.utils.Logger;
import dev.sorokin.utils.ThreadSleepUtil;

import java.util.concurrent.Semaphore;

public class SemaphoreFoodCourtDemo {

    static class Visitor implements Runnable {
        private final int id;
        private final Semaphore seats;

        Visitor(
                int id,
                Semaphore seats
        ) {
            this.id = id;
            this.seats = seats;
        }

        @Override
        public void run() {
            try {
                Logger.log("Пришёл в фудкорт, пытаюсь занять место...", id);
                seats.acquire(); // занять место
                Logger.log("Сел за стол. Свободных мест: %d", seats.availablePermits());
                ThreadSleepUtil.safeSleepRandomMillis(3000, 5000);
                Logger.log("Поел и уходит", id);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                seats.release(); // освободить место
                Logger.log("Освободил место. Свободных мест теперь: %d", seats.availablePermits());
            }
        }
    }

    public static void main(String[] args) {
        int totalVisitors = 15;
        int seatsCount = 5;
        Semaphore seats = new Semaphore(seatsCount);
        Logger.log("%s", seats.availablePermits());

        for (int i = 0; i < totalVisitors; i++) {
            new Thread(new Visitor(i, seats))
                    .start();
        }
    }
}

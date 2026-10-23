package dev.sorokin.module_2.concurrent_primitives;

import dev.sorokin.utils.Logger;
import dev.sorokin.utils.ThreadSleepUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class CountdownLatchRocketDemo {

    static class Engineer implements Runnable {
        private final int id;
        private final CountDownLatch latch;
        private final int[] flightParams;

        Engineer(
                int id,
                CountDownLatch latch,
                int[] flightParams
        ) {
            this.id = id;
            this.latch = latch;
            this.flightParams = flightParams;
        }

        @Override
        public void run() {
            Logger.log("Начал расчёты...");
            ThreadSleepUtil.safeSleepRandomMillis(500, 1500);
            int result = ThreadLocalRandom.current().nextInt(1000);
            flightParams[id] = result;
            Logger.log("Расчёт завершён: %d", result);
            latch.countDown();
            Logger.log("Отчитался, latch=%d", latch.getCount());
        }
    }

    static class Rocket implements Runnable {
        private final CountDownLatch latch;
        private final int[] flightParams;

        Rocket(
                CountDownLatch latch,
                int[] flightParams
        ) {
            this.latch = latch;
            this.flightParams = flightParams;
        }

        @Override
        public void run() {
            try {
                Logger.log("Ожидаю готовности инженеров...");
                latch.await(); // ждём, пока все инженеры закончат
                Logger.log("Все инженеры отчитались, стартуем!");
                int checksum = 0;
                for (int param : flightParams) {
                    checksum += param;
                }
                Logger.log("Полёт запущен. Контрольная сумма параметров: %d%n", checksum);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        int engineers = 10;
        CountDownLatch latch = new CountDownLatch(engineers);
        int[] flightParams = new int[engineers];

        new Thread(new Rocket(latch, flightParams)).start();

        for (int i = 0; i < engineers; i++) {
            new Thread(
                    new Engineer(i, latch, flightParams),
                    "engineer-" + i
            ).start();
        }
    }
}

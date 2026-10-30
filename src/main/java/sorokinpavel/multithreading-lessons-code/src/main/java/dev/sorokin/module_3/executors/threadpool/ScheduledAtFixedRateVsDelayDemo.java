package dev.sorokin.module_3.executors.threadpool;

import dev.sorokin.module_3.executors.threadpool.tasks.ExternalCallTask;
import dev.sorokin.utils.Logger;
import dev.sorokin.utils.ThreadSleepUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Выполняет задачи в будущем по расписанию
 */
public class ScheduledAtFixedRateVsDelayDemo {
    public static void main(String[] args) throws Exception {
        var start = System.currentTimeMillis();
        try (ScheduledExecutorService executorService = Executors.newScheduledThreadPool(8)) {

            var task1 = new ExternalCallTask(1);
            var task2 = new ExternalCallTask(2);
            var task3 = new ExternalCallTask(3);
            var task4 = new ExternalCallTask(4);
            var task5 = new ExternalCallTask(5);

//            executorService.schedule(task1, 10, TimeUnit.SECONDS);
//            executorService.schedule(task2, 30, TimeUnit.SECONDS);
//            executorService.schedule(task3, 20, TimeUnit.SECONDS);
//            executorService.schedule(task4, 1, TimeUnit.SECONDS);
//            executorService.schedule(task5, 0, TimeUnit.SECONDS);

            executorService.scheduleAtFixedRate(task1, 1, 1, TimeUnit.SECONDS);
            executorService.scheduleWithFixedDelay(task2, 1, 1, TimeUnit.SECONDS);
            ThreadSleepUtil.safeSleepWithoutThrow(10_000);
        } finally {
            long end = System.currentTimeMillis();
            long duration = end - start;
            Logger.log("Processed time: %sms",duration);
        }
    }
}

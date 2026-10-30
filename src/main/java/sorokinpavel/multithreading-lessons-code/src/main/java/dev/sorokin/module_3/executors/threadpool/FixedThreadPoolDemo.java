package dev.sorokin.module_3.executors.threadpool;

import dev.sorokin.module_3.executors.threadpool.tasks.ExternalCallTask;
import dev.sorokin.utils.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try (ExecutorService executorService = Executors.newFixedThreadPool(100)) {
            for (int i = 0; i < 1000; i++) {
                executorService.submit(new ExternalCallTask(i));
            }
        } finally {
            long end = System.currentTimeMillis();
            long duration = end - start;
            Logger.log("Processed time: %sms", duration);
        }
    }

}

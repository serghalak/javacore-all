package dev.sorokin.module_3.executors.threadpool;

import dev.sorokin.module_3.executors.threadpool.tasks.ExternalCallIntegerTask;
import dev.sorokin.utils.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FixedThreadPoolCallableDemo {
    public static void main(String[] args) {
        List<Future<Integer>> futures = new ArrayList<>();
        long start = System.currentTimeMillis();

        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            for (int i = 0; i < 10; i++) {
                futures.add(executorService.submit(new ExternalCallIntegerTask(i)));
            }
        }

        futures.forEach(integerFuture -> {
            try {
                Logger.log("Future result = %s", integerFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        long end = System.currentTimeMillis();
        long duration = end - start;
        Logger.log("Processed time: %sms", duration);
    }
}

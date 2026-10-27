package dev.sorokin.module_2.atomic;

import dev.sorokin.utils.Logger;
import dev.sorokin.utils.ThreadJoinUtils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicIntegerDemo {

    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger();
        AtomicLong atomicLong = new AtomicLong();

        int threadsCount = 8;
        int perIterationsThread = 250_000;
        Thread[] threads = new Thread[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < perIterationsThread; k++) {
                    counter.incrementAndGet();
                }
            });
            threads[i].start();
        }
        ThreadJoinUtils.safeJoin(threads);
        Logger.log("counter = " + counter); // ожидаемо: threadsCount * perThread
    }
}

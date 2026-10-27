package dev.sorokin.module_2.atomic;

import dev.sorokin.utils.Logger;
import dev.sorokin.utils.ThreadJoinUtils;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {

    static AtomicReference<String> name = new AtomicReference<>("Alice");

    public static void main(String[] args) {

        Runnable r = () -> name.updateAndGet(
                n -> n.equals("Alice") ? "Bob" : "Alice"
        );

        Thread[] threads = new Thread[101];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(r);
            threads[i].start();
        }

        ThreadJoinUtils.safeJoin(threads);

        Logger.log(name.get()); // Всегда будет Alice т.к. атомарно 2 swap'а
    }
}
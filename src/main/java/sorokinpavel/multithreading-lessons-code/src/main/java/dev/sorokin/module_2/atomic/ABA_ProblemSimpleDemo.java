package dev.sorokin.module_2.atomic;

import dev.sorokin.utils.Logger;
import dev.sorokin.utils.ThreadJoinUtils;
import dev.sorokin.utils.ThreadSleepUtil;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABA_ProblemSimpleDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicStampedReference<String> reference = new AtomicStampedReference<>("A", 0);

        Thread thread1 = new Thread(() -> {
            int[] holder = new int[1];
            String oldValue = reference.get(holder);

            Logger.log("Прочитал значение: " + oldValue);

            ThreadSleepUtil.safeSleepWithoutThrow(300); // даём время другому потоку всё поменять

            boolean ok = reference.compareAndSet(oldValue, "C", holder[0], holder[0] + 1);
            Logger.log("OK: %s", ok);
        }, "writer-1");

        Thread thread2 = new Thread(() -> {
            int[] holder = new int[1];
            String oldValue = reference.get(holder);
            ThreadSleepUtil.safeSleepWithoutThrow(100);
            Logger.log("Меняю A -> B");
            boolean ok = reference.compareAndSet(oldValue, "B", holder[0], holder[0] + 1);
            Logger.log("OK: %s", ok);

            ThreadSleepUtil.safeSleepWithoutThrow(100);
            Logger.log("Возвращаю B -> A (ABA)");
            oldValue = reference.get(holder);
            ok = reference.compareAndSet(oldValue, "A", holder[0], holder[0] + 1);
            Logger.log("OK: %s", ok);
        }, "writer-2");

        thread1.start();
        thread2.start();

        ThreadJoinUtils.safeJoin(thread1, thread2);

        int[] holder = new int[1];
        Logger.log("Финальное значение: " + reference.get(holder));
    }
}

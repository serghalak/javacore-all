package sorokinpavel.threads.lesson05;

import sorokinpavel.threads.utils.BusyCpuUtil;
import sorokinpavel.threads.utils.Logger;
import sorokinpavel.threads.utils.ThreadSleepUtil;

public class ThreadLifecycleCheckDemo {

    public static void main(String[] args) {
        Thread t = new Thread(
                ThreadLifecycleCheckDemo::doSomeWork,
                "worker-1"
        );

        Logger.log("worker state: %s", t.getState()); // NEW

        t.start();

        for (int i = 0; i < 100; i++) {
            Logger.log("worker state: %s", t.getState()); // RUNNABLE или TIMED_WAITING
            ThreadSleepUtil.safeSleepWithoutThrow(100);
        }

        try {
            t.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Logger.log("worker state: %s", t.getState()); // TERMINATED‘
    }

    private static void doSomeWork() {
        for (int i = 0; i < 5; i++) {
            Logger.log("working");
            BusyCpuUtil.spinOnCpuMillis(1000);
            Logger.log("goes sleep");
            ThreadSleepUtil.safeSleepWithoutThrow(1000);
        }
        Logger.log("work ended");
    }
}


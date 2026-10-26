package golovach.multythread.execservice;

import java.util.concurrent.*;

public class DemoSubmit {
    static void main() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future0 = executorService.submit(() -> 42);

        Future<Integer> future1 = executorService.submit(() -> {
            while(true) {
                // Do something
            }
        });

        Thread.sleep(1000);
        System.out.println("future0.isDone(): " + future0.isDone());
        System.out.println("future1.isDone(): " + future1.isDone());
    }
}

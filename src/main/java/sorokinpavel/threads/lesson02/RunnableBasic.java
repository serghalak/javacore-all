package sorokinpavel.threads.lesson02;

public class RunnableBasic {

    static class HelloTask implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello World! I'm runnable: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new HelloTask(), "worker-1");
        Thread thread2 = new Thread(new HelloTask(), "worker-2");
        Thread thread3 = new Thread(new HelloTask(), "worker-3");

        Thread[]  threads = new Thread[10];
        for (int i=0; i<10; i++) {
            Thread thread = new Thread(RunnableBasic::doSomething, "worker-" + (i+5));
            thread.start();
            threads[i] = thread;
        }
        Thread thread4 = new Thread(() -> {
            System.out.println("Hello World! I'm runnable: " + Thread.currentThread().getName());
            doSomething();
        }, "worker-4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("main thread end");
    }

    public static void doSomething() {

        for (int i = 0; i < 10; i++) {
            System.out.println("working " + i + " - " + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

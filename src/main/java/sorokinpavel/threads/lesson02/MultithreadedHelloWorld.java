package sorokinpavel.threads.lesson02;

public class MultithreadedHelloWorld {

    private static class HelloThread extends Thread {

        public HelloThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println("Hello World! I'm " +   Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        new HelloThread("Worker-1").start();
        new HelloThread("Worker-2").start();
    }
}

package golovach.multythread;

public class App06 {
    static void main() throws InterruptedException {
        final Object monitor = new Object();
        new Thread(() -> {
            synchronized (monitor) {
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000);
        System.out.println("A");
        System.out.println("B");
        synchronized (monitor) {
            System.out.println("C");
        }

    }
}

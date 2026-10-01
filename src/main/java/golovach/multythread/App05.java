package golovach.multythread;

public class App05 {
    static volatile boolean in = false;
    static void main() {
        final Object monitor = new Object();
        new Thread(() -> {
            synchronized (monitor) {
                in = true;
                try {
                    //Thread.sleep(Long.MAX_VALUE);
                    //Thread.yield();//interrupted thread but not long
                    monitor.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println("A");
        while (!in) {
            System.out.println("B");
        }
        synchronized (monitor) {
            System.out.println("C");
            monitor.notify();
        }

    }
}

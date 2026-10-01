package golovach.multythread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BlockingQueueExample {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {

        new Thread(() -> {
            int counter = 0;
            while (true) {
                try {
                    Thread.sleep(300L);
                    queue.put(++counter);
                    System.out.println("Put " + counter);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("...wait for taking");
                    //int data = queue.take();
                    Integer data = queue.poll(1, SECONDS);
                    System.out.println("Take " + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

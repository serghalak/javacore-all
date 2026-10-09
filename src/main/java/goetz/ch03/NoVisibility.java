package goetz.ch03;

/**
 * NoVisibility
 * <p/>
 * Sharing variables without synchronization
 *
 * @author Brian Goetz and Tim Peierls
 */

public class NoVisibility {
    private static boolean ready;
    private static int number;
    private static long work;

    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready) {
                work++;
            }
            System.out.println("work: " + work);
                //Thread.yield();
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
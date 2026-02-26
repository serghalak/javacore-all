package sorokinpavel.threads.lesson03;


import java.util.ArrayList;
import java.util.List;

public class SimpleRaceConditionDemo {

    private static int counter = 0;

    public static void main(String[] args) {
        int threads = 8;
        int incrementsPerThread = 100_000;

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    counter++;
                }
            });
            list.add(t);
        }
        list.forEach(Thread::start);
        list.forEach(SimpleRaceConditionDemo::joinQuietly);

        int expected = threads * incrementsPerThread;
        System.out.println("Expected: " + expected + ", actual: " + counter);
    }

    static void joinQuietly(Thread t) {
        try {
            t.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

package golovach.multythread;

public class App00_norm {
    static volatile boolean run = true;

    public static void main(String[] args) {
        Thread newThread = new Thread(
                () -> {
                    while (run) {
                    }
                });
        newThread.start();
        run = false;
    }
}

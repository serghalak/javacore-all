package golovach.multythread;

public class AppSynch {

    private static boolean run = true;

    public static synchronized boolean isRun() {
        return run;
    }

    public static synchronized void setRun(boolean run) {
        AppSynch.run = run;
    }

    static void main() {
        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Megthod run isRun = " + isRun() + "");
                setRun(false);
            }
        });

        newThread.start();

        //System.out.println("isRun = " + isRun() + "");
        while (isRun()) {
            System.out.println("isRun = " + isRun() + "");
        }
    }
}

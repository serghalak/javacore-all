package horsman14.v1ch08.limitations;

class Sneaky {
    @SuppressWarnings("unchecked")
    static <T extends Throwable> void throwAs(Throwable t) throws T {
        throw (T) t;
    }
    
    static void badThrowAsRuntimeException(Throwable t) throws RuntimeException {
        throw (RuntimeException) t;
    }
    
    interface Runnable { void run() throws Exception; }

    static java.lang.Runnable asRunnable(Sneaky.Runnable task) {
        return () -> {
            try {
                task.run();
            }
            catch (Exception e) {
                // The following does NOT work--it causes a ClassCastException 
                // Sneaky.badThrowAsRuntimeException(e);
                Sneaky.<RuntimeException>throwAs(e);
            }
        };
    }
}

public class DefeatCheckedExceptionChecking {
    void main() {
        var thread = new Thread(Sneaky.asRunnable(() -> {
            Thread.sleep(1000);
            IO.println("Hello, World!");
            throw new Exception("Check this out!");
        }));
        thread.start();
    }
}

package bloch.chapter4.inherence;

import java.time.Instant;

public class Sub extends Super {

    private final Instant instant;

    public Sub() {
        System.out.println("Sub constructor called");
        instant = Instant.now();

    }

    @Override
    public void overrideMe() {
        System.out.println("Sub overrideMe Instant: " + instant);
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe();
    }
}

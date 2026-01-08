package bloch.chapter4.inherence;

public class Super {
    public Super() {
        System.out.println("Super constructor");
        overrideMe();
    }

    public void overrideMe() {
        System.out.println("Override me super");
    }
}

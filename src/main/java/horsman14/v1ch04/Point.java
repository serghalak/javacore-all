package horsman14.v1ch04;

public record Point(double x, double y) {
    private static double z;
    //private double w;//not allowed

    double distanceFromOrigin() {
        return Math.hypot(x, y);
    }
}

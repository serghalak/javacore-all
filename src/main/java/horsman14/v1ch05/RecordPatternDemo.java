package horsman14.v1ch05;

record Point(double x, double y) {
}

class RecordPatternDemo {
    void main() {
        int r = (int) (4 * Math.random());
        Point p = switch (r) {
            case 0 -> new Point(0, 0);
            case 1 -> new Point(1, 0);
            case 2 -> new Point(0, 1);
            default -> new Point(1, 1);
        };
        String description = switch (p) {
            case Point(var x, var y) when x == 0 && y == 0 -> "origin";
            case Point(var x, var _) when x == 0 -> "on x-axis";
            case Point(var _, var y) when y == 0 -> "on y-axis";
            default -> "not on either axis";
        };
        IO.println("%s %s%n".formatted(p, description));
    }
}

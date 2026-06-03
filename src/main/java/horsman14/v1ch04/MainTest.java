package horsman14.v1ch04;

import java.util.Date;

public class MainTest {
    void main() {
        var point = new Point(1, 2);
        IO.println(point.distanceFromOrigin());
        testRecord();
    }

    static void testRecord() {
        var p = new Point(3, 4);
//        IO.println("Coordinates of p: " + p.x() + " " + p.y());
//        IO.println("Distance from origin: " + p.distanceFromOrigin());
        // Same computation with static field and method
        //IO.println("Distance from origin: " + Point.distance(Point.ORIGIN, p));
        // Invoking a compact constructor
//        var r = new Range(4, 3);
//        IO.println("r: " + r);

        // A mutable record
//        var pt = new PointInTime(3, 4, new Date());
//        IO.println("Before: " + pt);
//        pt.when().setTime(0);
//        IO.println("After: " + pt);
        testHashCode();
    }

    static void testHashCode() {
        String s = "abc";
        String  t = new String(s);
        IO.println(s.hashCode() + " " + t.hashCode());
        IO.println(s == t);

        Point p = new Point(1, 2);
        Object o = new Point(1, 2);
        IO.println(p.getClass().getName() + " " + o.getClass().getName());
    }


    record Point(double x, double y) {
        // A custom constructor
        Point() {
            this(0, 0);
        }

        // A method
        double distanceFromOrigin() {
            return Math.hypot(x, y);
        }

        // A static field and method
        static Point ORIGIN = new Point();

        static double distance(Point p, Point q) {
            return Math.hypot(p.x - q.x, p.y - q.y);
        }
    }

    record Range(int from, int to) {
        // A compact constructor
        Range {
            if (from > to) { // Swap the bounds
                int temp = from;
                from = to;
                to = temp;
            }
        }
    }

    record PointInTime(double x, double y, Date when) { }
}

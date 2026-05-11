package horsman14.v1ch05.objectAnalyzer;

import module java.base;

/**
 * This program uses reflection to spy on objects.
 */
class ObjectAnalyzerDemo {
    void main() throws Exception {
        var squares = new ArrayList<Integer>();
        for (int i = 1; i <= 5; i++)
            squares.add(i * i);
        IO.println(new ObjectAnalyzer().toString(squares));
    }
}

package horsman14.v1ch11;

import horsman14.v1ch11.runtimeAnnotations.*;

class RuntimeAnnotationDemo {
    void main() {
        var rect = new Rectangle(new Point(5, 10), 20, 30);
        IO.println(ToStrings.toString(rect));
    }
}

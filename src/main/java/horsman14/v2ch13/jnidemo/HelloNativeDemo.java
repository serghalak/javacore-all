package horsman14.v2ch13.jnidemo;

class HelloNativeDemo {  
    void main() {
        int result = HelloNative.printf("Hello, Native World!\n");
        IO.println("Printed %d characters.".formatted(result));
    }

    static {  
        System.loadLibrary("HelloNative");
    }
}

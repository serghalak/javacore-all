package horsman14.v2ch13.ffmdemo;

import java.lang.invoke.*;
import java.lang.foreign.*;

public class HelloForeign {
    void main() throws Throwable {
        Linker linker = Linker.nativeLinker();
        MethodHandle printf = linker.downcallHandle(
            linker.defaultLookup().findOrThrow("printf"),
            FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));

        try (Arena arena = Arena.ofConfined()) {
            MemorySegment str = arena.allocateFrom("Hello, Foreign World!\n");
            int result = (int) printf.invoke(str);
            System.out.printf("Printed %d characters.%n", result);
        }
    }
}

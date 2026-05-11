package horsman14.v2ch13.upcall;

import module java.base;

import static java.lang.foreign.ValueLayout.*;

class UpcallDemo {
    static int comp(MemorySegment a, MemorySegment b) {
        return Float.compare(a.get(JAVA_FLOAT, 0), b.get(JAVA_FLOAT, 0));
    }
    
    void main() throws Throwable {
        Linker linker = Linker.nativeLinker();
        MethodHandle qsort = linker.downcallHandle(
            linker.defaultLookup().findOrThrow("qsort"),
            FunctionDescriptor.ofVoid(ADDRESS, JAVA_LONG, JAVA_LONG, ADDRESS));

        MethodHandle compHandle = MethodHandles.lookup()
            .findStatic(UpcallDemo.class, "comp", 
                MethodType.methodType(
                    int.class, MemorySegment.class, MemorySegment.class));

        MemorySegment compStub = linker.upcallStub(
            compHandle,
            FunctionDescriptor.of(JAVA_INT,
                ADDRESS.withTargetLayout(ValueLayout.JAVA_FLOAT),
                ADDRESS.withTargetLayout(ValueLayout.JAVA_FLOAT)),
            Arena.ofAuto());

        float[] samples = new float[100];
        RandomGenerator generator = RandomGenerator.getDefault();
        for (int i = 0; i < samples.length; i++)
            samples[i] = generator.nextFloat();
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment csamples = arena.allocateFrom(JAVA_FLOAT, samples);
            qsort.invoke(csamples,
                samples.length, JAVA_FLOAT.byteSize(), compStub);
            samples = csamples.toArray(JAVA_FLOAT);
            IO.println(Arrays.toString(samples));
        }
    }    
}

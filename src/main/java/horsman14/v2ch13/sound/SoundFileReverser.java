package horsman14.v2ch13.sound;

import module java.base;

import static java.lang.foreign.ValueLayout.*;

class SoundFileReverser {
    static final int SFM_READ = 0x10;
    static final int SFM_WRITE = 0x20;

    void main(String[] args) throws Throwable {
        String inputFile = args.length > 0 ? args[0] : "sound/input.mp3";
        String outputFile = args.length > 1 ? args[1] : "sound/output.mp3";

        Linker linker = Linker.nativeLinker();
        SymbolLookup sndfile = SymbolLookup.libraryLookup(
            "/lib/x86_64-linux-gnu/libsndfile.so", Arena.ofAuto());

        StructLayout SF_INFO = MemoryLayout.structLayout(
            JAVA_LONG.withName("frames"),
            JAVA_INT.withName("samplerate"),
            JAVA_INT.withName("channels"),
            JAVA_INT.withName("format"),
            JAVA_INT.withName("sections"),
            JAVA_INT.withName("seekable")
        );

        MethodHandle sf_open = linker.downcallHandle(
            sndfile.findOrThrow("sf_open"),
            FunctionDescriptor.of(ADDRESS, ADDRESS, JAVA_INT, ADDRESS)
        );

        MethodHandle sf_close = linker.downcallHandle(
            sndfile.findOrThrow("sf_close"),
            FunctionDescriptor.of(JAVA_INT, ADDRESS)
        );

        MethodHandle sf_readf_float = linker.downcallHandle(
            sndfile.findOrThrow("sf_readf_float"),
            FunctionDescriptor.of(JAVA_LONG, ADDRESS, ADDRESS, JAVA_LONG)
        );

        MethodHandle sf_writef_float = linker.downcallHandle(
            sndfile.findOrThrow("sf_writef_float"),
            FunctionDescriptor.of(JAVA_LONG, ADDRESS, ADDRESS, JAVA_LONG)
        );

        try (Arena arena = Arena.ofConfined()) {
            MemorySegment info = arena.allocate(SF_INFO);
            MemorySegment cInputPath = arena.allocateFrom(inputFile);

            // Open sound file
            MemorySegment inFile = (MemorySegment) sf_open.invoke(cInputPath, SFM_READ, info);
            if (inFile == MemorySegment.NULL)
                throw new RuntimeException("sf_open: Failed for " + inputFile);
            long frames = info.get(JAVA_LONG,
                SF_INFO.byteOffset(PathElement.groupElement("frames")));
            int channels = info.get(JAVA_INT,
                SF_INFO.byteOffset(PathElement.groupElement("channels")));
            long totalSamples = frames * channels;

            // Allocate native memory for audio samples
            MemorySegment audioIn = arena.allocate(JAVA_FLOAT, totalSamples);

            // Read samples
            long actualFrames = (long) sf_readf_float.invoke(
                inFile, audioIn, frames);
            if (frames != actualFrames)
                throw new RuntimeException(
                    "sf_readf_float: Expected %d frames but read %d frames."
                    .formatted(frames, actualFrames));

            // Copy native array to Java and process
            float[] audioSamples = audioIn.toArray(JAVA_FLOAT);
            
            // Reverse the frames
            for (int f = 0; f < frames / 2; f++) {
                for (int c = 0; c < channels; c++) {
                    int i = f * channels + c;
                    int j = (int)((frames - 1 - f) * channels + c);
                    float temp = audioSamples[i];
                    audioSamples[i] = audioSamples[j];
                    audioSamples[j] = temp;
                }
            }

            // Write to sound file
            MemorySegment cOutputPath = arena.allocateFrom(outputFile);
            MemorySegment outFile = (MemorySegment) sf_open.invoke(
                cOutputPath, SFM_WRITE, info);
            if (outFile == MemorySegment.NULL)
                throw new RuntimeException("sf_open: Failed for " + outputFile);

            // Copy Java array to native and write
            MemorySegment audioOut = arena.allocateFrom(JAVA_FLOAT, audioSamples);

            actualFrames = (long) sf_writef_float.invoke(outFile, audioOut, frames);
            if (frames != actualFrames)
                throw new RuntimeException(
                    "sf_writef_float: Expected to write %d frames but wrote %d frames."
                    .formatted(frames, actualFrames));

            sf_close.invoke(inFile);
            int error = (int) sf_close.invoke(outFile);
            if (error != 0)
                throw new RuntimeException("sf_close: Failed with error code " + error);
            IO.println("Reversed " + inputFile + " to  " + outputFile);
        }
    }
}

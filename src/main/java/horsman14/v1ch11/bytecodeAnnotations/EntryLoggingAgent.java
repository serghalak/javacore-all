package horsman14.v1ch11.bytecodeAnnotations;

import module java.base;
import module java.instrument;

/**
 */
public class EntryLoggingAgent {
    public static void premain(final String arg, Instrumentation instr) {
        instr.addTransformer(new ClassFileTransformer() {
            public byte[] transform(ClassLoader loader, String className, Class<?> cl,
                    ProtectionDomain pd, byte[] data) throws IllegalClassFormatException {
                if (!className.replace("/", ".").equals(arg)) return null;
                return EntryLogger.addLogging(data);
            }
        });
    }
}

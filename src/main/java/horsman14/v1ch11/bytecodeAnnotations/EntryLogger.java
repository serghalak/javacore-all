package horsman14.v1ch11.bytecodeAnnotations;

import module java.base;
import java.lang.classfile.Annotation;
import java.lang.classfile.Attributes;
import static java.lang.constant.ConstantDescs.*;

/**
 * Adds "entering" logs to all methods of a class that have the LogEntry annotation.
 */
public class EntryLogger {
    static byte[] addLogging(byte[] classFileBytes) {
        ClassFile cf = ClassFile.of();
        ClassModel classModel = cf.parse(classFileBytes);
        String className = classModel.thisClass().asInternalName().replace('/', '.');
        MethodTransform methodTransform = (methodBuilder, me) -> {
            switch (me) {
                case CodeModel cm -> methodBuilder.withCode(codeBuilder -> {
                    MethodModel mm = cm.parent().get();
                    String methodName = mm.methodName().stringValue();
                    String loggerName = getLoggerName(mm);
                    addLoggingCode(codeBuilder, className, methodName, loggerName);
                    for (var e : cm.elementList())
                        codeBuilder.with(e);
                });
                default -> methodBuilder.accept(me);
            }
        };
        ClassTransform classTransform = ClassTransform
                .transformingMethods(EntryLogger::hasLoggingAnnotation, methodTransform);

        return cf.transformClass(classModel, classTransform);
    }

    private static final String LOGENTRY_DESC = LogEntry.class.descriptorString();

    private static boolean hasLoggingAnnotation(MethodModel mm) {
        var annotations = mm.findAttribute(Attributes.runtimeVisibleAnnotations())
                .map(RuntimeVisibleAnnotationsAttribute::annotations).orElse(List.of());
        for (Annotation a : annotations) {
            if (a.className().equalsString(LOGENTRY_DESC)) { return true; }
        }
        return false;
    }

    private static String getLoggerName(MethodModel mm) {
        var annotations = mm.findAttribute(Attributes.runtimeVisibleAnnotations())
                .map(RuntimeVisibleAnnotationsAttribute::annotations).orElse(List.of());
        for (Annotation a : annotations) {
            if (a.className().equalsString(LOGENTRY_DESC)) {
                for (AnnotationElement e : a.elements()) {
                    if (e.name().equalsString("logger"))
                        return ((AnnotationValue.OfString) e.value()).stringValue();
                }
            }
        }
        return null;
    }

    private static void addLoggingCode(CodeBuilder cb, String className, String methodName,
            String loggerName) {
        ClassDesc cdSystem = ClassDesc.of("java.lang.System");
        ClassDesc cdLogger = ClassDesc.of("java.lang.System$Logger");
        ClassDesc cdLevel = ClassDesc.of("java.lang.System$Logger$Level");
        cb.ldc(loggerName)
                .invokestatic(cdSystem, "getLogger", MethodTypeDesc.of(cdLogger, CD_String))
                .getstatic(cdLevel, "INFO", cdLevel)
                .ldc("Entering {0}.{1}")
                .iconst_2()
                .anewarray(CD_Object).dup().iconst_0().ldc(className)
                .aastore()
                .dup()
                .iconst_1()
                .ldc(methodName)
                .aastore()
                .invokeinterface(cdLogger, "log",
                        MethodTypeDesc.of(CD_void, cdLevel, CD_String, CD_Object.arrayType()));
    }

    /**
     * Adds entry logging code to the given class.
     * @param args the name of the class file to patch
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            IO.println("USAGE: java v1ch11.bytecodeAnnotations.EntryLogger classfile");
            System.exit(1);
        }
        Path path = Path.of(args[0]);
        byte[] bytes = Files.readAllBytes(path);
        bytes = addLogging(bytes);
        Files.write(path, bytes);
    }
}

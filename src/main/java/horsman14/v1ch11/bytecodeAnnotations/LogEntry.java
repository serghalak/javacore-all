package horsman14.v1ch11.bytecodeAnnotations;

import module java.base;

/**
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogEntry
{
   String logger();
}

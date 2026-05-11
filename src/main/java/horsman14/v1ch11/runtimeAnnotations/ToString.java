package horsman14.v1ch11.runtimeAnnotations;

import module java.base;

@Target({ ElementType.FIELD, ElementType.RECORD_COMPONENT, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ToString {
    boolean includeName() default true;
}

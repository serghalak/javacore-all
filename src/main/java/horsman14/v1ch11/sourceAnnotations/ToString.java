package horsman14.v1ch11.sourceAnnotations;

import module java.base;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.SOURCE)
public @interface ToString {
    boolean includeName() default true;
}

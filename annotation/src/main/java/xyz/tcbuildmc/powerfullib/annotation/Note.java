package xyz.tcbuildmc.powerfullib.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Note something...
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Note {
    String value() default "";
}

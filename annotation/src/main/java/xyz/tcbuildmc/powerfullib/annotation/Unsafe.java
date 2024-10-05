package xyz.tcbuildmc.powerfullib.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indicates that an element is unsafe and should not be used.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Unsafe {
}

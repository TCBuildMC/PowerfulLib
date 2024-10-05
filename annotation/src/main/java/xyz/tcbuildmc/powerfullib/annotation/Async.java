package xyz.tcbuildmc.powerfullib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * When it marks on methods, it indicates that the method should be executed asynchronously.
 * <p>
 * On classes, it indicates all methods in the class will be executed asynchronously.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Async {
}

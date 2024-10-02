package xyz.tcbuildmc.common.powerfullib.util.impl;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public final class Utils {
    public static <T> T make(T value, Consumer<? super T> initializer) {
        initializer.accept(value);
        return value;
    }

    // Assertions

    @NotNull
    public static <T> T assertNotNull(T object) {
        if (object == null) {
            throw new NullPointerException("Null object!");
        }

         return object;
    }
}

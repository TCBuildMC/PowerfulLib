package xyz.tcbuildmc.common.powerfullib.util.impl;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

    public static void write(InputStream is, OutputStream os) throws IOException {
        write(1024, is, os);
    }

    public static void write(int buf, InputStream is, OutputStream os) throws IOException {
        byte[] buffer = new byte[buf];
        int read;
        while ((read = is.read(buffer)) > 0) {
            os.write(buffer, 0, read);
        }
    }
}

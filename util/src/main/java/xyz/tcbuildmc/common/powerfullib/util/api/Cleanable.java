package xyz.tcbuildmc.common.powerfullib.util.api;

import java.io.Closeable;
import java.io.IOException;

@FunctionalInterface
public interface Cleanable extends Closeable {
    void clear();

    @Override
    default void close() throws IOException {
        clear();
    }
}

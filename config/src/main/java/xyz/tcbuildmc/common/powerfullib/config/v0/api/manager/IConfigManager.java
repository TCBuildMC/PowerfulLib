package xyz.tcbuildmc.common.powerfullib.config.v0.api.manager;

import org.jetbrains.annotations.Nullable;
import xyz.tcbuildmc.common.powerfullib.util.api.Cleanable;
import xyz.tcbuildmc.common.powerfullib.util.api.Reloadable;

import java.io.IOException;

public interface IConfigManager<T> extends Reloadable, Cleanable {
    @Nullable
    T getInstance();

    void load() throws IOException;

    void save() throws IOException;

    @Override
    default void reload() throws IOException {
        save();
        load();
    }
}

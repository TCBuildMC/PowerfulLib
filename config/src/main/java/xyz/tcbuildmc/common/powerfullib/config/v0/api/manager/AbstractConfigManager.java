package xyz.tcbuildmc.common.powerfullib.config.v0.api.manager;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.ConfigApi;

@RequiredArgsConstructor
public abstract class AbstractConfigManager<T> implements ConfigManager<T> {
    protected final Class<T> clazz;
    protected final ConfigApi parent;

    @Nullable
    protected T instance;

    @Nullable
    @Override
    public T getInstance() {
        return this.instance;
    }

    @Override
    public final void clear() {
        this.instance = null;
    }
}

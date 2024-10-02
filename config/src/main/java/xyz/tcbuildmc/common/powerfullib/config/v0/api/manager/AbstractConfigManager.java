package xyz.tcbuildmc.common.powerfullib.config.v0.api.manager;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.IConfigApi;

@RequiredArgsConstructor
public abstract class AbstractConfigManager<T> implements IConfigManager<T> {
    protected final Class<T> clazz;
    protected final IConfigApi parent;

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

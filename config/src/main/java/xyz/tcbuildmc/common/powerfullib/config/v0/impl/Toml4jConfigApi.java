package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import org.jetbrains.annotations.ApiStatus;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.IConfigApi;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@ApiStatus.Internal
public final class Toml4jConfigApi implements IConfigApi {
    private final ThreadLocal<Toml> toml;
    private final ThreadLocal<TomlWriter> tomlWriter;

    private Toml4jConfigApi(Toml toml, TomlWriter tomlWriter) {
        this.toml = ThreadLocal.withInitial(() -> toml);
        this.tomlWriter = ThreadLocal.withInitial(() -> tomlWriter);
    }

    private Toml4jConfigApi() {
        this(new Toml(), new TomlWriter.Builder().build());
    }

    public static IConfigApi create(Toml toml, TomlWriter tomlWriter) {
        return new Toml4jConfigApi(toml, tomlWriter);
    }

    public static IConfigApi create() {
        return new Toml4jConfigApi();
    }

    @Override
    public <T> T read(Class<T> clazz, String config) {
        try {
            T object = this.toml.get().read(config).to(clazz);

            if (object == null) {
                return clazz.getDeclaredConstructor().newInstance();
            }

            return object;
        } catch (IllegalStateException |
                 InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException("Failed to deserialize Toml.", e);
        }
    }

    @Override
    public <T> String write(T data) {
        return this.tomlWriter.get().write(data);
    }
}

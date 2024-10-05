package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.ConfigApi;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.annotation.Comment;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.annotation.Ignore;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public final class Toml4jConfigApi implements ConfigApi {
    private final ThreadLocal<Toml> toml;
    private final ThreadLocal<TomlWriter> tomlWriter;

    public Toml4jConfigApi(Toml toml, TomlWriter tomlWriter) {
        this.toml = ThreadLocal.withInitial(() -> toml);
        this.tomlWriter = ThreadLocal.withInitial(() -> tomlWriter);
    }

    public Toml4jConfigApi() {
        this(new Toml(), new TomlWriter.Builder().build());
    }

    @Override
    public <T> T read(String config, Class<T> clazz) {
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
            throw new RuntimeException("Failed to deserialize.", e);
        }
    }

    @Override
    public <T> String write(T data) {
        return this.tomlWriter.get().write(data);
    }
}

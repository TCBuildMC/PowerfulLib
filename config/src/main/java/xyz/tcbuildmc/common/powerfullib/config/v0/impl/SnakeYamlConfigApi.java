package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import org.yaml.snakeyaml.Yaml;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.ConfigApi;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.annotation.Comment;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.annotation.Ignore;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public final class SnakeYamlConfigApi implements ConfigApi {
    private final ThreadLocal<Yaml> yaml;

    public SnakeYamlConfigApi(Yaml yaml) {
        this.yaml = ThreadLocal.withInitial(() -> yaml);
    }

    public SnakeYamlConfigApi() {
        this(new Yaml());
    }

    @Override
    public <T> T read(String config, Class<T> clazz) {
        try {
            T object = this.yaml.get().load(config);

            if (object == null) {
                return clazz.getDeclaredConstructor().newInstance();
            }

            return object;
        } catch (InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException("Failed to deserialize.", e);
        }
    }

    @Override
    public <T> String write(T data) {
        return this.yaml.get().dumpAsMap(data);
    }
}

package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import org.jetbrains.annotations.ApiStatus;
import org.yaml.snakeyaml.Yaml;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.IConfigApi;

import java.lang.reflect.InvocationTargetException;

@ApiStatus.Internal
public final class SnakeYamlConfigApi implements IConfigApi {
    private final ThreadLocal<Yaml> yaml;

    private SnakeYamlConfigApi(Yaml yaml) {
        this.yaml = ThreadLocal.withInitial(() -> yaml);
    }

    private SnakeYamlConfigApi() {
        this(new Yaml());
    }

    public static IConfigApi create(Yaml yaml) {
        return new SnakeYamlConfigApi(yaml);
    }

    public static IConfigApi create() {
        return new SnakeYamlConfigApi();
    }

    @Override
    public <T> T read(Class<T> clazz, String config) {
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
            throw new RuntimeException("Failed to deserialize Yaml.", e);
        }
    }

    @Override
    public <T> String write(T data) {
        return this.yaml.get().dumpAsMap(data);
    }
}

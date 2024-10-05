package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.IConfigApi;

import java.lang.reflect.InvocationTargetException;

public class JacksonYmlConfigApi implements IConfigApi {
    private final ThreadLocal<YAMLMapper> mapper;

    private JacksonYmlConfigApi(YAMLMapper mapper) {
        this.mapper = ThreadLocal.withInitial(() -> mapper);
    }

    private JacksonYmlConfigApi() {
        this(new YAMLMapper());
    }

    public static IConfigApi create(YAMLMapper mapper) {
        return new JacksonYmlConfigApi(mapper);
    }

    public static IConfigApi create() {
        return new JacksonYmlConfigApi();
    }

    @Override
    public <T> T read(Class<T> clazz, String config) {
        try {
            T object = this.mapper.get().readValue(config, new TypeReference<T>() {});

            if (object == null) {
                return clazz.getDeclaredConstructor().newInstance();
            }

            return object;
        } catch (JsonProcessingException |
                 InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException("Failed to deserialize Yml.", e);
        }
    }

    @Override
    public <T> String write(T data) {
        try {
            return this.mapper.get().writer().withDefaultPrettyPrinter().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize Yml.", e);
        }
    }
}

package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.IConfigApi;

import java.lang.reflect.InvocationTargetException;

public final class JacksonConfigApi implements IConfigApi {
    private final ThreadLocal<JsonMapper> mapper;

    private JacksonConfigApi(JsonMapper mapper) {
        this.mapper = ThreadLocal.withInitial(() -> mapper);
    }

    private JacksonConfigApi() {
        this(new JsonMapper());
    }

    public static IConfigApi create(JsonMapper mapper) {
        return new JacksonConfigApi(mapper);
    }

    public static IConfigApi create() {
        return new JacksonConfigApi();
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
            throw new RuntimeException("Failed to deserialize Json.", e);
        }
    }

    @Override
    public <T> String write(T data) {
        try {
            return this.mapper.get().writer().withDefaultPrettyPrinter().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize Json.", e);
        }
    }
}

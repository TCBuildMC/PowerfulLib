package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.IConfigApi;

import java.lang.reflect.InvocationTargetException;

public class JacksonJavaPropertiesConfigApi implements IConfigApi {
    private final ThreadLocal<JavaPropsMapper> mapper;

    private JacksonJavaPropertiesConfigApi(JavaPropsMapper mapper) {
        this.mapper = ThreadLocal.withInitial(() -> mapper);
    }

    private JacksonJavaPropertiesConfigApi() {
        this(new JavaPropsMapper());
    }

    public static IConfigApi create(JavaPropsMapper mapper) {
        return new JacksonJavaPropertiesConfigApi(mapper);
    }

    public static IConfigApi create() {
        return new JacksonJavaPropertiesConfigApi();
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

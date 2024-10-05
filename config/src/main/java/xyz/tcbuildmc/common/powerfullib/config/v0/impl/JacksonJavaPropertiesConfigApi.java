package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.ConfigApi;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.reflect.TypeRef;

import java.lang.reflect.InvocationTargetException;

public class JacksonJavaPropertiesConfigApi implements ConfigApi {
    private final ThreadLocal<JavaPropsMapper> mapper;

    public JacksonJavaPropertiesConfigApi(JavaPropsMapper mapper) {
        this.mapper = ThreadLocal.withInitial(() -> mapper);
    }

    public JacksonJavaPropertiesConfigApi() {
        this(new JavaPropsMapper());
    }

    @Override
    public <T> T read(String config, Class<T> clazz) {
        try {
            T object = this.mapper.get().readValue(config, clazz);

            if (object == null) {
                return clazz.getDeclaredConstructor().newInstance();
            }

            return object;
        } catch (JsonProcessingException |
                 InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException("Failed to deserialize.", e);
        }
    }

    public <T> T read(String config, TypeReference<T> typeReference) {
        try {
            T object = this.mapper.get().readValue(config, typeReference);

            if (object == null) {
                return ((Class<T>) typeReference.getType()).getDeclaredConstructor().newInstance();
            }

            return object;
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize.", e);
        }
    }

    @Override
    public <T> String write(T data) {
        try {
            return this.mapper.get().writer().withDefaultPrettyPrinter().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize.", e);
        }
    }
}

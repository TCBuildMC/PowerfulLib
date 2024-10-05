package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.ConfigApi;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.reflect.TypeRef;

import java.lang.reflect.InvocationTargetException;

public class JacksonYmlConfigApi implements ConfigApi {
    private final ThreadLocal<YAMLMapper> mapper;

    public JacksonYmlConfigApi(YAMLMapper mapper) {
        this.mapper = ThreadLocal.withInitial(() -> mapper);
    }

    public JacksonYmlConfigApi() {
        this(new YAMLMapper());
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

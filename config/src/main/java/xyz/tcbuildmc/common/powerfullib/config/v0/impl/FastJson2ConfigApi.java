package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.TypeReference;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.ConfigApi;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.reflect.TypeRef;

public final class FastJson2ConfigApi implements ConfigApi {
    private FastJson2ConfigApi() {}

    public static ConfigApi create() {
        return new FastJson2ConfigApi();
    }

    @Override
    public <T> T read(String config, Class<T> clazz) {
        try {
            T object = JSON.parseObject(config, clazz);

            if (object == null) {
                return clazz.getDeclaredConstructor().newInstance();
            }

            return object;
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize.", e);
        }
    }

    @Override
    public <T> T read(String config, TypeRef<T> typeRef) {
        try {
            T object = JSON.parseObject(config, typeRef.getType());

            if (object == null) {
                return ((Class<T>) typeRef.getRawType()).getDeclaredConstructor().newInstance();
            }

            return object;
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize.", e);
        }
    }

    public <T> T read(String config, TypeReference<T> typeReference) {
        try {
            T object = JSON.parseObject(config, typeReference.getType());

            if (object == null) {
                return (T) typeReference.getRawType().getDeclaredConstructor().newInstance();
            }

            return object;
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize.", e);
        }
    }

    @Override
    public <T> String write(T data) {
        try {
            return JSON.toJSONString(data);
        } catch (JSONException e) {
            throw new RuntimeException("Failed to serialize.", e);
        }
    }
}

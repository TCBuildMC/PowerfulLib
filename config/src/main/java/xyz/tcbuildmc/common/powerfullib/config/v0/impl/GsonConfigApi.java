package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.ConfigApi;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.reflect.TypeRef;

public final class GsonConfigApi implements ConfigApi {
    private final ThreadLocal<Gson> gson;

    public GsonConfigApi(Gson gson) {
        this.gson = ThreadLocal.withInitial(() -> gson);
    }

    public GsonConfigApi() {
        this(new GsonBuilder().serializeNulls().setPrettyPrinting().create());
    }

    @Override
    public <T> T read(String config, Class<T> clazz) {
        try {
            T object = this.gson.get().fromJson(config, clazz);

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
            T object = this.gson.get().fromJson(config, typeRef.getType());

            if (object == null) {
                return ((Class<T>) typeRef.getRawType()).getDeclaredConstructor().newInstance();
            }

            return object;
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize.", e);
        }
    }

    public <T> T read(String config, TypeToken<T> typeToken) {
        try {
            T object = this.gson.get().fromJson(config, typeToken.getType());

            if (object == null) {
                return ((Class<T>) typeToken.getRawType()).getDeclaredConstructor().newInstance();
            }

            return object;
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize.", e);
        }
    }

    @Override
    public <T> String write(T data) {
        return this.gson.get().toJson(data);
    }
}

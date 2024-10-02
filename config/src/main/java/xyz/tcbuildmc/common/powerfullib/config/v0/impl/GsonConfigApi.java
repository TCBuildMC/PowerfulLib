package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import org.jetbrains.annotations.ApiStatus;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.IConfigApi;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@ApiStatus.Internal
public final class GsonConfigApi implements IConfigApi {
    private final ThreadLocal<Gson> gson;

    private GsonConfigApi(Gson gson) {
        this.gson = ThreadLocal.withInitial(() -> gson);
    }

    private GsonConfigApi() {
        this(new GsonBuilder().setPrettyPrinting().create());
    }

    public static IConfigApi create(Gson gson) {
        return new GsonConfigApi(gson);
    }

    public static IConfigApi create() {
        return new GsonConfigApi();
    }

    @Override
    public <T> T read(Class<T> clazz, String config) {
        try {
            T object = this.gson.get().fromJson(config, clazz);

            if (object == null) {
                return clazz.getDeclaredConstructor().newInstance();
            }

            return object;
        } catch (JsonSyntaxException |
                 JsonIOException |
                 InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException("Failed to deserialize Json.", e);
        }
    }

    @Override
    public <T> String write(T data) {
        return this.gson.get().toJson(data);
    }
}

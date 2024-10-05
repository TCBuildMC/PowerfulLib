package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.api.DeserializationException;
import blue.endless.jankson.api.SyntaxError;
import org.jetbrains.annotations.ApiStatus;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.IConfigApi;

import java.lang.reflect.InvocationTargetException;

@ApiStatus.Internal
public final class JanksonConfigApi implements IConfigApi {
    private final ThreadLocal<Jankson> jankson;

    private JanksonConfigApi(Jankson jankson) {
        this.jankson = ThreadLocal.withInitial(() -> jankson);
    }

    private JanksonConfigApi() {
        this(new Jankson.Builder().build());
    }

    public static IConfigApi create(Jankson jankson) {
        return new JanksonConfigApi(jankson);
    }

    public static IConfigApi create() {
        return new JanksonConfigApi();
    }

    @Override
    public <T> T read(Class<T> clazz, String config) {
        try {
            T object = this.jankson.get().fromJsonCarefully(config, clazz);

            if (object == null) {
                return clazz.getDeclaredConstructor().newInstance();
            }

            return object;
        } catch (SyntaxError |
                 DeserializationException |
                 InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException("Failed to deserialize Json.", e);
        }
    }

    @Override
    public <T> String write(T data)  {
        return this.jankson.get().toJson(data).toJson(true, true);
    }
}

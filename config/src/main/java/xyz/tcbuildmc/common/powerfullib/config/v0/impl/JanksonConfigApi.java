package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import blue.endless.jankson.Jankson;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.ConfigApi;

public final class JanksonConfigApi implements ConfigApi {
    private final ThreadLocal<Jankson> jankson;

    public JanksonConfigApi(Jankson jankson) {
        this.jankson = ThreadLocal.withInitial(() -> jankson);
    }

    public JanksonConfigApi() {
        this(new Jankson.Builder().build());
    }

    @Override
    public <T> T read(String config, Class<T> clazz) {
        try {
            T object = this.jankson.get().fromJsonCarefully(config, clazz);

            if (object == null) {
                return clazz.getDeclaredConstructor().newInstance();
            }

            return object;
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize Json.", e);
        }
    }

    @Override
    public <T> String write(T data)  {
        return this.jankson.get().toJson(data).toJson(true, true);
    }
}

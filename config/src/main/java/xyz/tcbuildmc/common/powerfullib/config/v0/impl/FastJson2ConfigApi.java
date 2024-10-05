package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import org.jetbrains.annotations.ApiStatus;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.IConfigApi;

import java.lang.reflect.InvocationTargetException;

@ApiStatus.Internal
public final class FastJson2ConfigApi implements IConfigApi {
    private FastJson2ConfigApi() {}

    public static IConfigApi create() {
        return new FastJson2ConfigApi();
    }

    @Override
    public <T> T read(Class<T> clazz, String config) {
        try {
            T object = JSON.parseObject(config, clazz);

            if (object == null) {
                return clazz.getDeclaredConstructor().newInstance();
            }

            return object;
        } catch (JSONException |
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
            return JSON.toJSONString(data);
        } catch (JSONException e) {
            throw new RuntimeException("Failed to serialize Json.", e);
        }
    }
}

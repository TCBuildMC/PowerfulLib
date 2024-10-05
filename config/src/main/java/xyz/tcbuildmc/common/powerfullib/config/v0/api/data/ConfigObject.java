package xyz.tcbuildmc.common.powerfullib.config.v0.api.data;

import org.jetbrains.annotations.Nullable;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.IConfigApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigObject extends HashMap<String, Object> {
    public ConfigObject() {
    }

    public ConfigObject(int initialCapacity) {
        super(initialCapacity);
    }

    public ConfigObject(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ConfigObject(Map<? extends String, ?> m) {
        super(m);
    }

    @Nullable
    public Object getByPath(String path) {
        String[] keys = path.split("\\.");

        Object self = new ConfigObject(this);
        for (String key : keys) {
            if (self instanceof Map) {
                Map<String, ?> instance = (Map<String, ?>) self;
                self = instance.get(key);
            } else if (self instanceof List) {
                List<?> instance = (List<?>) self;
                try {
                    int index = Integer.parseInt(key) - 1;
                    self = instance.get(index);
                } catch (Exception e) {
                    return null;
                }
            } else {
                break;
            }
        }

        return self;
    }

    public Object getByPathOrDefault(String path, Object defaultValue) {
        Object value = getByPath(path);
        return value != null ? value : defaultValue;
    }

    public Object set(String key, Object value) {
        if (value == null) {
            return super.remove(key);
        }

        return super.merge(key, value, (a, b) -> b);
    }

    public <T> T to(IConfigApi parent, Class<T> clazz) {
        String data = parent.write(this);
        return parent.read(clazz, data);
    }
}

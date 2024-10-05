package xyz.tcbuildmc.common.powerfullib.config.v0.api.data.concurrent;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.IConfigApi;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApiStatus.Experimental
public class ConcurrentConfigObject extends ConcurrentHashMap<String, Object> {
    public ConcurrentConfigObject() {
    }

    public ConcurrentConfigObject(int initialCapacity) {
        super(initialCapacity);
    }

    public ConcurrentConfigObject(Map<? extends String, ?> m) {
        super(m);
    }

    public ConcurrentConfigObject(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ConcurrentConfigObject(int initialCapacity, float loadFactor, int concurrencyLevel) {
        super(initialCapacity, loadFactor, concurrencyLevel);
    }

    @Nullable
    public Object getByPath(String path) {
        String[] keys = path.split("\\.");

        Object self = new ConcurrentConfigObject(this);
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

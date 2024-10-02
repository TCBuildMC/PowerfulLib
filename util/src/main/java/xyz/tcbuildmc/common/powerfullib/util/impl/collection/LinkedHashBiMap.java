package xyz.tcbuildmc.common.powerfullib.util.impl.collection;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashBiMap<K, V> extends HashBiMap<K, V> {
    public LinkedHashBiMap() {
        this(new LinkedHashMap<>(), new LinkedHashMap<>());
    }

    public LinkedHashBiMap(Map<K, V> k2vMap, Map<V, K> v2kMap) {
        super(k2vMap, v2kMap);
    }
}

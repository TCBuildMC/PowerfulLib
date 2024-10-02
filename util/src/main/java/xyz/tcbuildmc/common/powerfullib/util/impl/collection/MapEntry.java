package xyz.tcbuildmc.common.powerfullib.util.impl.collection;

import java.util.Map;

public final class MapEntry<K, V> implements Map.Entry<K, V> {
    private final K key;
    private V value;

    public MapEntry(K key) {
        this.key = key;
    }

    public MapEntry(K key, V value) {
        this(key);
        this.value = value;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    @Override
    public V setValue(V value) {
        this.value = value;
        return value;
    }
}

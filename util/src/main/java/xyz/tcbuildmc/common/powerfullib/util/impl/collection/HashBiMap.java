package xyz.tcbuildmc.common.powerfullib.util.impl.collection;

import org.jetbrains.annotations.Nullable;
import xyz.tcbuildmc.common.powerfullib.util.api.collection.BiMap;
import xyz.tcbuildmc.common.powerfullib.util.impl.Utils;

import java.util.*;
import java.util.function.Function;

public class HashBiMap<K, V> implements BiMap<K, V> {
    private final Map<K, V> k2vMap;
    private final Map<V, K> v2kMap;

    public HashBiMap() {
        this(new HashMap<>(), new HashMap<>());
    }

    public HashBiMap(Map<K, V> k2vMap, Map<V, K> v2kMap) {
        this.k2vMap = k2vMap;
        this.v2kMap = v2kMap;
    }

    @Override
    public void clear() {
        this.k2vMap.clear();
        this.v2kMap.clear();
    }

    @Override
    public int size() {
        return Math.min(this.k2vMap.size(), this.v2kMap.size());
    }

    @Override
    public @Nullable V getValue(K key) {
        return this.k2vMap.get(key);
    }

    @Override
    public @Nullable K getKey(V value) {
        return this.v2kMap.get(value);
    }

    @Override
    public void put(K key, V value) {
        this.k2vMap.put(key, value);
        this.v2kMap.put(value, key);
    }

    @Override
    public void removeValue(K key) {
        V value = this.k2vMap.remove(key);

        if (value != null) {
            this.v2kMap.remove(value);
        }
    }

    @Override
    public void removeKey(V value) {
        K key = this.v2kMap.remove(value);

        if (key != null) {
            this.k2vMap.remove(key);
        }
    }

    @Override
    public <A, B> BiMap<A, B> map(Function<K, A> keyMapper, Function<V, B> valueMapper) {
        HashBiMap<A, B> biMap = new HashBiMap<>();

        if (!Objects.equals(this.k2vMap, Collections.emptyMap())) {
            return biMap;
        }

        this.k2vMap.forEach((key, value) -> biMap.put(keyMapper.apply(key), valueMapper.apply(value)));
        return biMap;
    }

    @Override
    public Set<K> keySet() {
        return this.k2vMap.keySet();
    }

    @Override
    public Set<V> valueSet() {
        return this.v2kMap.keySet();
    }

    @Override
    public Set<BiMap.Entry<K, V>> entrySet() {
        HashSet<BiMap.Entry<K, V>> set = new HashSet<>();

        for (int i = 0; i < size(); i++) {
            set.add(new Entry<>(this.v2kMap.get(i), this.k2vMap.get(i)));
        }

        return set;
    }

    public static class Entry<K, V> implements BiMap.Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Entry() {
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
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public void setKey(K key) {
            this.key = key;
        }
    }

    public static final class Builder<K, V> {
        private final List<Entry<K, V>> entryList;

        public Builder(List<Entry<K, V>> entryList) {
            this.entryList = entryList;
        }

        public Builder() {
            this.entryList = new ArrayList<>();
        }

        public Builder<K, V> put(K key, V value) {
            this.entryList.add(new Entry<>(key, value));
            return this;
        }

        public Builder<K, V> put(Entry<K, V> entry) {
            this.entryList.add(entry);
            return this;
        }

        public Builder<K, V> putAll(List<Entry<K, V>> entryList) {
            this.entryList.addAll(entryList);
            return this;
        }

        public HashBiMap<K, V> create() {
            return Utils.make(new HashBiMap<>(), biMap -> {
                for (Entry<K, V> entry : this.entryList) {
                    biMap.put(entry.getKey(), entry.getValue());
                }
            });
        }
    }
}

package xyz.tcbuildmc.common.powerfullib.util.api.collection;

import org.jetbrains.annotations.Nullable;
import xyz.tcbuildmc.common.powerfullib.util.api.Cleanable;
import xyz.tcbuildmc.common.powerfullib.util.api.Sizeable;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.*;

public interface BiMap<K, V> extends Cleanable, Sizeable {
    default boolean isEmpty() {
        return size() == 0;
    }

    default boolean containsKey(K key) {
        return getValue(key) != null;
    }

    default boolean containsValue(V value) {
        return getKey(value) != null;
    }

    // Value Getter

    @Nullable
    V getValue(K key);

    default V getValueOrDefault(K key, V defaultValue) {
        return containsKey(key) ? getValue(key) : defaultValue;
    }

    
    default V getValueOrDefault(K key, Supplier<V> defaultValue) {
        return getValueOrDefault(key, defaultValue.get());
    }

    default V getValueOrThrow(K key) {
        return getValueOrThrow(key, new NullPointerException("No value for key " + key + " in " + getClass().getSimpleName()));
    }

    default <X extends Throwable> V getValueOrThrow(K key, X e) throws X {
        if (!containsKey(key)) {
            throw e;
        }

        return getValue(key);
    }
    
    default <X extends Throwable> V getValueOrThrow(K key, Supplier<X> e) throws X {
        if (!containsKey(key)) {
            throw e.get();
        }

        return getValue(key);
    }
    
    default Optional<V> getOptionalValue(K key) {
        return Optional.ofNullable(getValue(key));
    }

    default V getValueOrPut(K key, V value) {
        if (getValue(key) == null) {
            put(key, value);
        }

        return getValue(key);
    }

    default V getValueOrPut(K key, Supplier<V> valueSupplier) {
        if (getValue(key) == null) {
            put(key, valueSupplier.get());
        }

        return getValue(key);
    }

    // Key Getter

    @Nullable
    K getKey(V value);

    default K getKeyOrDefault(V value, K defaultValue) {
        return containsValue(value) ? getKey(value) : defaultValue;
    }

    default K getKeyOrDefault(V value, Supplier<K> defaultValue) {
        return getKeyOrDefault(value, defaultValue.get());
    }

    default K getKeyOrThrow(V value) {
        return getKeyOrThrow(value, new NullPointerException("No value for key " + value + " in " + getClass().getSimpleName()));
    }

    default <X extends Throwable> K getKeyOrThrow(V value, X e) throws X {
        if (!containsValue(value)) {
            throw e;
        }

        return getKey(value);
    }

    default <X extends Throwable> K getKeyOrThrow(V value, Supplier<X> e) throws X {
        if (!containsValue(value)) {
            throw e.get();
        }

        return getKey(value);
    }

    default Optional<K> getOptionalKey(V value) {
        return Optional.ofNullable(getKey(value));
    }

    default K getKeyOrPut(V value, K key) {
        if (getKey(value) == null) {
            put(key, value);
        }

        return getKey(value);
    }

    default K getKeyOrPut(V value, Supplier<K> keySupplier) {
        if (getKey(value) == null) {
            put(keySupplier.get(), value);
        }

        return getKey(value);
    }

    // Modify

    // Entry Setter

    void put(K key, V value);

    default void putAll(Entry<K, V>... entries) {
        for (Entry<K, V> entry : entries) {
            put(entry.getKey(), entry.getValue());
        }
    }
    
    default void putAll(Map<? extends K, ? extends V> map) {
        map.forEach(this::put);
    }
    
    default void putAll(BiMap<? extends K, ? extends V> map) {
        map.forEach(this::put);
    }
    
    default void mergeValue(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        put(key, remappingFunction.apply(getValue(key), value));
    }

    default void mergeKey(V value, K key, BiFunction<? super K, ? super K, ? extends K> remappingFunction) {
        put(remappingFunction.apply(getKey(value), key), value);
    }

    // Remove

    void removeValue(K key);

    default void removeValue(K key, V oldValue) {
        if (Objects.equals(getValue(key), oldValue)) {
            removeValue(key);
        }
    }

    default void removeValueIf(Predicate<K> keyPredicate) {
        for (Entry<K, V> entry : entrySet()) {
            if (keyPredicate.test(entry.getKey())) {
                removeValue(entry.getKey());
            }
        }
    }

    void removeKey(V value);

    default void removeKey(V value, K oldKey) {
        if (Objects.equals(getKey(value), oldKey)) {
            removeKey(value);
        }
    }
    
    default void removeKeyIf(Predicate<V> valuePredicate) {
        for (Entry<K, V> entry : entrySet()) {
            if (valuePredicate.test(entry.getValue())) {
                removeKey(entry.getValue());
            }
        }
    }

    // Replace

    default void replaceValue(K key, V value) {
        mergeValue(key, value, (oldValue, newValue) -> newValue);
    }
    
    default void replaceValue(K key, V oldValue, V newValue) {
        mergeValue(key, newValue, (oldValue1, newValue1) -> {
            if (Objects.equals(oldValue1, oldValue)) {
                return newValue1;
            }

            return oldValue1;
        });
    }

    default void replaceValueIf(BiPredicate<K, V> predicate) {
        for (Entry<K, V> entry : entrySet()) {
            if (predicate.test(entry.getKey(), entry.getValue())) {
                replaceValue(entry.getKey(), entry.getValue());
            }
        }
    }

    default void replaceKey(V value, K key) {
        mergeKey(value, key, (oldKey, newKey) -> newKey);
    }

    default void replaceKey(V value, K oldKey, K newKey) {
        mergeKey(value, newKey, (oldKey1, newKey1) -> {
            if (Objects.equals(oldKey1, oldKey)) {
                return newKey1;
            }

            return oldKey1;
        });
    }

    default void replaceKeyIf(BiPredicate<V, K> predicate) {
        for (Entry<K, V> entry : entrySet()) {
            if (predicate.test(entry.getValue(), entry.getKey())) {
                replaceKey(entry.getValue(), entry.getKey());
            }
        }
    }

    // Compute

    default V computeValueIfAbsent(K key, Function<K, V> mappingFunction) {
        if (containsKey(key)) {
            if (mappingFunction.apply(key) != null) {
                put(key, mappingFunction.apply(key));
            }
        }

        return getValue(key);
    }

    default K computeKeyIfAbsent(V value, Function<V, K> mappingFunction) {
        if (containsValue(value)) {
            if (mappingFunction.apply(value) != null) {
                put(mappingFunction.apply(value), value);
            }
        }

        return getKey(value);
    }

    default V computeValueIfPresent(K key, BiFunction<K, V, V> remappingFunction) {
        if (containsKey(key)) {
            if (remappingFunction.apply(key, getValue(key)) != null) {
                put(key, remappingFunction.apply(key, getValue(key)));
            } else {
                removeValue(key);
            }
        }

        return getValue(key);
    }

    default K computeKeyIfPresent(V value, BiFunction<V, K, K> remappingFunction) {
        if (containsValue(value)) {
            if (remappingFunction.apply(value, getKey(value)) != null) {
                put(remappingFunction.apply(value, getKey(value)), value);
            } else {
                removeKey(value);
            }
        }

        return getKey(value);
    }

    // Map

    <A, B> BiMap<A, B> map(Function<K, A> keyMapper, Function<V, B> valueMapper);

    // Default

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    // Iterator

    Set<K> keySet();

    Set<V> valueSet();

    Set<BiMap.Entry<K, V>> entrySet();
    
    default void forEach(BiConsumer<K, V> action) {
        for (Entry<K, V> entry : entrySet()) {
            action.accept(entry.getKey(), entry.getValue());
        }
    }

    interface Entry<K, V> {
        K getKey();

        V getValue();

        void setValue(V value);

        void setKey(K key);

        @Override
        boolean equals(Object o);

        @Override
        int hashCode();
    }
}

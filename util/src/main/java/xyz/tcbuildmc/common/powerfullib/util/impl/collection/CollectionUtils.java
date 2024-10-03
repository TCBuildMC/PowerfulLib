package xyz.tcbuildmc.common.powerfullib.util.impl.collection;

import xyz.tcbuildmc.common.powerfullib.util.api.collection.BiMap;
import xyz.tcbuildmc.common.powerfullib.util.impl.Utils;

import java.util.*;

public class CollectionUtils {
    @SafeVarargs
    public static <E, T extends E> List<E> newArrayList(T... elements) {
        return Utils.make(new ArrayList<>(), list -> Collections.addAll(list, elements));
    }

    @SafeVarargs
    public static <E, T extends E> Set<E> newHashSet(T... elements) {
        return Utils.make(new HashSet<>(), set -> Collections.addAll(set, elements));
    }

    @SafeVarargs
    public static <K, V> Map<K, V> newHashMap(Map.Entry<K, V>... entries) {
        return Utils.make(new HashMap<>(), map -> {
            for (Map.Entry<K, V> entry : entries) {
                map.put(entry.getKey(), entry.getValue());
            }
        });
    }

    @SafeVarargs
    public static <K, V> Map<K, V> newLinkedHashMap(Map.Entry<K, V>... entries) {
        return Utils.make(new LinkedHashMap<>(), map -> {
            for (Map.Entry<K, V> entry : entries) {
                map.put(entry.getKey(), entry.getValue());
            }
        });
    }

    @SafeVarargs
    public static <K, V> BiMap<K, V> newHashBiMap(BiMap.Entry<K, V>... entries) {
        return Utils.make(new HashBiMap<>(), map -> map.putAll(entries));
    }

    @SafeVarargs
    public static <E> void removeAll(Collection<E> c, E... remove) {
        for (E e : remove) {
            c.removeIf(e::equals);
        }
    }
}

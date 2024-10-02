package xyz.tcbuildmc.common.powerfullib.event.v0.api;

import xyz.tcbuildmc.common.powerfullib.event.v0.api.listener.IListenerEntry;
import xyz.tcbuildmc.common.powerfullib.event.v0.api.priority.IEventPriority;
import xyz.tcbuildmc.common.powerfullib.event.v0.impl.listener.ListenerEntryImpl;

public interface IEvent<T> {
    void invoke();

    // Registry

    void register(IListenerEntry<T> entry);

    default void register(T listener, IEventPriority priority) {
        register(new ListenerEntryImpl<>(listener, priority));
    }

    default void registerAll(IListenerEntry<T>... entries) {
        for (IListenerEntry<T> entry : entries) {
            register(entry);
        }
    }

    default void registerAll(IEventPriority priority, T... listeners) {
        for (T listener : listeners) {
            register(listener, priority);
        }
    }

    default void register(T... listeners) {
        registerAll(IEventPriority.NORMAL, listeners);
    }

    // Unregistry

    void unregister(IListenerEntry<T> entry);

    default void unregister(T listener, IEventPriority priority) {
        unregister(new ListenerEntryImpl<>(listener, priority));
    }

    default void unregisterAll(IListenerEntry<T>... entries) {
        for (IListenerEntry<T> entry : entries) {
            unregister(entry);
        }
    }

    default void unregisterAll(IEventPriority priority, T... listeners) {
        for (T listener : listeners) {
            unregister(listener, priority);
        }
    }

    default void unregister(T... listeners) {
        unregisterAll(IEventPriority.NORMAL, listeners);
    }
}

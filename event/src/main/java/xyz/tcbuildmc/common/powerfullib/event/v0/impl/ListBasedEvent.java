package xyz.tcbuildmc.common.powerfullib.event.v0.impl;

import xyz.tcbuildmc.common.powerfullib.event.v0.api.IEvent;
import xyz.tcbuildmc.common.powerfullib.util.impl.collection.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ListBasedEvent<T> implements IEvent<T> {
    private final List<T> listeners;

    public ListBasedEvent() {
        this(new ArrayList<>());
    }

    public ListBasedEvent(List<T> listeners) {
        this.listeners = listeners;
    }

    @Override
    public void register(T... listeners) {
        Collections.addAll(this.listeners, listeners);
    }

    @Override
    public void unregister(T... listeners) {
        CollectionUtils.removeAll(this.listeners, listeners);
    }
}

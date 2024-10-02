package xyz.tcbuildmc.common.powerfullib.event.v0.impl;

import xyz.tcbuildmc.common.powerfullib.event.v0.api.IEvent;
import xyz.tcbuildmc.common.powerfullib.event.v0.api.listener.IListenerEntry;

import java.util.ArrayList;
import java.util.List;

// TODO Priority
public abstract class ListBasedEvent<T> implements IEvent<T> {
    private final List<IListenerEntry<T>> listeners;

    public ListBasedEvent() {
        this(new ArrayList<>());
    }

    public ListBasedEvent(List<IListenerEntry<T>> listeners) {
        this.listeners = listeners;
    }

    @Override
    public void invoke() {
        this.listeners.sort(IListenerEntry::sort);
    }

    public abstract void invokeEach(T listener);

    @Override
    public void register(IListenerEntry<T> entry) {
        this.listeners.add(entry);
    }

    @Override
    public void unregister(IListenerEntry<T> entry) {

    }
}

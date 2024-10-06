package xyz.tcbuildmc.common.powerfullib.event.v0.impl;

import xyz.tcbuildmc.common.powerfullib.event.v0.api.Event;

import java.lang.reflect.Array;

public abstract class ArrayBasedEvent<T> implements Event<T> {
    private final T[] listeners;

    public ArrayBasedEvent(Class<T> clazz) {
        this.listeners = (T[]) Array.newInstance(clazz, Integer.MAX_VALUE);
    }
}

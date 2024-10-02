package xyz.tcbuildmc.common.powerfullib.event.v0.api.listener;

import xyz.tcbuildmc.common.powerfullib.event.v0.impl.priority.EventPriorityImpl;

public interface IListenerEntry<T> {
    T getListener();

    EventPriorityImpl getPriority();

    static int sort(IListenerEntry<?> e1, IListenerEntry<?> e2) {
        return e1.getPriority().compareTo(e2.getPriority());
    }
}

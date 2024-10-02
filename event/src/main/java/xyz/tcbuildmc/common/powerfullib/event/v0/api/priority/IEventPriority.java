package xyz.tcbuildmc.common.powerfullib.event.v0.api.priority;

import xyz.tcbuildmc.common.powerfullib.event.v0.impl.priority.EventPriorityImpl;

public interface IEventPriority extends Comparable<IEventPriority> {
    IEventPriority LOWEST = new EventPriorityImpl(Integer.MIN_VALUE);
    IEventPriority LOW = new EventPriorityImpl(-1000);
    IEventPriority NORMAL = new EventPriorityImpl(0);
    IEventPriority HIGH = new EventPriorityImpl(1000);
    IEventPriority HIGHEST = new EventPriorityImpl(Integer.MAX_VALUE);

    int getPriority();
}

package xyz.tcbuildmc.common.powerfullib.event.v0.impl.listener;

import lombok.Getter;
import org.jetbrains.annotations.ApiStatus;
import xyz.tcbuildmc.common.powerfullib.event.v0.api.listener.IListenerEntry;
import xyz.tcbuildmc.common.powerfullib.event.v0.api.priority.IEventPriority;

@Getter
@ApiStatus.Internal
public final class ListenerEntryImpl<T> implements IListenerEntry<T> {
    private final T listener;
    private final IEventPriority priority;

    public ListenerEntryImpl(T listener, IEventPriority priority) {
        this.listener = listener;
        this.priority = priority;
    }
}

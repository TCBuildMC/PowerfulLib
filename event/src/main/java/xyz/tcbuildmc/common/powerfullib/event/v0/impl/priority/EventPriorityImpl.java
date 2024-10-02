package xyz.tcbuildmc.common.powerfullib.event.v0.impl.priority;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.tcbuildmc.common.powerfullib.event.v0.api.priority.IEventPriority;

@Data
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@ApiStatus.Internal
public final class EventPriorityImpl implements IEventPriority {
    private final int priority;

    @Override
    public int compareTo(@NotNull IEventPriority o) {
        return Integer.compare(this.priority, o.getPriority());
    }
}

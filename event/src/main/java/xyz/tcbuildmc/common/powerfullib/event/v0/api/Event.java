package xyz.tcbuildmc.common.powerfullib.event.v0.api;

public interface Event<T> {
    void call();

    void register(T... listeners);

    void unregister(T... listeners);
}

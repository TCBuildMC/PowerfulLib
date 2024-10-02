package xyz.tcbuildmc.common.powerfullib.util.api;

import java.io.IOException;

@FunctionalInterface
public interface Reloadable {
    void reload() throws IOException;
}

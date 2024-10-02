package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import org.junit.jupiter.api.Test;
import xyz.tcbuildmc.common.powerfullib.config.v0.impl.SnakeYamlConfigApi;
import xyz.tcbuildmc.common.powerfullib.util.impl.collection.CollectionUtils;
import xyz.tcbuildmc.common.powerfullib.util.impl.collection.MapEntry;

import java.io.IOException;
import java.util.Map;

class SnakeYamlConfigApiTest {
    @Test
    void read() throws IOException {
        System.out.println(SnakeYamlConfigApi.create().read(
                Map.class,
                "name: George\nage: 18"));
    }

    @Test
    void write() throws IOException {
        System.out.println(SnakeYamlConfigApi.create().write(CollectionUtils.newHashMap(
                new MapEntry<>("name", "George"),
                new MapEntry<>("age", 18))));
    }
}

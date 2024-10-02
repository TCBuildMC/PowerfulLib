package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import org.junit.jupiter.api.Test;
import xyz.tcbuildmc.common.powerfullib.config.v0.impl.FastJson2ConfigApi;
import xyz.tcbuildmc.common.powerfullib.util.impl.collection.CollectionUtils;
import xyz.tcbuildmc.common.powerfullib.util.impl.collection.MapEntry;

import java.io.IOException;
import java.util.Map;

class FastJson2ConfigApiTest {
    @Test
    void read() throws IOException {
        System.out.println(FastJson2ConfigApi.create().read(
                Map.class,
                "{\"name\": \"George\", \"age\": 18}"));
    }

    @Test
    void write() throws IOException {
        System.out.println(FastJson2ConfigApi.create().write(CollectionUtils.newHashMap(
                new MapEntry<>("name", "George"),
                new MapEntry<>("age", 18))));
    }
}

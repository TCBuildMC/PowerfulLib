package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import org.junit.jupiter.api.Test;
import xyz.tcbuildmc.common.powerfullib.config.v0.impl.GsonConfigApi;
import xyz.tcbuildmc.common.powerfullib.util.impl.collection.CollectionUtils;
import xyz.tcbuildmc.common.powerfullib.util.impl.collection.MapEntry;

import java.io.IOException;
import java.util.Map;

class JanksonConfigApiTest {
    @Test
    void read() throws IOException {
        System.out.println(GsonConfigApi.create().read(
                Map.class,
                "{// Hey George\n\"name\": \"George\", \"age\": 18}"));
    }

    @Test
    void write() throws IOException {
        System.out.println(GsonConfigApi.create().write(CollectionUtils.newHashMap(
                new MapEntry<>("name", "George"),
                new MapEntry<>("age", 18))));
    }
}

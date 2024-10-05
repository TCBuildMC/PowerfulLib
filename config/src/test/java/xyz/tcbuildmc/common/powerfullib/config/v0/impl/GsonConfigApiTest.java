package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import org.junit.jupiter.api.Test;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.reflect.TypeRef;
import xyz.tcbuildmc.common.powerfullib.util.impl.collection.CollectionUtils;

import java.util.List;

class GsonConfigApiTest {
    @Test
    void read() {
        System.out.println(new GsonConfigApi().read(
                "{\"name\": \"George\", \"age\": 18}", TestObject.class));

        List<TestObject> list = new GsonConfigApi().read("[\n" +
                "  {\n" +
                "    \"name\": \"George\",\n" +
                "    \"age\": 18\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Rickroll\",\n" +
                "    \"age\": 18\n" +
                "  }\n" +
                "]", new TypeRef<List<TestObject>>() {});

        for (TestObject testObject : list) {
            System.out.println(testObject);
        }
    }

    @Test
    void write() {
        System.out.println(new GsonConfigApi().write(
                new TestObject("George", 18)));

        System.out.println(new GsonConfigApi().write(
                CollectionUtils.newArrayList(new TestObject("George", 18), new TestObject("Rickroll", 18))));
    }
}

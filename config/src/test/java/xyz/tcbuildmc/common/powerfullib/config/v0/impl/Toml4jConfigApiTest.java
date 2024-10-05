package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import org.junit.jupiter.api.Test;
import xyz.tcbuildmc.common.powerfullib.config.v0.impl.Toml4jConfigApi;
import xyz.tcbuildmc.common.powerfullib.util.impl.collection.CollectionUtils;
import xyz.tcbuildmc.common.powerfullib.util.impl.collection.MapEntry;

import java.io.IOException;
import java.util.Map;

class Toml4jConfigApiTest {
    @Test
    void read() throws IOException {
        System.out.println(Toml4jConfigApi.create().read(
                Map.class,
                "name = \"George\"\nage = 18"));
    }

    @Test
    void write() throws IOException {
        System.out.println(Toml4jConfigApi.create().write(CollectionUtils.newHashMap(
                new MapEntry<>("name", "George"),
                new MapEntry<>("age", 18))));
    }

    @Test
    void read2() {
        String toml = "[command]\n" +
                "help = \"See GitHub: https://github.com/TCBuildMC/GrassBackup-Bukkit\"\n" +
                "reload = \"Reloading...\"\n" +
                "list = \"List of backups: (Name | Comment)\"\n" +
                "\n" +
                "[command.make]\n" +
                "start = \"Start to make backup...\"\n" +
                "success = \"Backup success: %s\"\n" +
                "fail = \"Backup fail: %s\"\n";
        System.out.println(Toml4jConfigApi.create().read(Map.class, toml));
    }
}

package xyz.tcbuildmc.common.powerfullib.config.v0.impl.manager;

import org.jetbrains.annotations.NotNull;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.ConfigApi;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.manager.AbstractConfigManager;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.manager.ConfigManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class FileConfigManager<T> extends AbstractConfigManager<T> implements ConfigManager<T> {
    private final File configFile;

    public FileConfigManager(Class<T> clazz, ConfigApi parent, File configFile) {
        super(clazz, parent);
        this.configFile = configFile;
    }

    public FileConfigManager(Class<T> clazz, ConfigApi parent, String path) {
        this(clazz, parent, new File(path));
    }

    public FileConfigManager(Class<T> clazz, ConfigApi parent, String path, String name) {
        this(clazz, parent, new File(path, name));
    }

    public FileConfigManager(Class<T> clazz, ConfigApi parent, @NotNull Path path) {
        this(clazz, parent, path.toFile());
    }

    @Override
    public void load() {
        this.instance = this.parent.read(this.configFile, this.clazz);
    }

    @Override
    public void save() {
        this.parent.write(this.instance, this.configFile);
    }
}

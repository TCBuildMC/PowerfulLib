package xyz.tcbuildmc.common.powerfullib.config.v0.impl.manager;

import org.jetbrains.annotations.NotNull;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.IConfigApi;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.manager.AbstractConfigManager;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.manager.IConfigManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class FileConfigManager<T> extends AbstractConfigManager<T> implements IConfigManager<T> {
    private final File configFile;

    public FileConfigManager(Class<T> clazz, IConfigApi parent, File configFile) {
        super(clazz, parent);
        this.configFile = configFile;
    }

    public FileConfigManager(Class<T> clazz, IConfigApi parent, String path) {
        this(clazz, parent, new File(path));
    }

    public FileConfigManager(Class<T> clazz, IConfigApi parent, String path, String name) {
        this(clazz, parent, new File(path, name));
    }

    public FileConfigManager(Class<T> clazz, IConfigApi parent, @NotNull Path path) {
        this(clazz, parent, path.toFile());
    }

    @Override
    public void load() throws IOException {
        this.instance = this.parent.read(this.clazz, this.configFile);
    }

    @Override
    public void save() throws IOException {
        this.parent.write(this.instance, this.configFile);
    }
}

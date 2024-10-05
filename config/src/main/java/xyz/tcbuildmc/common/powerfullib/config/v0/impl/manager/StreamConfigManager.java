package xyz.tcbuildmc.common.powerfullib.config.v0.impl.manager;

import xyz.tcbuildmc.common.powerfullib.config.v0.api.ConfigApi;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.manager.AbstractConfigManager;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.manager.ConfigManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamConfigManager<T> extends AbstractConfigManager<T> implements ConfigManager<T> {
    private final InputStream input;
    private final OutputStream output;

    public StreamConfigManager(Class<T> clazz, ConfigApi parent, InputStream input, OutputStream output) {
        super(clazz, parent);
        this.input = input;
        this.output = output;
    }

    @Override
    public void load() {
        this.instance = this.parent.read(this.input, this.clazz);
    }

    @Override
    public void save() {
        this.parent.write(this.instance, this.output);
    }

    @Override
    public void close() throws IOException {
        super.close();
        this.input.close();
        this.output.close();
    }
}

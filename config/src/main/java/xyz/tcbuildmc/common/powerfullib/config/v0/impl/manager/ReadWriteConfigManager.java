package xyz.tcbuildmc.common.powerfullib.config.v0.impl.manager;

import xyz.tcbuildmc.common.powerfullib.config.v0.api.IConfigApi;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.manager.AbstractConfigManager;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.manager.IConfigManager;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class ReadWriteConfigManager<T> extends AbstractConfigManager<T> implements IConfigManager<T>, Closeable {
    private final Reader reader;
    private final Writer writer;

    public ReadWriteConfigManager(Class<T> clazz, IConfigApi parent, Reader reader, Writer writer) {
        super(clazz, parent);
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void load() throws IOException {
        this.instance = this.parent.read(this.clazz, this.reader);
    }

    @Override
    public void save() throws IOException {
        this.parent.write(this.instance, this.writer);
    }

    @Override
    public void close() throws IOException {
        super.close();
        this.reader.close();
        this.writer.close();
    }
}

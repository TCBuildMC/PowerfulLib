package xyz.tcbuildmc.common.powerfullib.config.v0.api;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.reflect.TypeRef;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

@SuppressWarnings("unused")
public interface ConfigApi {
    <T> T read(String config, Class<T> clazz);

    default <T> T read(String config, TypeRef<T> typeRef) {
        return read(config, (Class<T>) typeRef.getType());
    }

    default <T> T read(File config, Class<T> clazz) {
        return this.read(config, StandardCharsets.UTF_8, clazz);
    }

    default <T> T read(File file, TypeRef<T> typeRef) {
        return this.read(file, StandardCharsets.UTF_8, typeRef);
    }

    default <T> T read(File config, Charset charset, Class<T> clazz) {
        try {
            return this.read(FileUtils.readFileToString(config, charset), clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from File.", e);
        }
    }

    default <T> T read(File file, Charset charset, TypeRef<T> typeRef) {
        try {
            return this.read(FileUtils.readFileToString(file, charset), typeRef);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from File.", e);
        }
    }

    default <T> T read(Path config, Class<T> clazz) {
        return this.read(config, StandardCharsets.UTF_8, clazz);
    }

    default <T> T read(Path config, TypeRef<T> typeRef) {
        return this.read(config, StandardCharsets.UTF_8, typeRef);
    }

    default <T> T read(@NotNull Path config, Charset charset, Class<T> clazz) {
        return this.read(config.toFile(), charset, clazz);
    }

    default <T> T read(@NotNull Path config, Charset charset, TypeRef<T> typeRef) {
        return this.read(config.toFile(), charset, typeRef);
    }

    default <T> T read(InputStream config, Class<T> clazz) {
        return this.read(config, StandardCharsets.UTF_8, clazz);
    }

    default <T> T read(InputStream config, TypeRef<T> typeRef) {
        return this.read(config, StandardCharsets.UTF_8, typeRef);
    }

    default <T> T read(InputStream config, Charset charset, Class<T> clazz) {
        try {
            return this.read(IOUtils.toString(config, charset), clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from InputStream.", e);
        }
    }

    default <T> T read(InputStream config, Charset charset, TypeRef<T> typeRef) {
        try {
            return this.read(IOUtils.toString(config, charset), typeRef);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from InputStream.", e);
        }
    }

    default <T> T read(Reader config, Class<T> clazz) {
        try {
            return this.read(IOUtils.toString(config), clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read by Reader.", e);
        }
    }

    default <T> T read(Reader config, TypeRef<T> typeRef) {
        try {
            return this.read(IOUtils.toString(config), typeRef);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read by Reader.", e);
        }
    }

    default <T> T read(URI config, Class<T> clazz) {
        return this.read(config, StandardCharsets.UTF_8, clazz);
    }

    default <T> T read(URI config, TypeRef<T> typeRef) {
        return this.read(config, StandardCharsets.UTF_8, typeRef);
    }

    default <T> T read(URI config, Charset charset, Class<T> clazz) {
        try {
            return this.read(IOUtils.toString(config, charset), clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from URI.", e);
        }
    }

    default <T> T read(URI config, Charset charset, TypeRef<T> typeRef) {
        try {
            return this.read(IOUtils.toString(config, charset), typeRef);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from URI.", e);
        }
    }

    default <T> T read(URL config, Class<T> clazz) {
        return this.read(config, StandardCharsets.UTF_8, clazz);
    }

    default <T> T read(URL config, TypeRef<T> typeRef) {
        return this.read(config, StandardCharsets.UTF_8, typeRef);
    }

    default <T> T read(URL config, Charset charset, Class<T> clazz) {
        try {
            return this.read(config.toURI(), charset, clazz);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to encode URL to URI.", e);
        }
    }

    default <T> T read(URL config, Charset charset, TypeRef<T> typeRef) {
        try {
            return this.read(config.toURI(), charset, typeRef);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to encode URL to URI.", e);
        }
    }

    default <T> T read(byte[] config, Class<T> clazz) {
        return this.read(config, StandardCharsets.UTF_8, clazz);
    }

    default <T> T read(byte[] config, TypeRef<T> typeRef) {
        return this.read(config, StandardCharsets.UTF_8, typeRef);
    }

    default <T> T read(byte[] config, Charset charset, Class<T> clazz) {
        return this.read(new String(config, charset), clazz);
    }

    default <T> T read(byte[] config, Charset charset, TypeRef<T> typeRef) {
        return this.read(new String(config, charset), typeRef);
    }

    <T> String write(T data);

    default <T> void write(T data, File config) {
        this.write(data, config, StandardCharsets.UTF_8);
    }

    default <T> void write(T data, File config, Charset charset) {
        try {
            FileUtils.writeStringToFile(config, this.write(data), charset);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to File.", e);
        }
    }

    default <T> void write(T data, Path config) {
        this.write(data, config, StandardCharsets.UTF_8);
    }

    default <T> void write(T data, @NotNull Path config, Charset charset) {
        this.write(data, config.toFile(), charset);
    }

    default <T> void write(T data, OutputStream config) {
        this.write(data, config, StandardCharsets.UTF_8);
    }

    default <T> void write(T data, OutputStream config, Charset charset) {
        try {
            IOUtils.write(this.write(data), config, charset);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to OutputStream.", e);
        }
    }

    default <T> void write(T data, Writer config) {
        try {
            IOUtils.write(this.write(data), config);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write by Writer.", e);
        }
    }

    default <T> byte[] writeToBytes(T data) {
        return this.writeToBytes(data, StandardCharsets.UTF_8);
    }

    default <T> byte[] writeToBytes(T data, Charset charset) {
        return this.write(data).getBytes(charset);
    }
}

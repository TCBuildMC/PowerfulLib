package xyz.tcbuildmc.common.powerfullib.config.v0.api;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

@SuppressWarnings("unused")
public interface IConfigApi {
    <T> T read(Class<T> clazz, String config);

    default <T> T read(Class<T> clazz, File config) {
        return this.read(clazz, config, StandardCharsets.UTF_8);
    }

    default <T> T read(Class<T> clazz, File config, Charset charset) {
        try {
            return this.read(clazz, FileUtils.readFileToString(config, charset));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from File.", e);
        }
    }

    default <T> T read(Class<T> clazz, Path config) {
        return this.read(clazz, config, StandardCharsets.UTF_8);
    }

    default <T> T read(Class<T> clazz, @NotNull Path config, Charset charset) {
        return this.read(clazz, config.toFile(), charset);
    }

    default <T> T read(Class<T> clazz, InputStream config) {
        return this.read(clazz, config, StandardCharsets.UTF_8);
    }

    default <T> T read(Class<T> clazz, InputStream config, Charset charset) {
        try {
            return this.read(clazz, IOUtils.toString(config, charset));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from InputStream.", e);
        }
    }

    default <T> T read(Class<T> clazz, Reader config) {
        try {
            return this.read(clazz, IOUtils.toString(config));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read by Reader.", e);
        }
    }

    default <T> T read(Class<T> clazz, URI config) {
        return this.read(clazz, config, StandardCharsets.UTF_8);
    }

    default <T> T read(Class<T> clazz, URI config, Charset charset) {
        try {
            return this.read(clazz, IOUtils.toString(config, charset));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from URI.", e);
        }
    }

    default <T> T read(Class<T> clazz, URL config) {
        return this.read(clazz, config, StandardCharsets.UTF_8);
    }

    default <T> T read(Class<T> clazz, URL config, Charset charset) {
        try {
            return this.read(clazz, config.toURI(), charset);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to encode URL to URI.", e);
        }
    }

    default <T> T readToBytes(Class<T> clazz, byte[] config) {
        return this.read(clazz, new String(config, StandardCharsets.UTF_8));
    }

    default <T> T readToBytes(Class<T> clazz, byte[] config, Charset charset) {
        return this.read(clazz, new String(config, charset));
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

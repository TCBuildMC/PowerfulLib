package xyz.tcbuildmc.common.powerfullib.i18n.v1;

import xyz.tcbuildmc.common.powerfullib.config.v0.api.ConfigApi;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.reflect.TypeRef;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Localization helper.
 */
public interface Translations {
    /**
     * All the translations.
     */
    Map<String, String> TRANSLATIONS = new ConcurrentHashMap<>();

    static String getLocalizedLanguage() {
        return Locale.getDefault().getDisplayLanguage().toLowerCase(Locale.ROOT);
    }

    static void load(URI uri, ConfigApi parent) {
        Map<String, String> data = parent.read(uri, new TypeRef<Map<String, String>>() {});
        TRANSLATIONS.putAll(data);
    }

    static void load(URL url, ConfigApi parent) {
        Map<String, String> data = parent.read(url, new TypeRef<Map<String, String>>() {});
        TRANSLATIONS.putAll(data);
    }

    static void load(File file, ConfigApi parent) {
        Map<String, String> data = parent.read(file, new TypeRef<Map<String, String>>() {});
        TRANSLATIONS.putAll(data);
    }

    static void load(Path path, ConfigApi parent) {
        Map<String, String> data = parent.read(path, new TypeRef<Map<String, String>>() {});
        TRANSLATIONS.putAll(data);
    }

    static void load(String path, String ext, ConfigApi parent) {
        try (InputStream is = Translations.class.getClassLoader().getResourceAsStream(path + "/" + getLocalizedLanguage() + "." + ext)) {
            if (is == null) {
                return;
            }

            Map<String, String> data = parent.read(is, new TypeRef<Map<String, String>>() {});
            TRANSLATIONS.putAll(data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to get resources from classpath.", e);
        }
    }

    static void unload() {
        TRANSLATIONS.clear();
    }

    static String tr(String key) {
        return tr(key, key);
    }

    static String tr(String key, Object... args) {
        return String.format(tr(key), args);
    }

    static String tr(String key, String defaultValue) {
        return TRANSLATIONS.getOrDefault(key, defaultValue);
    }

    static String tr(String key, String defaultValue, Object... args) {
        return String.format(tr(key, defaultValue), args);
    }

    static boolean hasAny() {
        return !TRANSLATIONS.isEmpty();
    }

    static boolean has(String key) {
        return TRANSLATIONS.containsKey(key);
    }
}

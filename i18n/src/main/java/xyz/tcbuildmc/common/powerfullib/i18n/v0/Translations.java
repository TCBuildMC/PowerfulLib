package xyz.tcbuildmc.common.powerfullib.i18n.v0;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import xyz.tcbuildmc.common.powerfullib.config.v0.api.IConfigApi;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Getter
public final class Translations {
    private final Map<String, String> translations;

    public Translations() {
        this(new HashMap<>());
    }

    public Translations(Map<String, String> translations) {
        this.translations = translations;
    }

    public static String getLanguageCode() {
        return getLanguageCode("_");
    }

    public static String getLanguageCode(String spec) {
        return Locale.getDefault().getLanguage() + spec + Locale.getDefault().getCountry().toLowerCase(Locale.ROOT);
    }

    public static Translations loadFromClasspathFile(@NotNull IConfigApi parent, String extName) throws IOException {
        try (InputStream is = Translations.class.getClassLoader().getResourceAsStream("lang/" + getLanguageCode() + "." + extName)) {
            return new Translations((Map<String, String>) parent.read(Map.class, is));
        }
    }

    public boolean has(String key) {
        return this.translations.containsKey(key);
    }

    public String tr(String key) {
        return tr(key, key);
    }

    public String tr(String key, String defaultValue) {
        return this.translations.getOrDefault(key, defaultValue);
    }
}

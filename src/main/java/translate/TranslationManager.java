package translate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class TranslationManager {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Map<Language, Map<String, Object>> translations = new ConcurrentHashMap<>();

    private TranslationManager() {
    } // утилитарный класс

    public static String get(Language lang, String dotPath) {
        if (lang == null || dotPath == null || dotPath.isEmpty()) {
            return "INVALID_ARGS";
        }

        Object current = translations.computeIfAbsent(lang, TranslationManager::loadTranslation);
        for (String key : dotPath.split("\\.")) {
            if (!(current instanceof Map<?, ?> m)) {
                return "???" + dotPath;
            }
            current = m.get(key);
            if (current == null) {
                return "???" + dotPath;
            }
        }
        return current.toString();
    }

    private static Map<String, Object> loadTranslation(Language lang) {
        String fileName = "/translations/" + lang.name().toLowerCase() + ".json";
        try (InputStream is = TranslationManager.class.getResourceAsStream(fileName)) {
            if (is == null) {
                log.warn("Missing translation file: {}", fileName);
                return Collections.emptyMap();
            }
            return mapper.readValue(is, new TypeReference<>() {
            });
        } catch (Exception e) {
            log.error("Failed to load translations for {}", lang, e);
            return Collections.emptyMap();
        }
    }
}

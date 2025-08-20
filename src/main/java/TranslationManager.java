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
            log.warn("Некорректные аргументы: язык = {}, путь = {}", lang, dotPath);
            return "INVALID_ARGS";
        }

        Object current = translations.computeIfAbsent(lang, TranslationManager::loadTranslation);
        for (String key : dotPath.split("\\.")) {
            if (!(current instanceof Map<?, ?> m)) {
                log.warn("Путь '{}' не найден в переводах для языка {}", dotPath, lang);
                return "???" + dotPath;
            }
            current = m.get(key);
            if (current == null) {
                log.warn("Отсутствует ключ '{}' в переводах для языка {}", dotPath, lang);
                return "???" + dotPath;
            }
        }
        return current.toString();
    }

    private static Map<String, Object> loadTranslation(Language lang) {
        String fileName = "/translations/" + lang.name().toLowerCase() + ".json";
        try (InputStream is = TranslationManager.class.getResourceAsStream(fileName)) {
            if (is == null) {
                log.warn("Файл перевода не найден: {}", fileName);
                return Collections.emptyMap();
            }
            log.info("Загружен файл перевода: {}", fileName);
            return mapper.readValue(is, new TypeReference<>() {
            });
        } catch (Exception e) {
            log.error("Не удалось загрузить переводы для языка {} из файла {}", lang, fileName, e);
            return Collections.emptyMap();
        }
    }
}

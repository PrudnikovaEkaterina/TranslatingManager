package translate;

import translate.dictionary.EnglishTranslations;
import translate.dictionary.GeorgianTranslations;
import translate.dictionary.RussianTranslations;

public class TranslationManager {
    private final Translation translations;

    public TranslationManager(Language language) {
        this.translations = createTranslations(language);
    }

    private Translation createTranslations(Language language) {
        switch (language) {
            case RU: return new RussianTranslations();
            case EN: return new EnglishTranslations();
            case KA: return new GeorgianTranslations();
            default: throw new IllegalArgumentException("Нет такого языка");
        }
    }

    public Translation.ProfileTranslations profilePage() {
        return translations.profilePage();
    }

    public Translation.RegistrationTranslations registrationPage() {
        return translations.registrationPage();
    }
}
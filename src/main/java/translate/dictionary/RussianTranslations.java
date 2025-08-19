package translate.dictionary;

import translate.Translation;

public class RussianTranslations implements Translation {
    @Override
    public RegistrationTranslations registrationPage() {
        return new RegistrationTranslations() {
            @Override
            public String confirmAge18AndTerms() {return "Я подтверждаю, что мне исполнилось 18 лет и принимаю условия";}
        };
    }

    @Override
    public ProfileTranslations profilePage() {
        return new ProfileTranslations() {
            @Override
            public String female() {return "Женский";}
        };
    }
}
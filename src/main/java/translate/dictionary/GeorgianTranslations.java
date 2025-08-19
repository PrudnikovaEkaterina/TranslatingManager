package translate.dictionary;

import translate.Translation;

public class GeorgianTranslations implements Translation {
    @Override
    public RegistrationTranslations registrationPage() {
        return new RegistrationTranslations() {
            @Override
            public String confirmAge18AndTerms() {return "ადასტურებ, რომ 18 წლის ვარ და ვეთანხმები პირობებს";}
        };
    }

    @Override
    public ProfileTranslations profilePage() {
        return new ProfileTranslations() {
            @Override
            public String female() {return "მდედრობითი";}
        };
    }
}
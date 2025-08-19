package translate.dictionary;

import translate.Translation;

public class EnglishTranslations implements Translation {
    @Override
    public RegistrationTranslations registrationPage() {
        return new RegistrationTranslations() {
            @Override
            public String confirmAge18AndTerms() {return "I confirm that I am 18 years of age or older and accept the terms and conditions";}
        };
    }

    @Override
    public ProfileTranslations profilePage() {
        return new ProfileTranslations() {
            @Override
            public String female() {return "female";}
        };
    }
}
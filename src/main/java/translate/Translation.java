package translate;

public interface Translation {

    RegistrationTranslations registrationPage();
    ProfileTranslations profilePage();

    public interface RegistrationTranslations {
        String confirmAge18AndTerms();
    }

    public interface ProfileTranslations {
        String female();
    }
}
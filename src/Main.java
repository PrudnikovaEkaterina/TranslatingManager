import translate.Language;
import translate.TranslationManager;

public class Main {
    public static void main(String[] args) {

        TranslationManager manager = new TranslationManager(Language.RU);

        System.out.println(manager.profilePage().female());
        System.out.println(manager.registrationPage().confirmAge18AndTerms());

    }
}
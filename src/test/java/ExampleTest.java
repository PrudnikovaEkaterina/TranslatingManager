import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import translate.Language;
import translate.TranslationManager;

public class ExampleTest {

    @ParameterizedTest
    @EnumSource(Language.class)
    void translateTest(Language language) {
        TranslationManager manager = new TranslationManager(language);

        System.out.println(manager.profilePage().female());
        System.out.println(manager.registrationPage().confirmAge18AndTerms());
    }
}
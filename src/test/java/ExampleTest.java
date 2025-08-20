import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

@Execution(ExecutionMode.CONCURRENT)
public class ExampleTest {

    @ParameterizedTest
    @EnumSource(Language.class)
    void translateTest(Language language) {
        String confirm = TranslationManager.get(language, "registrationPage.confirmAge18AndTerms");
        String female = TranslationManager.get(language, "profilePage.female");
        System.out.println(confirm);
        System.out.println(female);
    }
}
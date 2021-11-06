import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class ParametrizedTests extends TestBases {

    @ParameterizedTest(name = "Check Registration Form - {0}")
    @ValueSource(strings = {"Ivan", "Anna", "Mark"})
    void checkFillFirstName(String firstname) {
        open(URL);
        $("#firstName").setValue(firstname).pressTab();
        $("#lastName").shouldBe(empty);
        Assertions.assertTrue(true);
    }

    @ParameterizedTest(name = "{1}")
    @CsvSource({
            "Anna, Petrova",
            "Ivan, Korobkov",
            "Mark, Trunin"
    })
    void checkFillLastName(String firstname, String lastname) {
        open(URL);
        $("#firstName").setValue(firstname);
        $("#lastName").setValue(lastname).pressTab();
        $("#userEmail").shouldBe(empty);
    }

    enum Gender {
        Male, Female, Other
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(value = Gender.class, names = {"Male", "Female", "Other"})
    void checkGender(Gender gender) {
        open(URL);
        $("#genterWrapper").$(byText(gender.name())).click();
        Assertions.assertTrue(true);
    }


    static Stream<Arguments> checkFillFields() {
        return Stream.of(
                Arguments.of(
                        "Anna", "Petrova", "mymail_1@gmail.com", "Female"
                ),
                Arguments.of(
                        "Ivan", "Ivanov", "sharik@gmail.com", "Male"
                ),
                Arguments.of(
                        "Luiza", "Albertovna", "foolfil@gmail.com", "Other"
                )
        );
    }

    @MethodSource("checkFillFields")
    @ParameterizedTest(name = "{0}")
    void lookFreedomFormTest(String firstname, String lastname, String email, String gender) {
        open(URL);
        $("#firstName").setValue(firstname);
        $("#lastName").setValue(lastname);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
    }
}

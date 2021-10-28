import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

    public class TestBases {
        static final String URL = "https://demoqa.com/automation-practice-form";

        @BeforeAll
        static void setup() {
            Configuration.browser = "chrome";
            Configuration.startMaximized = true;
            Configuration.holdBrowserOpen = true;
        }
    }

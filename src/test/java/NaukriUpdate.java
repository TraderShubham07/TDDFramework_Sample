import core.BaseCommands;
import core.BaseTest;
import org.testng.annotations.Test;
import pages.NaukriPage;

public class NaukriUpdate extends BaseTest {

    NaukriPage naukriPage = NaukriPage.getInstance();

    @Test
    public void NaukriAutomation() {
        BaseCommands.getURL("https://www.naukri.com/");
        naukriPage.loginViaEmailAndPassword();
        naukriPage.updateResumeHeadlines();
    }
}

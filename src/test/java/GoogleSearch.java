import core.BaseCommands;
import core.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.GooglePages;
import utils.ListenersImplementation;

public class GoogleSearch extends BaseTest {
    GooglePages googlePages=GooglePages.getInstance();

    @Test
    public void launchGoogle(){
        BaseCommands.getURL("https://www.google.com/");
        BaseCommands.addScreenShotInReport("Test");
        googlePages.click();
        BaseCommands.addScreenShotInReport("Google Page");
    }
}
import core.BaseCommands;
import core.BaseTest;
import org.testng.annotations.Test;
import pages.AmazonPages;

public class AmazonSearch extends BaseTest {
    AmazonPages amazonPages = AmazonPages.getInstance();

    @Test
    public void amazonLaunch() throws InterruptedException {
        BaseCommands.getURL("https://www.amazon.com/");
        BaseCommands.addScreenShotInReport("Test");
        amazonPages.click();
        BaseCommands.addScreenShotInReport("Amazon Page");
        amazonPages.searchBox();
    }
}
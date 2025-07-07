package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utils.ListenersImplementation;

@Listeners(ListenersImplementation.class)
public abstract class BaseTest {
    protected static WebDriver driver;
//    private static final ReadProperty readProperty = ReadProperty.getInstance();
//    public static final String testDataExcel = readProperty.readProperties("testDataPath");

    public BaseTest() {
    }

    //Clears Screenshot directory
    //Clears Screenshot directory
    @BeforeSuite
    public void clearScreenshotDir() {
        BaseCommands.clearDir(System.getProperty("user.dir") + "/screenshots");
    }

    //Clears Downloads directory
    @BeforeSuite
    public void clearDownloadDir() {
        BaseCommands.clearDir(System.getProperty("user.dir") + "/downloads");
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // This closes all windows and ends the session
        }
    }
}

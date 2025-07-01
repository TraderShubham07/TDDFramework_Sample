package core;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static utils.ListenersImplementation.test;


public class BaseCommands extends BaseTest {
    public static WebDriverWait wait;
    public static String dateName;

    public BaseCommands() {
    }

    //This method is used To enter the URL
    public static void getURL(String inputURL) {
        try {
            driver.get(inputURL);
            test.pass("Entered application URL successfully");
            test.info("Application URL is " + inputURL);
        } catch (Exception var4) {
//            failureScenario("Failed to enter application URL ............. " + var4);
        }
    }

    //This method is used To clear the field
    public static void clear(By locator) {
        try {
            waitForClickable(locator).clear();
            test.pass("Able to clear the input field successfully");
        } catch (Exception var4) {
            failureScenario("Failed to clear the input field .......... " + var4);
        }
    }

    //This method is used to wait until an element is clickable
    public static WebElement waitForClickable(By locator) {
        WebElement element;
        while (true) {
            try {
                waitForVisibilityOfElement(locator);
                element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                test.pass("Able to wait for an element");
                break;
            } catch (StaleElementReferenceException e) {
                waitForElement(locator);
            } catch (Exception var3) {
                failureScenario("waiting for clickable element failed " + var3);
            }
        }
        return element;
    }

    //Wait until the element is visible
    public static WebElement waitForVisibilityOfElement(By locator) {
        WebElement element = null;
        try {
            waitForElement(locator);
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            element = wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfElementLocated(locator));
            test.pass("Able to wait until the element is visible");
        } catch (Exception e) {
            failureScenario("Failed to find the visible element........ " + e);
        }
        return element;
    }

    //This method is used To wait for an element until its located
    public static WebElement waitForElement(By locator) {
        WebElement element = null;
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            test.pass("Able to wait for an element");
        } catch (Exception var3) {
            failureScenario("waiting for an element failed " + var3);
        }
        return element;
    }

    //This method is used to send values and click on it
    public static void sendAndClick(By element, String value) {
        try {
            waitForClickable(element).sendKeys(value);
            driver.findElement(element).click();
            test.pass("Entered " + value + " for input field " + driver.findElement(element).getText() + " successfully");
        } catch (Exception var4) {
            failureScenario("Failed to send the value for input field failed.......... " + var4);
        }
    }

    public static void failureScenario(String failureMessage) {
//        String temp = takeScreenshot(driver);
//        test.fail(failureMessage, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
//        Assert.fail(failureMessage);
    }

    // This method is used to click on an element
    public static void click(By locator) {
        while (true) {
            try {
                waitForVisibilityOfElement(locator);
                waitForElementToBeClickable(locator);
                try {
                    driver.findElement(locator).click();
                } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
                    Actions action = new Actions(driver);
                    WebElement element = driver.findElement(locator);
                    action.moveToElement(element).click();
                }
                break;
            } catch (Exception var4) {
                failureScenario("Failed to click the Element......... " + var4);
            }
        }
    }

    public static By waitForElementToBeClickable(By locator) {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception var4) {
            failureScenario("Failed to capture the selected element as Clickable  " + var4);
        }
        return locator;
    }

    //To capture the screenshot
    public static String takeScreenshot(String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String relativePath = "screenshots/" + testName + "_" + timestamp + ".png"; // RELATIVE
        String fullPath = System.getProperty("user.dir") + "/" + relativePath;

        File destFile = new File(fullPath);
        new File(destFile.getParent()).mkdirs();  // ensure folder exists
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relativePath;  // RETURN RELATIVE PATH
    }


    //To add screenshot into the report
    public static void addScreenShotInReport(String Message) {
        String temp = BaseCommands.takeScreenshot(Message);
        test.info(Message, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
    }

    //To clear the directory
    public static void clearDir(String path) {
        try {
            Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            // Assert.fail("failed to clear the directory");
            e.printStackTrace();
        }
    }
}

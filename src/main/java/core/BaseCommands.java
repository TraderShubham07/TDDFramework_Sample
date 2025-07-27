package core;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
            driver.manage().window().maximize();
//            driver.navigate().to(inputURL);
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

    public static void clickWithAction(By element){
        Actions actions = new Actions(driver);
        actions.click(findElement(element)).perform();

    }

    public static void click(WebElement element) {
        element.click();
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

    public static String getText(By element) {
        try {
            return driver.findElement(element).getText();
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + element);
            return "";
        }
    }

    public static void moveToElement(By element) {
        try {
            waitForVisibilityOfElement(element);
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(element)).perform();
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + element);
        } catch (Exception e) {
            System.out.println("Could not move to element: " + element + " due to " + e.getMessage());
        }
    }

    public static WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public static List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public static void dropdownSelectByIndex(By locator, int index) {
        try {
            waitForVisibilityOfElement(locator);
            Select select = new Select(findElement(locator));
            select.selectByIndex(index);
        } catch (Exception e) {
            failureScenario("Failed to Select element : " + locator + " " + e);
        }
    }

//    public static void dropdownSelectByContainsVisibleText(By locator, String visibleText) {
//        try {
//            waitForVisibilityOfElement(locator);
//            Select select = new Select(findElement(locator));

    /// /            select.selectByContainsVisibleText(visibleText);
//            select.selectByValue(visibleText);
//        } catch (Exception e) {
//            failureScenario("Failed to Select element : " + locator + " " + e);
//        }
//    }

    public static void selectFromDropdown(By locator, String selectType, String value) {
        Select select = new Select(driver.findElement(locator));

        switch (selectType.toLowerCase()) {
            case "text":
                select.selectByVisibleText(value);
                break;
            case "value":
                select.selectByValue(value);
                break;
            case "index":
                select.selectByIndex(Integer.parseInt(value));
                break;
            default:
                System.out.println("Invalid selection type: " + selectType);
        }
    }

    public static String handleAlert(String alertOperation, String inputText) {
        try {
            Alert alert = driver.switchTo().alert();

            switch (alertOperation.toLowerCase()) {
                case "accept":
                    alert.accept();
                    break;
                case "dismiss":
                    alert.dismiss();
                    break;
                case "gettext":
                    return alert.getText();
                case "sendkeys":
                    alert.sendKeys(inputText);
                    alert.accept();  // ✅ Accept the alert after input
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported alert operation: " + alertOperation);
            }
        } catch (NoAlertPresentException e) {
            System.out.println("No alert is present.");
        }
        return null;
    }

    public static void switchToWindow(int index) {
        Set<String> handles = driver.getWindowHandles();
        int i = 0;
        for (String handle : handles) {
            if (i == index) {
                driver.switchTo().window(handle);
                break;
            }
            i++;
        }
    }

    // Store this once when test starts
    public static String originalWindow;

    // Call this at beginning of test
    public static void saveOriginalWindow() {
        originalWindow = driver.getWindowHandle();
    }

    // Switch back to the original window
    public static void switchToOriginalWindow() {
        if (originalWindow != null) {
            driver.switchTo().window(originalWindow);
        } else {
            throw new IllegalStateException("Original window handle was not saved.");
        }
    }

    // Close current tab and switch back to original
    public static void closeCurrentTabAndReturn() {
        driver.close(); // Closes current tab
        driver.switchTo().window(originalWindow); // Returns to original
    }

    public static void dragAndDrop(By sourceElement, By targetElement){
        WebElement source = findElement(sourceElement);
        WebElement target = findElement(targetElement);

        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).build().perform();
    }

    public static void uploadFileUsingRobotClass(String file) throws AWTException {
        Robot robot = new Robot();
        robot.delay(1000);

        StringSelection selection = new StringSelection(file);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}

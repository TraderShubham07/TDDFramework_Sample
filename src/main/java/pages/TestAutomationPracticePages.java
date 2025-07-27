package pages;

import core.BaseCommands;
import maps.TestAutomationPracticeMaps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestAutomationPracticePages extends TestAutomationPracticeMaps {
    private static TestAutomationPracticePages object;

    public static TestAutomationPracticePages getInstance() {
        if (object == null) {
            object = new TestAutomationPracticePages();
        }
        return object;
    }

    public void enterText() {
        BaseCommands.sendAndClick(inputText(), "Testing this feature");
    }

    public void downloadFile(String value) {
        BaseCommands.click(generateAndDownloadFile(value));
    }

    public void textOnUI(String value) {
        String actualText = BaseCommands.getText(generateText(value));
        Assert.assertEquals(actualText, value);
    }

    public void selectBox(String value) {
        BaseCommands.moveToElement(headerPaginationWebTable());
        BaseCommands.waitForElementToBeClickable(checkBox(value));
        BaseCommands.click(checkBox(value));
        WebElement checkbox = BaseCommands.findElement(checkBox(value));

        Assert.assertTrue(checkbox.isSelected(), "Checkbox should be selected");
    }

    public void selectDropdownFromIndex() {
        BaseCommands.moveToElement(selectAnItem());
        BaseCommands.click(selectAnItem());
        BaseCommands.waitForVisibilityOfElement(dropdownItem());
        List<WebElement> dropdownlists = BaseCommands.findElements(dropdownList());
        BaseCommands.click(dropdownlists.get(2));
    }

    public void alertAndPopup() {
        BaseCommands.moveToElement(headerAlertAndPopup());
        BaseCommands.click(promtAlert());
        BaseCommands.handleAlert("sendkeys", "Testing");
    }

    public void newTabOperations() {
        BaseCommands.moveToElement(userCancelledSection());
        BaseCommands.saveOriginalWindow();
        BaseCommands.click(newTab()); //
        BaseCommands.switchToWindow(1);
        String actualText = BaseCommands.getText(basicDataBaseValidationHeader());
        Assert.assertEquals(actualText, "1. Basic Database Validation");
        BaseCommands.closeCurrentTabAndReturn();
    }

    // Same operation for both new Tab and new Child Windows
    public void newChildOperations() {
        BaseCommands.moveToElement(userCancelledSection());
        BaseCommands.saveOriginalWindow();
        BaseCommands.click(newChildTab()); //
        BaseCommands.switchToWindow(1);
        String actualText = BaseCommands.getText(basicDataBaseValidationHeader());
        Assert.assertEquals(actualText, "1. Basic Database Validation");
        BaseCommands.closeCurrentTabAndReturn();
    }

    public void tableOperationTest() {
        BaseCommands.moveToElement(staticWebElement());
        List<String> l1 = new ArrayList<>();

        for (int i = 2; i < 8; i++) {
            for (int j = 1; j < 5; j++) {
                String str = "";
                str = BaseCommands.findElement(tableOperation(i, j)).getText();
                l1.add(str);
            }
        }
//        System.out.println(l1);
    }

    public void selectDropdown() throws InterruptedException {
        BaseCommands.moveToElement(countryDropdown());
        BaseCommands.click(countryDropdown());

//        BaseCommands.dropdownSelectByIndex(countryDropdown(),9);
//        BaseCommands.dropdownSelectByContainsVisibleText(countryDropdown(),"India");
        BaseCommands.selectFromDropdown(countryDropdown(),"index","9");
        Thread.sleep(10000);
    }

    public void dragAndDrop(){
        BaseCommands.dragAndDrop(source(),target());
    }

    public void brokenLinks(){
        List<WebElement> links = BaseCommands.findElements(By.tagName("a"));

        System.out.println("Total links found: " + links.size());

        for (WebElement link : links) {
            String url = link.getAttribute("href");

            if (url == null || url.isEmpty()) {
                System.out.println("⚠️ URL is either empty or not configured for anchor tag.");
                continue;
            }

            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("HEAD");
                connection.connect();
                int statusCode = connection.getResponseCode();

                if (statusCode >= 400) {
                    System.out.println("❌ Broken Link: " + url + " - Status Code: " + statusCode);
                } else {
                    System.out.println("✅ Valid Link: " + url + " - Status Code: " + statusCode);
                }

            } catch (Exception e) {
                System.out.println("🚫 Error with URL: " + url + " - " + e.getMessage());
            }
        }
    }

    public void uploadFile(){
        BaseCommands.moveToElement(singleFileUpload());
        BaseCommands.sendAndClick(singleFileUpload(),"D:\\My Framework\\TDD_Framework\\TDDFramework\\numbers.txt");
        BaseCommands.click(uploadSingleFileButton());
        boolean isVisible = BaseCommands.findElement(uploadSingleFileParagraphText()).isDisplayed();
        Assert.assertTrue(isVisible);
    }

    public void uploadFileWithRobot() throws AWTException {
        BaseCommands.moveToElement(singleFileUpload());
        BaseCommands.waitForElementToBeClickable(singleFileUpload());
        BaseCommands.clickWithAction(singleFileUpload());
        String file ="D:\\My Framework\\TDD_Framework\\TDDFramework\\numbers.txt";

        BaseCommands.uploadFileUsingRobotClass(file);
        BaseCommands.click(uploadSingleFileButton());

        boolean isVisible = BaseCommands.findElement(uploadSingleFileParagraphText()).isDisplayed();
        Assert.assertTrue(isVisible);
    }

    public void calenderDateOpr(){
        BaseCommands.moveToElement(startDate());
        BaseCommands.sendAndClick(startDate(),"10-07-2024");
    }
}
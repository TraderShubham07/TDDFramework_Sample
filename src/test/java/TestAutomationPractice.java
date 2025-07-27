import core.BaseCommands;
import core.BaseTest;
import org.testng.annotations.Test;
import pages.TestAutomationPracticePages;

import java.awt.*;

public class TestAutomationPractice extends BaseTest {

    TestAutomationPracticePages testAutoPages = TestAutomationPracticePages.getInstance();

    @Test
    public void testTextForText() throws InterruptedException {
        BaseCommands.getURL("https://testautomationpractice.blogspot.com/p/download-files_25.html");
        testAutoPages.enterText();
        testAutoPages.downloadFile("Generate and Download Text File");
        testAutoPages.textOnUI("Download Text File");
        Thread.sleep(10000);
    }

    @Test
    public void testTextForPDF() {
        BaseCommands.getURL("https://testautomationpractice.blogspot.com/p/download-files_25.html");
        testAutoPages.enterText();
        testAutoPages.downloadFile("Generate and Download PDF File");
        testAutoPages.textOnUI("Download PDF File");

    }

    @Test
    public void testCheckBox() throws InterruptedException {
        BaseCommands.getURL("https://testautomationpractice.blogspot.com/p/download-files_25.html");
        testAutoPages.enterText();
        testAutoPages.downloadFile("Generate and Download PDF File");
        testAutoPages.textOnUI("Download PDF File");
        testAutoPages.selectBox("Smartphone");
        testAutoPages.selectBox("Tablet");
        Thread.sleep(1000);
    }

    @Test
    public void testDropdownWithoutSelectTag() throws InterruptedException {
        BaseCommands.getURL("https://testautomationpractice.blogspot.com/p/download-files_25.html");
        testAutoPages.selectDropdownFromIndex();
        Thread.sleep(10000);
    }

    @Test
    public void testAlertAndPopup() throws InterruptedException {
        BaseCommands.getURL("https://testautomationpractice.blogspot.com/p/download-files_25.html");
        testAutoPages.alertAndPopup();
        Thread.sleep(10000);
    }

    @Test
    public void testNewTab() throws InterruptedException {
        BaseCommands.getURL("https://testautomationpractice.blogspot.com/p/download-files_25.html");
        testAutoPages.newTabOperations();
        Thread.sleep(10000);
    }

    @Test
    public void testChildWindow() throws InterruptedException {
        BaseCommands.getURL("https://testautomationpractice.blogspot.com/p/download-files_25.html");
        testAutoPages.newTabOperations();
        Thread.sleep(10000);
    }

    @Test
    public void tableDataCheck() {
        BaseCommands.getURL("https://testautomationpractice.blogspot.com/p/download-files_25.html");
        testAutoPages.tableOperationTest();
    }

    @Test
    public void testDropdownWithSelectTag() throws InterruptedException {
        BaseCommands.getURL("https://testautomationpractice.blogspot.com");
        testAutoPages.selectDropdown();
        Thread.sleep(10000);
    }

    @Test
    public void testDragAndDrop() throws InterruptedException {
        BaseCommands.getURL("https://testautomationpractice.blogspot.com");
        Thread.sleep(5000);
        testAutoPages.dragAndDrop();
        Thread.sleep(10000);
    }

    @Test
    public void testBrokenLinks() throws InterruptedException {
        BaseCommands.getURL("https://testautomationpractice.blogspot.com");
        testAutoPages.brokenLinks();
        Thread.sleep(10000);
    }

    @Test
    public void testUploadFile() throws InterruptedException {
        BaseCommands.getURL("https://testautomationpractice.blogspot.com");
       testAutoPages.uploadFile();
        Thread.sleep(10000);
    }

    @Test
    public void testUploadFileUsingRobot() throws InterruptedException, AWTException {
        BaseCommands.getURL("https://testautomationpractice.blogspot.com");
        testAutoPages.uploadFileWithRobot();
        Thread.sleep(10000);
    }

    @Test
    public void testCalenderOperation() throws InterruptedException, AWTException {
        BaseCommands.getURL("https://testautomationpractice.blogspot.com");
        testAutoPages.calenderDateOpr();
        Thread.sleep(10000);
    }
}
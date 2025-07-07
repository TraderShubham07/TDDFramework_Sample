package pages;

import core.BaseCommands;
import maps.NaukriMaps;
import org.testng.Assert;
import org.testng.asserts.Assertion;

public class NaukriPage extends NaukriMaps {
    private static NaukriPage object;

    private String actualResumeHeadlinesContent;

    public static NaukriPage getInstance() {
        if (object == null) {
            object = new NaukriPage();
        }
        return object;
    }

    public void loginViaEmailAndPassword() {
        BaseCommands.click(login());
        BaseCommands.waitForElement(loginHeader());
        BaseCommands.sendAndClick(textBasedInputField("Email ID"), "Imshubham224@gmail.com");
        BaseCommands.sendAndClick(textBasedInputField("password"), "Naukri@009@");
        BaseCommands.click(loginBtn());
    }

    public void updateResumeHeadlines() {
        BaseCommands.waitForElement(viewProfileHeader());
        BaseCommands.click(viewProfileHeader());
        BaseCommands.waitForElementToBeClickable(resumeHeadlinesIcon());
        BaseCommands.click(resumeHeadlinesIcon());
        BaseCommands.click(resumeHeadlinesIcon());
        actualResumeHeadlinesContent = BaseCommands.getText(resumeHeadlineContentField());
        System.out.println("Actual is : " + actualResumeHeadlinesContent);
        resumeHeadlinesContent();
        BaseCommands.click(saveBtn());
        BaseCommands.waitForElement(successMessage());
        String actualSuccessMessage = BaseCommands.getText(successMessage());
        Assert.assertEquals(actualSuccessMessage,"Resume Headline has been successfully saved.");
    }

    public void resumeHeadlinesContent() {
        String expectedResumeHeadlineContent = "Experienced SDET Engineer with 2.11 years in test automation, API testing, and framework development. Skilled in Java, Selenium, Appium, CI/CD tools (Jenkins, Git), AWS, and Agile. Proven track record in driving software quality through automation";
        if (actualResumeHeadlinesContent.equals(expectedResumeHeadlineContent)) {
            BaseCommands.clear(resumeHeadlineContentField());
            BaseCommands.sendAndClick(resumeHeadlineContentField(), expectedResumeHeadlineContent + ".");
        } else {
            BaseCommands.clear(resumeHeadlineContentField());
            BaseCommands.sendAndClick(resumeHeadlineContentField(), expectedResumeHeadlineContent);
        }
    }


}

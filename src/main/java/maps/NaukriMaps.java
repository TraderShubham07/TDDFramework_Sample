package maps;

import org.openqa.selenium.By;

public class NaukriMaps {

    public By login() {
        return By.xpath("//a[text()='Login']");
    }

    public By textBasedInputField(String value){
        return By.xpath("//input[contains(@placeholder,'"+value+"')]");
    }

    public By loginBtn(){
        return By.xpath("//button[text()='Login']");
    }

    public By loginHeader(){
        return By.xpath("//div[text()='Login']");
    }

    public By viewProfileHeader(){
        return By.xpath("//a[normalize-space(.)='View profile']");
//        return By.xpath("//a[contains(., 'View') and contains(., 'profile')]");
    }

    public By resumeHeadlinesIcon(){
        return By.xpath("//span[text()='Resume headline']/following-sibling::span");
    }

    public By resumeHeadlineContentField(){
        return By.id("resumeHeadlineTxt");
    }

    public By saveBtn(){
        return By.xpath("//button[text()='Save']");
    }

    public By successMessage(){
        return By.xpath("//p[contains(text(),'Resume Headline has been successfully saved.')]");
    }
}

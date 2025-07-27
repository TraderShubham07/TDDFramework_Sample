package maps;

import org.openqa.selenium.By;

public class TestAutomationPracticeMaps {

    public By inputText() {
        return By.xpath("//textarea[@id='inputText']");
    }

    public By generateAndDownloadFile(String value) {
        return By.xpath("//button[text()='" + value + "']");
    }

    public By generateText(String value) {
        return By.xpath("//a[text()='" + value + "']");
    }

    public By headerPaginationWebTable() {
        return By.xpath("//h2[text()='Pagination Web Table']");
    }

    public By checkBox(String value) {
        return By.xpath("//td[contains(text(),'" + value + "')]/following-sibling::td//input[@type='checkbox']");
    }

    public By selectAnItem() {
        return By.xpath("//input[@placeholder='Select an item']");
    }

    public By dropdownItem() {
        return By.xpath("//div[@id='dropdown']");
    }

    public By dropdownList() {
        return By.xpath("//div[@id='dropdown']//div");
    }

    public By headerAlertAndPopup() {
        return By.xpath("//h2[text()='Alerts & Popups']");
    }

    public By promtAlert() {
        return By.xpath("//button[text()='Prompt Alert']");
    }

    public By userCancelledSection() {
        return By.xpath("//p[text()='User cancelled the prompt.']");
    }

    public By newTab() {
        return By.xpath("//button[text()='New Tab']");
    }

    public By newChildTab() {
        return By.xpath("//button[text()='Popup Windows']");
    }

    public By basicDataBaseValidationHeader() {
        return By.xpath("//span[contains(text(),'Basic Database Validation')]");
    }

    public By tableOperation(int i, int j) {
        return By.xpath("//table[@name='BookTable']/tbody/tr[" + i + "]/td[" + j + "]");
    }

    public By staticWebElement(){
        return By.xpath("//h2[text()='Static Web Table']");
    }

    public By countryDropdown(){
        return By.xpath("//select[@id='country']");
    }

    public By source(){
        return By.xpath("//p[text()='Drag me to my target']");
    }

    public By target(){
        return By.xpath("//p[text()='Drop here']");
    }

    public By singleFileUpload(){
        return By.xpath("//input[@id='singleFileInput']");
    }

    public By uploadSingleFileButton(){
        return By.xpath("//button[text()='Upload Single File']");
    }

    public By uploadSingleFileParagraphText(){
        return By.xpath("//p[@id='singleFileStatus']");
    }

    public By startDate(){
        return By.xpath("//input[@id='start-date']");
    }
}

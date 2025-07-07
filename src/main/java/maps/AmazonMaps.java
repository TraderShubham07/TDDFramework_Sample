package maps;

import org.openqa.selenium.By;

public class AmazonMaps {

    public By amazonLogo() {
        return By.xpath("//button[text()='Continue shopping']");
    }

    public By searchBox1(){
        return By.xpath("//input[@id='twotabsearchtextbox']");
    }

    public By searchBtn(){
        return By.xpath("//input[@id='nav-search-submit-button']");
    }

    public By orderClick(){
        return By.xpath("((//div[@class='a-section a-spacing-base'])[1]//following-sibling::div)[5]");
    }
}

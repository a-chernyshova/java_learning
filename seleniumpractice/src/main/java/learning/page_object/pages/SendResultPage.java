package learning.page_object.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SendResultPage {
    WebDriver driver;
    public static final By RESULT_NOTIFICATION_LOCATOR = By.cssSelector(".mail-Done-Title");

    public SendResultPage(WebDriver driver){
        this.driver = driver;
    }
    public String getStatus(){
        return driver.findElement(RESULT_NOTIFICATION_LOCATOR).getText();
    }
}

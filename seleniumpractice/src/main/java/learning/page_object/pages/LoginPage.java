package learning.page_object.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    public static final By LOGIN_INPUT_LOCATOR = By.cssSelector(".desk-notif input");
    public static final By SUBMIT_LOGIN_FORM_LOCATOR = new By.ByClassName(".auth__button");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void fillLoginCredentials(String[] logpass){
        driver.findElement(LOGIN_INPUT_LOCATOR).sendKeys(logpass[0] + Keys.TAB + logpass[1] + Keys.ENTER);
    }
}


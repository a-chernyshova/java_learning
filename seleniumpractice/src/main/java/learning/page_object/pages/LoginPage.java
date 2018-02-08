package learning.page_object.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class LoginPage extends AbstractPage{
    public static final By LOGIN_INPUT_LOCATOR = By.cssSelector(".desk-notif input");
    public static final By SUBMIT_LOGIN_FORM_LOCATOR = new By.ByClassName(".auth__button");

    public void fillLoginCredentials(String log, String pass){
        driver.findElement(LOGIN_INPUT_LOCATOR).sendKeys(log + Keys.TAB + pass + Keys.ENTER);
    }

    public String returnTitle(){
        return driver.getTitle();
    }
}
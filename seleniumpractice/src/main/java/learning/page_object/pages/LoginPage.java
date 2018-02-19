package learning.page_object.pages;

import learning.page_object.pages.buisness_object.User;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class LoginPage extends AbstractPage{
    public static final By LOGIN_INPUT_LOCATOR = By.cssSelector(".desk-notif input");
    public static final By SUBMIT_LOGIN_FORM_LOCATOR = new By.ByClassName(".auth__button");

    public void fillLoginCredentials(User user){
        driver.findElement(LOGIN_INPUT_LOCATOR).sendKeys(user.getLogin() + Keys.TAB + user.getPassword() + Keys.ENTER);
    }

    public void fillIncorrectLoginCredentials(User user){
        driver.findElement(LOGIN_INPUT_LOCATOR).sendKeys(user.getLogin() + Keys.TAB + (user.getPassword() + "123") + Keys.ENTER);
    }

    public String returnTitle(){
        return driver.getTitle();
    }
}
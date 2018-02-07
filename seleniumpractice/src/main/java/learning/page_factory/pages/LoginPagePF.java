package learning.page_factory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPagePF extends AbstractPagePF{
    @FindBy(css = ".desk-notif input")
    public WebElement loginInputs;

    @FindBy(className = ".auth__button")
    public WebElement submitLoginFormButton;

    public LoginPagePF(WebDriver driver){
        super(driver);
    }
    public LoginPagePF fillLoginCredentials(String log, String pass){
        loginInputs.sendKeys(log + Keys.TAB + pass + Keys.ENTER);
        return this;
    }
}
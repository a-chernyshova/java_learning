package learning.page_factory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SendResultPagePF extends AbstractPagePF{
    @FindBy(css = ".mail-Done-Title")
    public WebElement resultNotification;

    public SendResultPagePF(WebDriver driver){
        super(driver);
    }
    public String getStatus(){
        return resultNotification.getText();
    }
}

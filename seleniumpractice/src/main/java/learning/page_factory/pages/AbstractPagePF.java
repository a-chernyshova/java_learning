package learning.page_factory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPagePF {
    public static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 10;
    protected WebDriver driver;

    protected AbstractPagePF(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    protected void waitForElementVisible(WebElement element){
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).
                until(ExpectedConditions.visibilityOf(element));
    }
    protected void waitForElementEnabled(WebElement element){
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).
                until(ExpectedConditions.elementToBeClickable(element));
    }
}

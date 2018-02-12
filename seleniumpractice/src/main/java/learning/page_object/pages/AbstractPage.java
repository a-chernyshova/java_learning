package learning.page_object.pages;

import learning.page_object.utils.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
    public static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 10;
    protected WebDriver driver;

    protected AbstractPage(){
        this.driver = WebDriverSingleton.getWebdriverInstance();
    }
    protected void waitForElementPresent(By locator){
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).
                until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
    protected void waitForElementVisible(By locator){
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).
                until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    protected void waitForElementEnabled(By locator){
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).
                until(ExpectedConditions.elementToBeClickable(locator));
    }
    protected void waitForElementStateLess(By locator){
        WebElement elem = driver.findElement(locator);
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.stalenessOf(elem));
    }
}

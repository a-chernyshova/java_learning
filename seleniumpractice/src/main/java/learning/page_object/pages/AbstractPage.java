package learning.page_object.pages;

import learning.page_object.utils.Logger;
import learning.page_object.utils.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    protected void highlightElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid green'", driver.findElement(locator));
    }

    protected void unHighlightElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", driver.findElement(locator));
    }

    protected void sleep(){
        ((JavascriptExecutor)driver).executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");
        Logger.info("Sleep timer is in progress...");
    }
}

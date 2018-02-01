package learning;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.File;
import java.security.Key;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AppTest {
    WebDriver chromeDriver = new ChromeDriver();

    //@BeforeClass
    //public void runDriver(){ WebDriver chromeDriver = new ChromeDriver();}ChromeDriver
    //WebDriver firefoxDriver = new FirefoxDriver();

    @Test(enabled = false)
    public void loginFailTest(){
        chromeDriver.get("https://www.yandex.ru/");
        WebElement loginDiv = chromeDriver.findElement(By.className("desk-notif"));
        loginDiv.findElement(By.tagName("input")).sendKeys("nassy.kusaka" + Keys.TAB +"awesome" + Keys.ENTER);
        String winTitle = chromeDriver.getTitle();
        chromeDriver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        //Boolean errorMessage = chromeDriver.findElement(By.ByClassName("")).isDisplayed();
        Assert.assertEquals(winTitle, "Авторизация");
    }

    @Test //(dependsOnMethods = {"loginFailTest"})
    public void loginTest(){
        chromeDriver.get("https://www.yandex.ru/");
        WebElement loginDiv = chromeDriver.findElement(By.className("desk-notif"));
        loginDiv.findElement(By.tagName("input")).sendKeys("nassy.kusaka" + Keys.TAB +"awesome1" + Keys.ENTER);
        String winTitle = chromeDriver.getTitle();
        chromeDriver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        Assert.assertEquals(winTitle, "Yandex.Mail");
    }

    @Test(dependsOnMethods = {"loginTest"})
    public void createDraftTest(){
        chromeDriver.findElement(By.className("mail-ComposeButton ")).click();

        WebDriverWait wait = new WebDriverWait(chromeDriver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("mail-Compose-Field-Input")));

        WebElement currentElement = chromeDriver.switchTo().activeElement();
        currentElement.sendKeys("anastasiia_chernyshova@epam.com" + Keys.TAB + "Selenium webdriver" +
                Keys.TAB + "The primary new feature in Selenium 2.0 is the integration of the WebDriver API. WebDriver is designed to provide a simpler, more concise programming interface in addition to addressing some limitations in the Selenium-RC API. Selenium-WebDriver was developed to better support dynamic web pages where elements of a page may change without the page itself being reloaded. WebDriver’s goal is to supply a well-designed object-oriented API that provides improved support for modern advanced web-app testing problems.");

        chromeDriver.findElement(By.cssSelector(".ns-view-folders a:last-child")).click();
        chromeDriver.findElement(By.className("_nb-modal-popup ")).findElement(By.tagName("button")).click();
        WebElement lastSaved = chromeDriver.findElement(By.cssSelector(".ns-view-messages-wrap .ns-view-messages-item-wrap"));
        String title = lastSaved.findElement(By.className("mail-MessageSnippet-Item_subject")).findElement(By.tagName("span")).getAttribute("title");
        String email = lastSaved.findElement(By.className("mail-MessageSnippet-FromText")).getAttribute("title");
        Assert.assertEquals(title, "Selenium webdriver");
        Assert.assertEquals(email, "anastasiia_chernyshova@epam.com");
    }

    @Test(dependsOnMethods = {"createDraftTest"})
    public void sendDraftTest(){
        chromeDriver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        WebElement lastSaved = chromeDriver.findElement(By.cssSelector(".ns-view-messages-wrap .ns-view-messages-item-wrap"));
        lastSaved.findElement(By.className("js-message-snippet-sender")).click();
        chromeDriver.findElement(By.className("ns-view-compose-action-buttons")).click();
        //chromeDriver.findElement(By.className("mail-Done-Title")).getAttribute("");
    }

    @Test(dependsOnMethods = {"sendDraftTest"})
    public void logoutTest(){
        chromeDriver.findElement(By.className("mail-User-Name")).click();
        chromeDriver.findElement(By.linkText("Log out")).click();
        Assert.assertEquals(chromeDriver.getTitle(), "Яндекс");
    }

    @AfterClass
    public void closure(){
        chromeDriver.quit();
    }
}

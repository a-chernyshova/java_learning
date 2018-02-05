package learning;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

import java.io.File;
import java.security.Key;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AppTest {

    private static final String LOG = "correct_login";
    private static final String PASS = "correct_pass";
    private static final String ADDRESS = "address@gmail.com";
    private static final String SUBJECT = "Selenium webdriver";
    private static final String TEXT = "\"The primary new feature in Selenium 2.0 is the integration of the WebDriver API. " +
            "WebDriver is designed to provide a simpler, more concise programming interface in addition to addressing " +
            "some limitations in the Selenium-RC API. /nSelenium-WebDriver was developed to better support dynamic " +
            "web pages where elements of a page may change without the page itself being reloaded. /nWebDriver’s " +
            "goal is to supply a well-designed object-oriented API that provides improved support for modern " +
            "advanced web-app testing problems.\"";
    private WebDriver chromeDriver;
    private WebDriverWait wait;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        chromeDriver = new ChromeDriver();
        wait = new WebDriverWait(chromeDriver, 5);
        //WebDriver firefoxDriver = new FirefoxDriver();
    }

    private String authorization(String LOG, String PASS){
        chromeDriver.get("https://www.yandex.ru/");
        WebElement loginDiv = chromeDriver.findElement(By.className("desk-notif"));
        loginDiv.findElement(By.tagName("input")).sendKeys(LOG + Keys.TAB + PASS + Keys.ENTER);
        String winTitle = chromeDriver.getTitle();
        return winTitle;
    }

    @Test(enabled = true)
    public void loginFailTest(){
        String winTitle = authorization(LOG, (PASS + "123")); //incorrect pass
        Assert.assertEquals(winTitle, "Авторизация");
    }

    @Test(dependsOnMethods = {"loginFailTest"})
    public void loginTest(){
        String winTitle = authorization(LOG, PASS);
        Assert.assertEquals(winTitle, "Yandex.Mail");
    }

    public String findMailSubject(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".mail-MessageSnippet-Content")));
        WebElement lastMail = chromeDriver.findElement(By.cssSelector(".mail-MessageSnippet-Content"));
        String mailSubject = lastMail.findElement(By.className("mail-MessageSnippet-Item_subject")).
                findElement(By.tagName("span")).getAttribute("title");
        return mailSubject;
    }

    public String findMailAddress(){
        WebElement lastMail = chromeDriver.findElement(By.cssSelector(".mail-MessageSnippet-Content"));
        String email = lastMail.findElement(By.className("mail-MessageSnippet-FromText")).getAttribute("title");
        return email;
    }

    @Test(dependsOnMethods = {"loginTest"})
    public void createDraftTest(){
        wait.until(ExpectedConditions.elementToBeClickable(By.className("mail-ComposeButton ")));
        chromeDriver.findElement(By.className("mail-ComposeButton ")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.className("mail-Compose-Field-Input")));

        //fill out mail form: address, subject, message body
        WebElement currentElement = chromeDriver.switchTo().activeElement();
        currentElement.sendKeys(ADDRESS + Keys.TAB + SUBJECT + Keys.TAB + TEXT);

        //save draft
        chromeDriver.findElement(By.cssSelector(".ns-view-folders a:last-child")).click();
        chromeDriver.findElement(By.className("_nb-modal-popup ")).findElement(By.tagName("button")).click();

        Assert.assertEquals(findMailSubject(), SUBJECT);
        Assert.assertEquals(findMailAddress(), ADDRESS);
    }

    @Test(dependsOnMethods = {"createDraftTest"})
    public void sendDraftTest(){
        chromeDriver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        WebElement lastSaved = chromeDriver.findElement(By.cssSelector(".ns-view-messages-wrap .ns-view-messages-item-wrap"));
        lastSaved.findElement(By.className("js-message-snippet-sender")).click();
        chromeDriver.findElement(By.className("ns-view-compose-action-buttons")).click();
        String success = chromeDriver.findElement(By.className("mail-Done-Title")).getText();
        Assert.assertEquals(success, "Message sent successfully.");
    }

    @Test(dependsOnMethods = {"sendDraftTest"})
    public void checkDraftTest(){
        chromeDriver.findElement(By.cssSelector(".ns-view-folders a:last-child")).click();
        try{
            chromeDriver.findElements(By.className("ns-view-messages-empty"));
        }catch (NoSuchElementException e){
            Assert.assertTrue(false);
        };
    }

    @Test(dependsOnMethods = {"sendDraftTest"})
    public void checkSentTest(){
        chromeDriver.findElement(By.linkText("Sent")).click();
        WebElement lastSaved = chromeDriver.findElement(By.cssSelector(".ns-view-messages-wrap .ns-view-messages-item-wrap"));
        String title = lastSaved.findElement(By.className("mail-MessageSnippet-Item_subject")).findElement(By.tagName("span")).getAttribute("title");
        String email = lastSaved.findElement(By.className("mail-MessageSnippet-FromText")).getAttribute("title");
        Assert.assertEquals(title, SUBJECT);
        Assert.assertEquals(email, ADDRESS);
    }

    @Test(dependsOnMethods = {"checkSentTest"})
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

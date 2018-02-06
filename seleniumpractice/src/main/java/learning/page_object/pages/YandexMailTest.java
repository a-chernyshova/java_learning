package learning.page_object.pages;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import java.util.concurrent.TimeUnit;

public class YandexMailTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String[] LOG_PASS = {"address", "password"};
    private static final String[] INCORRECT_LOG_PASS = {"address", "incorrect"};
    private static final String[] EMAIL_DATA = {"test@gmail.com", "Selenium webdriver",
            "The primary new feature in Selenium 2.0 is the integration of the WebDriver API. " +
            "WebDriver is designed to provide a simpler, more concise programming interface in addition to addressing " +
            "some limitations in the Selenium-RC API. \nSelenium-WebDriver was developed to better support dynamic " +
            "web pages where elements of a page may change without the page itself being reloaded. \nWebDriver’s " +
            "goal is to supply a well-designed object-oriented API that provides improved support for modern " +
            "advanced web-app testing problems."};

    @BeforeClass(description="Start browser")
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    private String authorization(String[] LOG_PASS){
        HomePage homePage = new HomePage(driver);
        homePage.open();

        LoginPage logForm = new LoginPage(driver);
        logForm.fillLoginCredentials(LOG_PASS);
        String winTitle = driver.getTitle();
        return winTitle;
    }

    @Test(enabled = true)
    public void loginFailTest(){
        String winTitle = authorization(INCORRECT_LOG_PASS); //incorrect pass
        Assert.assertEquals(winTitle, "Авторизация");
    }

    @Test(dependsOnMethods = {"loginFailTest"})
    public void loginTest(){
        String winTitle = authorization(LOG_PASS);
        Assert.assertEquals(winTitle, "Yandex.Mail");
    }

    public String findMailSubject(){
        LettersContainer mailList = new LettersContainer(driver);
        wait.until(ExpectedConditions.elementToBeClickable(mailList.getLastMessageLocator()));
        String mailSubject = mailList.getLastMessageSubject();
        return mailSubject;
    }

    public String findMailAddress(){
        LettersContainer mailList = new LettersContainer(driver);
        String addressee = mailList.getLastMessageAddress();
        return addressee;
    }

    @Test(dependsOnMethods = {"loginTest"})
    public void createDraftTest(){
        Menu mailBoxMenu = new Menu(driver);
        wait.until(elementToBeClickable(mailBoxMenu.getCreateButtonLocator()));
        NewLetter newLetterForm = mailBoxMenu.openNewMailForm();
        wait.until(elementToBeClickable(newLetterForm.getInputLocator()));
        newLetterForm.fillNewLetterForm(EMAIL_DATA);
        mailBoxMenu.openDraftsFolder();
        newLetterForm.saveDraft();

        Assert.assertEquals(findMailSubject(), EMAIL_DATA[1]);
        Assert.assertEquals(findMailAddress(), EMAIL_DATA[0]);
    }

    @Test(dependsOnMethods = {"createDraftTest"})
    public void sendDraftTest(){
        LettersContainer drafts = new LettersContainer(driver);
        NewLetter currentLetter = drafts.openLastMessage();
        SendResultPage result = currentLetter.sendLetter();
        Assert.assertEquals(result.getStatus(), "Message sent successfully.");
    }

    @Test(dependsOnMethods = {"sendDraftTest"})
    public void checkDraftTest(){
        Menu mailBoxMenu = new Menu(driver);
        LettersContainer lettersList = mailBoxMenu.openDraftsFolder();
        try{
            lettersList.getIsEmptyListLocator();
        }catch (NoSuchElementException e){
            Assert.assertTrue(false);
        };
    }

    @Test(dependsOnMethods = {"sendDraftTest"})
    public void checkSentTest(){
        Menu mailBoxMenu = new Menu(driver);
        LettersContainer mailList = mailBoxMenu.openSentFolder();
        String title = mailList.getLastMessageSubject();
        String email = mailList.getLastMessageAddress();

        Assert.assertEquals(title, EMAIL_DATA[1]);
        Assert.assertEquals(email, EMAIL_DATA[0]);
    }

    @Test(description = "clean sent folder", dependsOnMethods = {"checkSentTest"})
    public void cleanSentFolder(){
        LettersContainer mailList = new LettersContainer(driver);
        mailList.chooseAllMessage();
        mailList.pushDelete();
        try{
            mailList.getIsEmptyListLocator();
        }catch (NoSuchElementException e){
            Assert.assertTrue(false);
        };
    }

    @Test(dependsOnMethods = {"cleanSentFolder"})
    public void logoutTest(){
        Menu mailBoxMenu = new Menu(driver);
        mailBoxMenu.openAccountMenu();
        mailBoxMenu.logoutSubmit();
        Assert.assertEquals(driver.getTitle(), "Яндекс");
    }

    @AfterClass(description = "close browser")
    public void closure(){
        driver.quit();
    }
}

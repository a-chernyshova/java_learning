package learning.page_factory.pages;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import java.util.concurrent.TimeUnit;

public class YandexMailTestPF {
    private WebDriver driver;
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
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    private String authorization(String log, String pass){
        HomePagePF homePage = new HomePagePF(driver);
        homePage.open();

        LoginPagePF logForm = new LoginPagePF(driver);
        logForm.fillLoginCredentials(log, pass);
        return driver.getTitle();
    }

    @DataProvider(name="loginCredentials")
    public Object[][] loginCredentials(){
        return new Object[][]{
                {"username", "password"}
        };
    }

    @Test(enabled = true, dataProvider = "loginCredentials",description="check logging in with incorrect credentials")
    public void loginFailTest(String log, String pass){
        String winTitle = authorization(log, (pass + "123")); //incorrect pass
        Assert.assertEquals(winTitle, "Авторизация");
    }

    @Test(dependsOnMethods = {"loginFailTest"}, dataProvider = "loginCredentials")
    public void loginTest(String log, String pass){
        String winTitle = authorization(log, pass);
        Assert.assertEquals(winTitle, "Yandex.Mail");
    }

    public String findMailSubject(){
        LettersContainerPF mailList = new LettersContainerPF(driver);
        String mailSubject = mailList.getLastMessageSubject();
        return mailSubject;
    }

    public String findMailAddress(){
        LettersContainerPF mailList = new LettersContainerPF(driver);
        String addressee = mailList.getLastMessageAddress();
        return addressee;
    }

    @Test(dependsOnMethods = {"loginTest"})
    public void createDraftTest(){
        MenuPF mailBoxMenu = new MenuPF(driver);
        NewLetterPF newLetterForm = mailBoxMenu.openNewMailForm();
        newLetterForm.fillNewLetterForm(EMAIL_DATA);
        mailBoxMenu.openDraftsFolder();
        newLetterForm.saveDraft();

        Assert.assertEquals(findMailSubject(), EMAIL_DATA[1]);
        Assert.assertEquals(findMailAddress(), EMAIL_DATA[0]);
    }

    @Test(dependsOnMethods = {"createDraftTest"})
    public void sendDraftTest(){
        LettersContainerPF drafts = new LettersContainerPF(driver);
        NewLetterPF currentLetter = drafts.openLastMessage();
        SendResultPagePF result = currentLetter.sendLetter();
        Assert.assertEquals(result.getStatus(), "Message sent successfully.");
    }

    @Test(dependsOnMethods = {"sendDraftTest"})
    public void checkDraftTest(){
        MenuPF mailBoxMenu = new MenuPF(driver);
        LettersContainerPF lettersList = mailBoxMenu.openDraftsFolder();
        try{
            lettersList.getIsEmptyListLocator();
        }catch (NoSuchElementException e){
            Assert.assertTrue(false);
        };
    }

    @Test(dependsOnMethods = {"sendDraftTest"})
    public void checkSentTest(){
        MenuPF mailBoxMenu = new MenuPF(driver);
        LettersContainerPF mailList = mailBoxMenu.openSentFolder();
        String title = mailList.getLastMessageSubject();
        String email = mailList.getLastMessageAddress();

        Assert.assertEquals(title, EMAIL_DATA[1]);
        Assert.assertEquals(email, EMAIL_DATA[0]);
    }

    @Test(description = "clean sent folder", dependsOnMethods = {"checkSentTest"})
    public void cleanSentFolder(){
        LettersContainerPF mailList = new LettersContainerPF(driver);
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
        MenuPF mailBoxMenu = new MenuPF(driver);
        mailBoxMenu.openAccountMenu();
        mailBoxMenu.logoutSubmit();
        Assert.assertEquals(driver.getTitle(), "Яндекс");
    }

    @AfterClass(description = "close browser")
    public void closure(){
        driver.quit();
    }
}

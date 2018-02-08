package learning.page_object.pages;

import learning.page_object.utils.WebDriverSingleton;
import org.openqa.selenium.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import java.util.concurrent.TimeUnit;

public class YandexMailTest {
    private static final String[] EMAIL_DATA = {"test@gmail.com", "Selenium webdriver",
            "The primary new feature in Selenium 2.0 is the integration of the WebDriver API. " +
            "WebDriver is designed to provide a simpler, more concise programming interface in addition to addressing " +
            "some limitations in the Selenium-RC API. \nSelenium-WebDriver was developed to better support dynamic " +
            "web pages where elements of a page may change without the page itself being reloaded. \nWebDriver’s " +
            "goal is to supply a well-designed object-oriented API that provides improved support for modern " +
            "advanced web-app testing problems."};

    private String authorization(String log, String pass){
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage logForm = new LoginPage();
        logForm.fillLoginCredentials(log, pass);
        return logForm.returnTitle();
    }

    @DataProvider(name="loginCredentials")
    public Object[][] loginCredentials(){
        return new Object[][]{
                {"email-address", "password"}
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
        LettersContainer mailList = new LettersContainer();
        String mailSubject = mailList.getLastMessageSubject();
        return mailSubject;
    }

    public String findMailAddress(){
        LettersContainer mailList = new LettersContainer();
        String addressee = mailList.getLastMessageAddress();
        return addressee;
    }

    @Test(dependsOnMethods = {"loginTest"})
    public void createDraftTest(){
        Menu mailBoxMenu = new Menu();
        learning.page_object.pages.NewLetter newLetterForm = mailBoxMenu.openNewMailForm();
        newLetterForm.fillNewLetterForm(EMAIL_DATA);
        mailBoxMenu.openDraftsFolder();
        newLetterForm.saveDraft();

        Assert.assertEquals(findMailSubject(), EMAIL_DATA[1]);
        Assert.assertEquals(findMailAddress(), EMAIL_DATA[0]);
    }

    @Test(dependsOnMethods = {"createDraftTest"})
    public void sendDraftTest(){
        LettersContainer drafts = new LettersContainer();
        learning.page_object.pages.NewLetter currentLetter = drafts.openLastMessage();
        learning.page_object.pages.SendResultPage result = currentLetter.sendLetter();
        Assert.assertEquals(result.getStatus(), "Message sent successfully.");
    }

    @Test(dependsOnMethods = {"sendDraftTest"})
    public void checkDraftTest(){
        Menu mailBoxMenu = new Menu();
        LettersContainer lettersList = mailBoxMenu.openDraftsFolder();
        try{
            lettersList.getIsEmptyListLocator();
        }catch (NoSuchElementException e){
            Assert.assertTrue(false);
        };
    }

    @Test(dependsOnMethods = {"sendDraftTest"})
    public void checkSentTest(){
        Menu mailBoxMenu = new Menu();
        LettersContainer mailList = mailBoxMenu.openSentFolder();
        String title = mailList.getLastMessageSubject();
        String email = mailList.getLastMessageAddress();

        Assert.assertEquals(title, EMAIL_DATA[1]);
        Assert.assertEquals(email, EMAIL_DATA[0]);
    }

    @Test(description = "clean sent folder", dependsOnMethods = {"checkSentTest"})
    public void cleanSentFolder(){
        LettersContainer mailList = new LettersContainer();
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
        Menu mailBoxMenu = new Menu();
        mailBoxMenu.openAccountMenu();
        mailBoxMenu.logoutSubmit();
        Assert.assertEquals(mailBoxMenu.returnTitle(), "Яндекс");
    }

    @AfterClass(description = "close browser")
    public void closure(){
        WebDriverSingleton.kill();
    }
}

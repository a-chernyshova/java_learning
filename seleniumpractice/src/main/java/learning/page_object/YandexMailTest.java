package learning.page_object;

import learning.page_object.buisness_object.User;
import learning.page_object.pages.*;
import learning.page_object.utils.WebDriverSingleton;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class YandexMailTest {
    private static final String[] EMAIL_DATA = {"test@gmail.com", "Selenium webdriver",
            "The primary new feature in Selenium 2.0 is the integration of the WebDriver API. " +
            "WebDriver is designed to provide a simpler, more concise programming interface in addition to addressing " +
            "some limitations in the Selenium-RC API. \nSelenium-WebDriver was developed to better support dynamic " +
            "web pages where elements of a page may change without the page itself being reloaded. \nWebDriver’s " +
            "goal is to supply a well-designed object-oriented API that provides improved support for modern " +
            "advanced web-app testing problems."};

    private String authorization(User user){
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage logForm = new LoginPage();
        logForm.fillLoginCredentials(user);
        return logForm.returnTitle();
    }
    private String incorrectAuthorization(User user){
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage logForm = new LoginPage();
        logForm.fillIncorrectLoginCredentials(user);
        return logForm.returnTitle();
    }

    @Test(enabled = true, description="check logging in with incorrect credentials")
    public void loginFailTest(){
        String winTitle = incorrectAuthorization(new User()); //incorrect pass
        Assert.assertEquals(winTitle, "Авторизация");
    }

    @Test(dependsOnMethods = {"loginFailTest"})
    public void loginTest(){
        String winTitle = authorization(new User());
        Assert.assertEquals(winTitle, "Yandex.Mail");
    }

    @Test(enabled = false, dependsOnMethods = {"loginTest"})
    public void createDraftTest(){
        Menu mailBoxMenu = new Menu();
        learning.page_object.pages.NewLetter newLetterForm = mailBoxMenu.openNewMailForm();
        newLetterForm.fillNewLetterForm(EMAIL_DATA);
        mailBoxMenu.openDraftsFolder();
        newLetterForm.saveDraft();

        Assert.assertEquals(new LettersContainer().getLastMessageSubject(), EMAIL_DATA[1]);
        Assert.assertEquals(new LettersContainer().getLastMessageAddress(), EMAIL_DATA[0]);
    }

    @Test(enabled=false, dependsOnMethods = {"createDraftTest"})
    public void sendDraftTest(){
        LettersContainer drafts = new LettersContainer();
        learning.page_object.pages.NewLetter currentLetter = drafts.openLastMessage();
        learning.page_object.pages.SendResultPage result = currentLetter.sendLetter();
        Assert.assertEquals(result.getStatus(), "Message sent successfully.");
    }

    @Test(enabled = false, dependsOnMethods = {"sendDraftTest"})
    public void checkDraftTest(){
        Menu mailBoxMenu = new Menu();
        LettersContainer lettersList = mailBoxMenu.openDraftsFolder();
        try{
            lettersList.getIsEmptyListLocator();
        }catch (NoSuchElementException e){
            Assert.assertTrue(false);
        };
    }

    @Test(enabled = false, dependsOnMethods = {"sendDraftTest"})
    public void checkSentTest(){
        Menu mailBoxMenu = new Menu();
        LettersContainer mailList = mailBoxMenu.openSentFolder();
        String title = mailList.getLastMessageSubject();
        String email = mailList.getLastMessageAddress();

        Assert.assertEquals(title, EMAIL_DATA[1]);
        Assert.assertEquals(email, EMAIL_DATA[0]);
    }

    @Test(enabled = false, description = "clean sent folder", dependsOnMethods = {"checkSentTest"})
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

    @Test(enabled = false, dependsOnMethods = {"cleanSentFolder"})
    public void logoutTest(){
        Menu mailBoxMenu = new Menu();
        mailBoxMenu.openAccountMenu();
        mailBoxMenu.logoutSubmit();
        Assert.assertEquals(mailBoxMenu.returnTitle(), "Яндекс");
    }

    @Test(description = "open disk and create test folder", dependsOnMethods = {"loginTest"})
    public void openDiskTest(){
        Menu mailBoxMenu = new Menu();
        DiskPage disk = mailBoxMenu.openDisk();
        Assert.assertEquals(disk.returnTitle(), "Yandex.Disk");
    }
    @Test(dependsOnMethods = {"openDiskTest"})
    public void createFolderTest(){
        DiskPage disk = new DiskPage();
        disk.createFolder("test");
    }
    @Test(enabled = true, dependsOnMethods = {"createFolderTest"})
    public void uploadFileTest(){
        DiskPage disk = new DiskPage();
        disk.uploadFile("C:\\Users\\Anastasiia_Chernysho\\Documents\\test.txt");
        Assert.assertTrue(disk.checkFileUploaded());
    }
    @Test(enabled = true, dependsOnMethods = {"uploadFileTest"})
    public void dragFileToTest(){
        DiskPage disk = new DiskPage();
        disk.dragFileToFolder();
    }
    @Test(dependsOnMethods = {"dragFileToTest"})
    public void openFolderTest(){
        DiskPage disk = new DiskPage();
        Assert.assertTrue(disk.openFolder());
    }
    @Test(dependsOnMethods ={"openFolderTest"}, description = "Remove test folder from disk")
    public void removeFolder(){
        DiskPage disk = new DiskPage();
        Assert.assertTrue(disk.removeFolder());
    }
    @AfterClass(description = "close browser")
    public void closure(){
        WebDriverSingleton.kill();
    }
}

package learning.page_object;

import learning.page_object.buisness_object.User;
import learning.page_object.pages.DiskPage;
import learning.page_object.pages.HomePage;
import learning.page_object.pages.LoginPage;
import learning.page_object.pages.Menu;
import learning.page_object.utils.WebDriverSingleton;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class YandexDiskTest {
    private String authorization(User user){
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage logForm = new LoginPage();
        logForm.fillLoginCredentials(user);
        return logForm.returnTitle();
    }
    private DiskPage disk = new DiskPage();

    @BeforeSuite
    public void loginTest(){
        String winTitle = authorization(new User());
        Assert.assertEquals(winTitle, "Yandex.Mail");
    }

    @Test(description = "open disk and create test folder")
    public void openDiskTest(){
        Menu mailBoxMenu = new Menu();
        DiskPage disk = mailBoxMenu.openDisk();
        Assert.assertEquals(disk.returnTitle(), "Yandex.Disk");
    }

    @Test(dependsOnMethods = {"openDiskTest"})
    public void createFolderTest(){
        disk.createFolder("test");
    }
    @Test(enabled = true, dependsOnMethods = {"createFolderTest"})
    public void uploadFileTest(){
        disk.uploadFile("C:\\Users\\Anastasiia_Chernysho\\Documents\\test.txt");
        Assert.assertTrue(disk.checkFileUploaded());
    }
    @Test(enabled = true, dependsOnMethods = {"uploadFileTest"})
    public void dragFileToTest(){
        disk.dragFileToFolder();
    }
    @Test(dependsOnMethods = {"dragFileToTest"})
    public void openFolderTest(){
        Assert.assertTrue(disk.openFolder());
    }
    @Test(dependsOnMethods ={"openFolderTest"}, description = "Remove test folder from disk")
    public void removeFolder(){
        Assert.assertTrue(disk.removeFolder());
    }
    @AfterClass(description = "close browser")
    public void closure(){
        WebDriverSingleton.kill();
    }
}

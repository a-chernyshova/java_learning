package org.selenide.yandex.disk;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit.ScreenShooter;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.By;
import org.selenide.yandex.disk.buisness.object.User;
import org.selenide.yandex.disk.pages.DiskPage;
import org.selenide.yandex.disk.pages.LoginForm;
import org.selenide.yandex.disk.pages.Menu;
import org.testng.Assert;
import org.testng.annotations.*;
import com.codeborne.selenide.Selenide;

import javax.jws.soap.SOAPBinding;
import javax.swing.text.Highlighter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class YandexDiskTest{
    private YandexDiskTest yandexPage;
    private static String folderName = "test";

    @BeforeSuite
    public void openYandex(){
        yandexPage = open("https://www.yandex.ru/", YandexDiskTest.class);
    }

    @Test
    public void loginToYandex(){
        LoginForm loginForm = new LoginForm();
        loginForm.inputLogin(new User().LOGIN);
        loginForm.inputPassword(new User().PASSWORD);
        Selenide.Wait();
        $(".mail-Logo-Yandex").waitUntil(Condition.visible, 8000);
        assert title().contains("Yandex.Mail");
    }

    @Test(enabled=true, dependsOnMethods = {"loginToYandex"})
    public void openDisk(){
        Menu menu = new Menu();
        menu.DISK_LINK.waitUntil(Condition.enabled, 5000).followLink();
        menu.MAIL_LOGO.waitUntil(Condition.disappears, 8000);
        $(".upload-button__attach").waitUntil(Condition.enabled, 8000);
        assert title().equals("Yandex.Disk");
    }

    @Test(enabled = false, dependsOnMethods = {"openDisk"})
    public void createFolder(){
        DiskPage disk = new DiskPage();
        Boolean res = disk.createFolder(folderName);
        Assert.assertTrue(res);
        disk.findCreatedFolder(folderName);
    }

    @Test(enabled = false, dependsOnMethods = {"uploadFile"})
    public void checkIfCanCreateTheSameNameFolder(){
        DiskPage disk = new DiskPage();
        Boolean res = disk.createFolder(folderName);
        Assert.assertFalse(res);
    }

    @Test(dependsOnMethods = {"openDisk"})
    public void uploadFile(){
        DiskPage disk = new DiskPage();
        disk.uploadFile("C:\\Users\\Anastasiia_Chernysho\\Documents\\test.txt");
        Assert.assertTrue(disk.checkFileUploaded());
    }

    @Test(dependsOnMethods = {"uploadFile"})
    public void emptyTrashTest(){
        DiskPage disk = new DiskPage();
        disk.emptyTrash();
        disk.ifTrashEmpty();
    }

    @Test(dependsOnMethods = {"emptyTrashTest"})
    public void dragFileToFolderTest(){
        DiskPage disk = new DiskPage();
        disk.dragFileToFolder("test");
    }

    @Test(dependsOnMethods = {"dragFileToFolderTest"})
    public void logOut(){
        Menu menu = new Menu();
        menu.logOut();
        assert title().equals("Yandex");
    }
}

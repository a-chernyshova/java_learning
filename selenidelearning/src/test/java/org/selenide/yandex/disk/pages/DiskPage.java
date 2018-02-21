package org.selenide.yandex.disk.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class DiskPage {
    public static final SelenideElement MENU_CREATE_BUTTON = $(".nb-bar__flow_right .button2_theme_normal");
    public static final SelenideElement CREATE_FOLDER_BUTTON = $(".create-resource-button:first-child");
    public static final SelenideElement CREATE_DOC_BUTTON = $(".create-resource-button:second-child");
    public static final SelenideElement CREATE_PANNEL = $(".ns-view-aside");
    public static final SelenideElement FOLDER_MODIFIED_DATA = $(".b-aside__details");
    public static final SelenideElement UPLOAD_BUTTON = $(".upload-button__attach");
    public static final SelenideElement CONTENT_DIV_LOCATOR = $(".b-content");
    public static final SelenideElement FILE_LOCATOR = $(".ui-draggable[title='test.txt']");
    public static final SelenideElement TRASH_FOLDER = $(".ui-droppable[title=Trash]");

    public static boolean createFolder(String folderName) {
        MENU_CREATE_BUTTON.click();
        CREATE_FOLDER_BUTTON.waitUntil(Condition.enabled, 3000).click();
        CREATE_PANNEL.waitUntil(Condition.visible, 3000);
        getFocusedElement();
        Selenide.switchTo().activeElement().sendKeys(folderName + Keys.ENTER);
        FOLDER_MODIFIED_DATA.waitUntil(Condition.visible, 5000);
        String notification = $(".notifications").waitUntil(Condition.visible, 5000).getText();
        $(".notifications").waitUntil(Condition.disappears, 15000);
        if (notification.equals("Вы создали папку " + folderName)){
            System.out.println(notification);
            return true;
        }else{
            System.out.println(notification);
            return false;
        }
        //if (notification.equals("A folder named “" + folderName + "” already exists"))
    }
    public static void findCreatedFolder(String folderName){
        $$(".ui-droppable[title='" + folderName + "']").shouldHave(CollectionCondition.size(1));
    }
    public static void findRemovedFolder(String folderName){
        $$(".ui-droppable[title='" + folderName + "']").shouldHave(CollectionCondition.size(0));
    }
    public static void uploadFile(String pathToFile){
        UPLOAD_BUTTON.waitUntil(Condition.enabled, 5000).sendKeys(pathToFile);
        $(".b-dialog-upload__content").waitUntil(Condition.enabled, 10000);
        $(".b-dialog-upload__button-close").waitUntil(Condition.enabled, 10000).click();
        CONTENT_DIV_LOCATOR.waitUntil(Condition.enabled, 5000);
    }

    public static boolean checkFileUploaded(){
        try {
            FILE_LOCATOR.waitUntil(Condition.visible, 4000);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static void emptyTrash(){
        TRASH_FOLDER.click();
        $(".ns-view-cleanTrash button").waitUntil(Condition.enabled, 3000).click();
        Selenide.screenshot("modal_window");
        $(".js-confirmation-accept").waitUntil(Condition.visible, 5000).click();
        $(".b-progressbar__fill").waitUntil(Condition.disappears, 10000);
    }

    public static void ifTrashEmpty(){
        assert $(".ns-view-cleanTrash button").isDisplayed();
    }

    public static void dragFileToFolder(String folderName){
        if ($$(".crumbs__link[title='Disk'").size() != 0) {
            $(".crumbs__link[title='Disk'").click();
        }
        FILE_LOCATOR.waitUntil(Condition.visible, 5000).dragAndDropTo($(".ui-droppable[title='Trash']"));
        $(".ui-droppable[title='Trash']").doubleClick().waitUntil(Condition.disappears, 5000);
        Selenide.screenshot("file_in_folder");
    }
}

package learning.page_object.pages;

import learning.page_object.utils.Logger;
import learning.page_object.utils.Screenshoter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DiskPage extends AbstractPage {
    public static final By UPLOAD_INPUT_LOCATOR = By.className("upload-button__attach");
    public static final By OPEN_CREATE_OPTIONS_LOCATOR = By.cssSelector(".nb-bar__flow_right .button2_theme_normal");
    public static final By CREATE_FOLDER = By.cssSelector(".create-resource-button:first-child");
    public static final By FOLDER_CREATION_PANEL_LOCATOR = By.className(".nb-panel__content");
    public static final By CONTENT_DIV_LOCATOR = By.className("b-content");
    public static final By FOLDER_LOCATOR = By.cssSelector(".ui-droppable[title='test']");
    public static final By FILE_LOCATOR = By.cssSelector(".ui-draggable[title='test.txt']");
    public static final By REMOVE_BUTTON = By.cssSelector(".nb-panel__footer .nb-button[title='Delete']");
    public static final By BREAD_CRUMBS = By.className("crumbs__path");

    public DiskPage uploadFile(String pathToFile){
        Screenshoter.takeScreenshot();
        //waitForElementEnabled(By.className("upload-button__attach"));
        driver.findElement(By.className("upload-button__attach")).sendKeys(pathToFile);
        sleep();
        waitForElementEnabled(By.className("b-dialog-upload__content"));
        waitForElementVisible(By.className("b-dialog-upload__button-close"));
        driver.findElement(By.className("b-dialog-upload__button-close")).click();
        waitForElementEnabled(CONTENT_DIV_LOCATOR);
        Screenshoter.takeScreenshot();
        return this;
    }
    public Boolean checkFileUploaded(){
        try {
            driver.findElement(FILE_LOCATOR);
            Logger.info("File test.txt was uploaded");
            return true;
        } catch(Exception e){
            e.printStackTrace();
            Logger.error("Can't upload file test.txt");
            return false;
        }
    }
    public DiskPage createFolder(String filename){
        driver.findElement(OPEN_CREATE_OPTIONS_LOCATOR).click();
        waitForElementEnabled(CREATE_FOLDER);
        driver.findElement(CREATE_FOLDER).click();
        waitForElementEnabled(By.className("ns-view-aside"));
        WebElement currentElement = driver.switchTo().activeElement();
        waitForElementVisible(By.className("nb-panel__content"));
        currentElement.sendKeys(filename + Keys.ENTER);
        waitForElementEnabled(FOLDER_LOCATOR);
        Logger.info("Folder was created");
        Screenshoter.takeScreenshot();
        return this;
    }
    public DiskPage dragFileToFolder(){
        waitForElementEnabled(FILE_LOCATOR);
        WebElement file = driver.findElement(FILE_LOCATOR);
        highlightElement(FOLDER_LOCATOR);
        WebElement folder = driver.findElement(FOLDER_LOCATOR);
        highlightElement(FOLDER_LOCATOR);
        Screenshoter.takeScreenshot();
        new Actions(driver).dragAndDrop(file, folder).build().perform();
        Logger.info("File was moved to folder");
        unHighlightElement(FOLDER_LOCATOR);
        return this;
    }
    public Boolean openFolder(){
        Actions action = new Actions(driver);
        highlightElement(FOLDER_LOCATOR);
        action.moveToElement(driver.findElement(FOLDER_LOCATOR)).doubleClick().build().perform();
        Logger.info("Opened test folder");
        waitForElementEnabled(BREAD_CRUMBS);
        Screenshoter.takeScreenshot();
        try {
            driver.findElement(FILE_LOCATOR);
            return true;
        } catch(Exception e) {
            Logger.error("Can't open test folder");
            e.printStackTrace();
            return false;
        }
    }
    public Boolean removeFolder(){
        waitForElementEnabled(By.id("/disk"));
        highlightElement(BREAD_CRUMBS);
        Screenshoter.takeScreenshot();
        driver.findElement(BREAD_CRUMBS).findElement(By.xpath("div[2]/a")).click();
        waitForElementVisible(FOLDER_LOCATOR);
        driver.findElement(FOLDER_LOCATOR).click();
        waitForElementEnabled(By.cssSelector(".nb-panel__footer "));
        driver.findElement(REMOVE_BUTTON).click();
        sleep();
        try{
            driver.findElement(FOLDER_LOCATOR);
            Screenshoter.takeScreenshot();
            Logger.info("Folder was removed");
            return false;
        } catch (Exception e){
            e.printStackTrace();
            Screenshoter.takeScreenshot();
            Logger.error("Can't remove folder");
            return true;
        }
    }
    public String returnTitle(){
        return driver.getTitle();
    }
}

package learning.page_object.pages;
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

    public DiskPage uploadFile(String pathToFile){
        waitForElementEnabled(By.className("upload-button__attach"));
        driver.findElement(By.className("upload-button__attach")).sendKeys(pathToFile);
        waitForElementEnabled(By.className("b-dialog-upload__button-close"));
        driver.findElement(By.className("b-dialog-upload__button-close")).click();
        waitForElementEnabled(CONTENT_DIV_LOCATOR);
        return this;
    }
    public Boolean checkFileUploaded(){
        try {
            driver.findElement(FILE_LOCATOR);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public DiskPage createFolder(String filename){
        driver.findElement(OPEN_CREATE_OPTIONS_LOCATOR).click();
        waitForElementEnabled(CREATE_FOLDER);
        driver.findElement(CREATE_FOLDER).click();
        WebElement currentElement = driver.switchTo().activeElement();
        waitForElementEnabled(By.className("nb-panel__content"));
        currentElement.sendKeys(filename + Keys.ENTER);
        waitForElementEnabled(FOLDER_LOCATOR);
        return this;
    }
    public DiskPage dragFileToFolder(){
        waitForElementEnabled(FILE_LOCATOR);
        WebElement file = driver.findElement(FILE_LOCATOR);
        WebElement folder = driver.findElement(FOLDER_LOCATOR);
        new Actions(driver).dragAndDrop(file, folder).build().perform();
        return this;
    }
    public Boolean openFolder(){
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(FOLDER_LOCATOR)).doubleClick().build().perform();
        waitForElementEnabled(By.className("crumbs__path"));
        try {
            driver.findElement(FILE_LOCATOR);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public String returnTitle(){
        return driver.getTitle();
    }
}

package learning.page_object.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewLetter extends AbstractPage{
    public static final By SAVE_CHANGES_BUTTON_LOCATOR = By.cssSelector("._nb-modal-popup button");
    public static final By DONT_SAVE_CHANGES_BUTTON_LOCATOR = By.linkText("Don't save");
    public static final By CANCEL_BUTTON_LOCATOR = By.linkText("Cancel");
    public static final By SEND_BUTTON_LOCATOR = By.cssSelector(".mail-Compose-From ._nb-action-button");
    public static final By INPUT_FORM_LOCATOR = By.className("mail-Compose-Field-Input");

//    public NewLetter(WebDriver driver){super(driver);}

    public By getInputLocator(){
        return INPUT_FORM_LOCATOR;
    }
    public void fillNewLetterForm(String[] testData){
        waitForElementPresent(INPUT_FORM_LOCATOR);
        WebElement currentElement = driver.switchTo().activeElement();
        currentElement.sendKeys(testData[0] + Keys.TAB + testData[1] + Keys.TAB + testData[2]);
    }
    public void saveDraft(){
        driver.findElement(SAVE_CHANGES_BUTTON_LOCATOR).click();
    }
    public void discardChanges(){
        driver.findElement(DONT_SAVE_CHANGES_BUTTON_LOCATOR).click();
    }
    public void goBackToDraft(){
        driver.findElement(CANCEL_BUTTON_LOCATOR).click();
    }
    public SendResultPage sendLetter(){
        driver.findElement(SEND_BUTTON_LOCATOR).click();
        return new SendResultPage();
    }
}
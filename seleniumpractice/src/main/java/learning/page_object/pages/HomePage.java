package learning.page_object.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    public static final By SEARCH_INPUT_LOCATOR = By.cssSelector(".search.2 .input__control");
    public static final By RUN_SEARCH_LOCATOR = By.cssSelector("search2_button");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }
    public void open(){
        driver.get("https://www.yandex.ru/");
    }
    public void fillSearchInput(String query){
        driver.findElement(SEARCH_INPUT_LOCATOR).sendKeys(query);
    }

    public void startSearch(){
        driver.findElement(RUN_SEARCH_LOCATOR).click();
    }
}

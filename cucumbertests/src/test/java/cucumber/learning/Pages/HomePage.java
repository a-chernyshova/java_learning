package cucumber.learning.Pages;

import org.openqa.selenium.By;

public class HomePage extends AbstractPage{
    public static final String MAIN_URL = "https://www.yandex.ru/";
    public static final By SEARCH_INPUT_LOCATOR = By.cssSelector(".search.2 .input__control");
    public static final By RUN_SEARCH_LOCATOR = By.cssSelector("search2_button");

//    public HomePage open(){
//        open(MAIN_URL);
//        return this;
//    }
    public void fillSearchInput(String query){
        driver.findElement(SEARCH_INPUT_LOCATOR).sendKeys(query);
    }

    public void startSearch(){
        driver.findElement(RUN_SEARCH_LOCATOR).click();
    }
}

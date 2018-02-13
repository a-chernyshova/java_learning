package learning.page_object.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {
    private static WebDriver instance;
    private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";

    private WebDriverSingleton(){}

    public static WebDriver getWebdriverInstance(){
        if (instance != null){
            return instance;
        }
        return instance = init();
    }

    private static WebDriver init(){
        WebDriver driver = null;
        try{
            driver = new ChromeDriver();
        }catch (Exception e){
            System.out.println("Can't open browser");
            e.printStackTrace();
        }
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void kill(){
        if (instance != null){
            try{
                instance.quit();
            }catch (Exception e){
                Logger.error("Cannot kill browser");
                System.out.println("Cannot kill browser");
            }finally{
                instance = null;
            }
        }
    }
    public void takeScreenshot() {
        File screenshot = ((TakesScreenshot) instance).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
            File copy = new File(screenshotName + ".png");
            FileUtils.copyFile(screenshot, copy);
            Logger.info("Saved screenshot: " + screenshotName);
        } catch (IOException e) {
            Logger.error("Failed to make screenshot");
        }
    }
}

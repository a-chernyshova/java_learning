package learning.page_object.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {
    private static WebDriver instance;

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
        return driver;
    }

    public static void kill(){
        if (instance != null){
            try{
                instance.quit();
            }catch (Exception e){
                //logger.error("Cannot kill browser");
                System.out.println("Cannot kill browser");
            }finally{
                instance = null;
            }
        }
    }

}

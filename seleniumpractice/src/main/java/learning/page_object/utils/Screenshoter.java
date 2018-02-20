package learning.page_object.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.time.LocalDateTime;

public class Screenshoter {
    private static final String SCREENSHOT_NAME = "screenshot/screenshot_";

    private static void takeScreenshot(){
        WebDriver driver = WebDriverSingleton.getWebdriverInstance();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            LocalDateTime timePoint = LocalDateTime.now();
            String screenshotName = SCREENSHOT_NAME + "_" + timePoint.getDayOfMonth() + "_" + timePoint.getHour() +
                    "_" + timePoint.getMinute() + "_" + timePoint.getSecond();
            File copy = new File(screenshotName + ".png");
            FileUtils.copyFile(screenshot, copy);
            Logger.info("Saved screenshot: " + screenshotName);
        }catch (Exception e) {
            Logger.error("Failed to take screenshot");
        }
    }
}
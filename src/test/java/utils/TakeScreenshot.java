package utils;

import base.BaseTest;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TakeScreenshot
{
    public static String takeScreenshot(WebDriver driver) throws IOException {
        System.out.println("Execution entered here");
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
        String formattedDate = formatter.format(dateTime);
        String path = System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\screenshot" + formattedDate + ".png";
        System.out.println(path);
        File dest = new File(path);
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        Files.copy(src, dest);
        BaseTest.logger.info("Screenshot Taken");
        return path;
    }
}

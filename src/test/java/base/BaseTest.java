package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import reports.ExtentManager;
import utils.LoadDataProperties;

import java.io.IOException;

/**
 * Purpose:
 *
 *      Launch browser
 *      Close browser
 *      Common setup
 *      Common teardown
 */
public class BaseTest
{

    private WebDriver driver;
    public static ExtentReports extentReports;
    public static ExtentTest test;
    @BeforeAll
    static void startReporting()
    {
        extentReports = ExtentManager.getReporter();
    }



    @BeforeEach
    void setDriver() throws IOException {
        LoadDataProperties.loadProperties();
        if(LoadDataProperties.getBrowser().equalsIgnoreCase("edge"))
        {
            this.driver = new EdgeDriver();
            this.driver.manage().window().maximize();
            this.driver.get(LoadDataProperties.getURL());
        } else if (LoadDataProperties.getBrowser().equalsIgnoreCase("chrome")) {
            this.driver = new ChromeDriver();
            this.driver.manage().window().maximize();
            this.driver.get(LoadDataProperties.getURL());
        } else {
            System.out.println("Invalid Browser");
            throw new InvalidArgumentException("Invalid Driver Type");
        }
    }

    protected WebDriver getDriver()
    {
        return this.driver;
    }
    @AfterEach
    void driverTeardown()
    {
        if (driver!=null)
            driver.quit();
    }
    @AfterAll
    static void flushReport()
    {
        extentReports.flush();
    }
}

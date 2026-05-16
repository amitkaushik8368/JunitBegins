package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.junit.jupiter.api.*;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import reports.ExtentManager;
import utils.LoadDataProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    public static final Logger logger = LogManager.getLogger(BaseTest.class);
    private WebDriver driver;
    public static ExtentReports extentReports;
    public static ExtentTest test;
    @BeforeAll
    static void startReporting() throws IOException {
        logger.info("Extent Report Initialized");
        extentReports = ExtentManager.getReporter();
        logger.info("Data Properties Loaded");
    }



    @BeforeEach
    void setDriver(TestInfo testInfo) throws IOException {
        if(LoadDataProperties.get("browser").equalsIgnoreCase("edge"))
        {
            EdgeOptions options = new EdgeOptions();
            if (Boolean.parseBoolean(LoadDataProperties.get("headless")))
                options.addArguments("--headless=new");
            if (Boolean.parseBoolean(LoadDataProperties.get("incognito")))
                options.addArguments("--inprivate");
            this.driver = new EdgeDriver(options);
            logger.info("EdgeDriver Loaded for the test method: {}", testInfo.getTestMethod());
            this.driver.manage().window().maximize();
            this.driver.get(LoadDataProperties.get("url"));
            logger.info("Application URL Loaded on Edge");
        } else if (LoadDataProperties.get("browser").equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (Boolean.parseBoolean(LoadDataProperties.get("headless")))
                options.addArguments("--headless=new");
            if (Boolean.parseBoolean(LoadDataProperties.get("incognito")))
                options.addArguments("--incognito");
            this.driver = new ChromeDriver(options);
            logger.info("ChromeDriver Loaded for the test method: {}", testInfo.getTestMethod());
            this.driver.manage().window().maximize();
            this.driver.get(LoadDataProperties.get("url"));
            logger.info("Application URL Loaded on Chrome");
        } else {
            System.out.println("Invalid Browser");
            logger.error("Invalid Browser name passed");
            throw new InvalidArgumentException("Invalid Driver Type");
        }
    }

    protected WebDriver getDriver()
    {
        return this.driver;
    }
    @AfterEach
    void driverTeardown(TestInfo testInfo)
    {
        if (driver!=null)
            driver.quit();
        logger.info("Driver closed for the test {}", testInfo.getTestMethod());
    }
    @AfterAll
    static void flushReport()
    {
        extentReports.flush();
        logger.info("Extent Report Flushed");
    }
}

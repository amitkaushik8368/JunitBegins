package base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
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

    String browserType = "edge";
    private WebDriver driver;

    @BeforeEach
    void setDriver() throws InterruptedException {
//        this.driver = new EdgeDriver();
//        this.driver.manage().window().maximize();
        if(browserType.equalsIgnoreCase("edge"))
        {
            this.driver = new EdgeDriver();
            this.driver.manage().window().maximize();
        } else if (browserType.equalsIgnoreCase("chrome")) {
            this.driver = new ChromeDriver();
            this.driver.manage().window().maximize();
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
}

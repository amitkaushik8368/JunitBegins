package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.JsonReader;
import utils.LoadDataProperties;
import utils.WaitUtility;

import java.io.IOException;

public class LoginPage
{
    WebDriver driver;
    WaitUtility waitUtility;
    By username = By.id("username");
    By password = By.id("password");
    By loginButton = By.className("radius");
    By loginMessage = By.id("flash");
    By elementalSelenium = By.xpath("//a[contains(@href, 'elemental')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        waitUtility = new WaitUtility(driver);
    }

    public void enterValidUsername()
    {
        //waitUtility.waitForElementVisibility(username).sendKeys(LoadDataProperties.get("valid.username"));
        waitUtility.waitForElementVisibility(username).sendKeys(JsonReader.getValue("validUser", "username"));
    }
    public void enterValidPassword()
    {
        //waitUtility.waitForElementVisibility(password).sendKeys(LoadDataProperties.get("valid.password"));
        waitUtility.waitForElementVisibility(password).sendKeys(JsonReader.getValue("validUser", "password"));
    }
    public void enterInvalidUsername()
    {
        //waitUtility.waitForElementVisibility(username).sendKeys(LoadDataProperties.get("invalid.username"));
        waitUtility.waitForElementVisibility(username).sendKeys(JsonReader.getValue("invalidUser", "username"));
    }
    public void enterInvalidPassword()
    {
        //waitUtility.waitForElementVisibility(password).sendKeys(LoadDataProperties.get("invalid.password"));
        waitUtility.waitForElementVisibility(password).sendKeys(JsonReader.getValue("invalidUser", "password"));
    }
    public SecureAreaPage clickLogin()
    {
        this.driver.findElement(loginButton).click();
        return new SecureAreaPage(driver);
    }

    public String getLoginMessage()
    {
        return waitUtility.waitForElementVisibility(loginMessage).getText().replaceAll("[^A-Za-z ]", "");
    }

    public void navigateElementalSelenium() throws InterruptedException {
        waitUtility.waitForElementVisibility(elementalSelenium).click();
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.LoadDataProperties;

import java.io.IOException;

public class LoginPage
{
    WebDriver driver;
    By username = By.id("username");
    By password = By.id("password");
    By loginButton = By.className("radius");
    By loginMessage = By.id("flash");
    By elementalSelenium = By.xpath("//a[contains(@href, 'elemental')]");

    public LoginPage(WebDriver driver) throws IOException {
        LoadDataProperties.loadProperties();
        this.driver = driver;
        this.driver.get(LoadDataProperties.getURL());
    }

    public void enterValidUsername()
    {
        this.driver.findElement(username).sendKeys(LoadDataProperties.getValidUsername());
    }
    public void enterValidPassword()
    {
        this.driver.findElement(password).sendKeys(LoadDataProperties.getValidPassword());
    }
    public void enterInvalidUsername()
    {
        this.driver.findElement(username).sendKeys(LoadDataProperties.getInvalidUsername());
    }
    public void enterInvalidPassword()
    {
        this.driver.findElement(password).sendKeys(LoadDataProperties.getInvalidPassword());
    }
    public void clickLogin()
    {
        this.driver.findElement(loginButton).click();
    }

    public String getLoginMessage()
    {
        return this.driver.findElement(loginMessage).getText().replaceAll("[^A-Za-z ]", "");
    }

    public void navigateElementalSelenium() throws InterruptedException {
        this.driver.findElement(elementalSelenium).click();
    }


}

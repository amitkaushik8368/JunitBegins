package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage
{
    By logoutButton = By.xpath("//a[contains(@href, 'logout')]");
    By loginMessage = By.id("flash");

    public void clickLogout(WebDriver driver)
    {
        driver.findElement(logoutButton).click();
    }
    public String getLoginMessage(WebDriver driver)
    {
        return driver.findElement(loginMessage).getText().replaceAll("[^A-Za-z ]", "");
    }

}

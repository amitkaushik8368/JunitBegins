package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtility;

public class SecureAreaPage
{
    By logoutButton = By.xpath("//a[contains(@href, 'logout')]");
    By loginMessage = By.id("flash");
    WaitUtility waitUtility;
    public SecureAreaPage(WebDriver driver)
    {
        waitUtility = new WaitUtility(driver);
    }
    public void clickLogout()
    {
        waitUtility.waitForElementVisibility(logoutButton).click();
    }
    public String getLoginMessage()
    {
        return waitUtility.waitForElementVisibility(loginMessage).getText().replaceAll("[^A-Za-z ]", "");
    }

}

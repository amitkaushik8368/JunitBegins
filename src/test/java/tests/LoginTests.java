package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.bidi.log.Log;
import org.opentest4j.AssertionFailedError;
import pages.LoginPage;
import pages.SecureAreaPage;
import utils.LoadDataProperties;
import utils.TakeScreenshot;

import java.io.IOException;


public class LoginTests extends BaseTest
{
    @Test
    void validatePageLoad() throws IOException {
        LoginPage loginPage = new LoginPage(getDriver());
        try {
            Assertions.assertEquals("The Intenet", getDriver().getTitle());
        } catch (AssertionFailedError e) {
            TakeScreenshot.takeScreenshot(getDriver());
            throw e;
        }
    }
    @Test
    void validateLoginSuccess() throws IOException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterValidUsername();
        loginPage.enterValidPassword();
        loginPage.clickLogin();
        SecureAreaPage secureAreaPage = new SecureAreaPage();
        String loginMessage = secureAreaPage.getLoginMessage(getDriver());
        try {
            Assertions.assertTrue(loginMessage.contains("You logged into a secure area"));
        } catch (Exception e) {
            TakeScreenshot.takeScreenshot(getDriver());
        }
    }

    @Test
    void validateInvalidUsername() throws IOException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterInvalidUsername();
        loginPage.enterValidPassword();
        loginPage.clickLogin();
        String loginMessage = loginPage.getLoginMessage();
        Assertions.assertTrue(loginMessage.contains("Your username is invalid"));
    }

    @Test
    void validateInvalidPassword() throws IOException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterValidUsername();
        loginPage.enterInvalidPassword();
        loginPage.clickLogin();
        String loginMessage = loginPage.getLoginMessage();
        Assertions.assertTrue(loginMessage.contains("Your password is invalid"));
    }
    @Test
    void validateLogoutFunctionality() throws IOException, InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterValidUsername();
        loginPage.enterValidPassword();
        loginPage.clickLogin();
        SecureAreaPage secureAreaPage = new SecureAreaPage();
        secureAreaPage.clickLogout(getDriver());
        String logoutMessage = loginPage.getLoginMessage();
        Assertions.assertTrue(logoutMessage.contains("You logged out of the secure area"));
    }
    @Test
    void validateElementalSelenium() throws IOException, InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        String originalWindow = getDriver().getWindowHandle();
        loginPage.navigateElementalSelenium();
        for (String handle : getDriver().getWindowHandles())
        {
            if (!handle.equals(originalWindow)){
                getDriver().switchTo().window(handle);
                break;
            }
        }
        String title = getDriver().getTitle();
        if (title != null) {
            Assertions.assertTrue(title.contains("Elemental"));
        }
    }

}

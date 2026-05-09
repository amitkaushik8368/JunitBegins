package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.bidi.log.Log;
import pages.LoginPage;
import pages.SecureAreaPage;
import utils.LoadDataProperties;

import java.io.IOException;
import java.util.Base64;

public class LoginTests extends BaseTest
{
    @Test
    void validatePageLoad() throws IOException {
        LoginPage loginPage = new LoginPage(getDriver());
        Assertions.assertEquals("The Internet", getDriver().getTitle());
    }
    @Test
    void validateLoginSuccess() throws IOException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterValidUsername();
        loginPage.enterValidPassword();
        loginPage.clickLogin();
        SecureAreaPage secureAreaPage = new SecureAreaPage();
        String loginMessage = secureAreaPage.getLoginMessage(getDriver());
        //System.out.println(loginMessage);
        Assertions.assertTrue(loginMessage.contains("You logged into a secure area"));
    }

    @Test
    void validateInvalidUsername() throws IOException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterInvalidUsername();
        loginPage.enterValidPassword();
        loginPage.clickLogin();
        String loginMessage = loginPage.getLoginMessage();
        System.out.println(loginMessage);
        Assertions.assertTrue(loginMessage.contains("Your username is invalid"));
    }

    @Test
    void validateInvalidPassword() throws IOException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterValidUsername();
        loginPage.enterInvalidPassword();
        loginPage.clickLogin();
        String loginMessage = loginPage.getLoginMessage();
        System.out.println(loginMessage);
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
        System.out.println(logoutMessage);
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

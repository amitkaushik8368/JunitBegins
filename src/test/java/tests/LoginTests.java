package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import pages.LoginPage;
import pages.SecureAreaPage;
import utils.TakeScreenshot;

import java.io.IOException;


public class LoginTests extends BaseTest
{
    @Test
    void validatePageLoad() throws IOException {
        test = extentReports.createTest("Validate Login Page Load");
        try {
            Assertions.assertEquals("The Internet", getDriver().getTitle());
            test.pass("The Login page has loaded successfully");
        } catch (AssertionFailedError e) {
            test.fail("Login Page has failed to Load: ").addScreenCaptureFromPath(TakeScreenshot.takeScreenshot(getDriver()));
            throw e;
        }
    }
    @Test
    void validateLoginSuccess() throws IOException {
        test = extentReports.createTest("Validate successful Login");
        LoginPage loginPage = new LoginPage(getDriver());
        test.info("Entering Username");
        loginPage.enterValidUsername();
        test.warning("Entering Password");
        loginPage.enterValidPassword();
        test.info("Clicking Login Button");
        SecureAreaPage secureAreaPage = loginPage.clickLogin();
        String loginMessage = secureAreaPage.getLoginMessage();
        try {
            Assertions.assertTrue(loginMessage.contains("You logged into a secure area"));
            test.pass("User is able to login through Valid credentials");
        } catch (Throwable e) {
            test.fail("Login Functionality Failed").addScreenCaptureFromPath(TakeScreenshot.takeScreenshot(getDriver()));
            throw e;
        }
    }

    @Test
    void validateInvalidUsername() throws IOException {
        test = extentReports.createTest("Validate Invalid Username Login Failure");
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterInvalidUsername();
        loginPage.enterValidPassword();
        loginPage.clickLogin();
        String loginMessage = loginPage.getLoginMessage();
        try {
            Assertions.assertTrue(loginMessage.contains("Your username is invalid"));
            test.pass("Login Failed with Invalid Username");
        }catch (Throwable e)
        {
            test.fail("Unauthorised Login happened with Invalid Username").addScreenCaptureFromPath(TakeScreenshot.takeScreenshot(getDriver()));
            throw e;
        }

    }

    @Test
    void validateInvalidPassword() throws IOException {
        test = extentReports.createTest("Validate login through Invalid  Password");
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterValidUsername();
        loginPage.enterInvalidPassword();
        loginPage.clickLogin();
        String loginMessage = loginPage.getLoginMessage();
        try {
            Assertions.assertTrue(loginMessage.contains("Your password is invalid"));
            test.pass("Login Failed with Invalid Password");
        } catch (Exception e) {
            test.fail("Unauthorised Login happened with Invalid Password").addScreenCaptureFromPath(TakeScreenshot.takeScreenshot(getDriver()));
            throw e;
        }
    }
    @Test
    void validateLogoutFunctionality() throws IOException, InterruptedException {
        test = extentReports.createTest("Validate Logout Functionality");
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterValidUsername();
        loginPage.enterValidPassword();
        SecureAreaPage secureAreaPage = loginPage.clickLogin();
        secureAreaPage.clickLogout();
        String logoutMessage = loginPage.getLoginMessage();
        try {
            Assertions.assertTrue(logoutMessage.contains("You logged out of the secure area"));
            test.pass("User is able to Logout correctly");
        } catch (Throwable e) {
            test.fail("User is unable to Logout").addScreenCaptureFromPath(TakeScreenshot.takeScreenshot(getDriver()));
            throw e;
        }
    }
    @Test
    void validateElementalSelenium() throws IOException, InterruptedException {
        test = extentReports.createTest("Validate  Elemental Selenium Navigation");
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
        try {
            if (title != null) {
                Assertions.assertTrue(title.contains("Elemental"));
                test.pass("User is able to navigate to Elemental Selenium:");
            }
        } catch (Throwable e) {
            test.fail("User is unable to navigate to Elemental Selenium").addScreenCaptureFromPath(TakeScreenshot.takeScreenshot(getDriver()));
            throw e;
        }
    }
}

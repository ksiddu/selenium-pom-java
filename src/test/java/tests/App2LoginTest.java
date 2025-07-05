package tests;

import constants.AppConstants;
import io.qameta.allure.*;
import listeners.AllureListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureListener.class})
public class App2LoginTest extends BaseTest{

    //@Epic("Login Feature")
    //@Feature("Valid Login")
    //@Story("User logs in with valid credentials")
    //@Severity(SeverityLevel.CRITICAL)
    @Test(description = "Login with valid credentials")
    public void testLoginApp2() {

            driver.get(prop.get("url"));
            app2LoginPage.login(prop.get("username"), prop.get("password"));
            Assert.assertTrue(dashboardPage.isLogoutDisplayed());
            Assert.assertTrue(app2LoginPage.waitForText(AppConstants.PROFILE_PAGE_TEXT));
            dashboardPage.logout();


    }

    @Test(description = "Login with invalid credentials")
    public void testLoginWithInvalidCredentialsApp2() {

        driver.get(prop.get("url"));
        app2LoginPage.login("sdsdfsf@gmail.com", "admin");
        Assert.assertTrue(app2LoginPage.waitForText(AppConstants.INVALID_LOGIN_TEXT));
    }
}

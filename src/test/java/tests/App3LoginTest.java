package tests;

import constants.AppConstants;
import listeners.AllureListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureListener.class})
public class App3LoginTest extends BaseTest{

    @Test
    public void testLoginApp3() {

            driver.get(prop.get("url"));
            app2LoginPage.login(prop.get("username"), prop.get("password"));
            Assert.assertTrue(dashboardPage.isLogoutDisplayed());
            Assert.assertTrue(app2LoginPage.waitForText(AppConstants.PROFILE_PAGE_TEXT));
            dashboardPage.logout();


    }

    @Test
    public void testLoginWithInvalidCredentialsApp3() {

        driver.get(prop.get("url"));
        app2LoginPage.login("sdsdfsf@gmail.com", "admin");
        Assert.assertTrue(app2LoginPage.waitForText(AppConstants.INVALID_LOGIN_TEXT));
    }
}

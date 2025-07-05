package tests;

import constants.AppConstants;
import listeners.AllureListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureListener.class})
public class LoginTest extends BaseTest{

    @Test
    public void testLogin() {

            driver.get(prop.get("url"));
            app1LoginPage.login(prop.get("username"), prop.get("password"));
            Assert.assertTrue(dashboardPage.isPageLoaded());
            Assert.assertTrue(dashboardPage.isTitleMatched(AppConstants.DASHBOARD_PAGE_TITLE));
            dashboardPage.logout();

    }

    @Test
    public void testLoginWithInvalidCredentials() {

        driver.get(prop.get("url"));
        app1LoginPage.login("admin", "admin");
        Assert.assertTrue(app1LoginPage.isLoginFailed());
    }
}

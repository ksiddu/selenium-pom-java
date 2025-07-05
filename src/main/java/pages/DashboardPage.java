package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    @FindBy(css = ".subheader")
    private WebElement welcomeMessage;

    @FindBy(css = "a[href*='logout']")
    private WebElement logoutLink;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getWelcomeMessage() {
        return getText(welcomeMessage, "Welcome Message");
    }

    public void logout() {
        click(logoutLink, "Logout Button");
    }

    @Override
    public boolean isPageLoaded() {
        return isDisplayed(welcomeMessage, "Welcome Message");
    }

    public boolean isTitleMatched(String value) {
        return verifyTextEquals(welcomeMessage, value,"Welcome Message");
    }

    public boolean isLogoutDisplayed() {
        return isDisplayed(logoutLink, "Logout Link");
    }

}

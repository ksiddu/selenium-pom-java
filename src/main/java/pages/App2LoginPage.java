package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class App2LoginPage extends BasePage {

    @FindBy(css = "[data-qa='login-email']")
    private WebElement usernameInput;

    @FindBy(css = "[data-qa='login-password']")
    private WebElement passwordInput;

    @FindBy(css = "[data-qa='login-button']")
    private WebElement loginButton;

    @FindBy(css = ".flash.error")
    private WebElement invalidLoginMessage;

    public App2LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        type(usernameInput, username, "Username Input");
    }

    public void enterPassword(String password) {
        type(passwordInput, password, "Password Input");
    }

    public void clickLogin() {
        click(loginButton, "Login Button");
    }

    //@Override
    public void login(String username, String password) {
        //isAlertPresentAndAccept();
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    @Override
    public boolean isPageLoaded() {
        return isDisplayed(usernameInput, "Username Input");
    }

    public boolean isLoginFailed() {
        return isDisplayed(invalidLoginMessage, "Login Error");
    }
}


package pages;

import exceptions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(BasePage.class);


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element, String elementName) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            logger.info("Clicked on: {}", elementName);
        } catch (Exception e) {
            logger.error("Failed to click on '{}': {}", elementName, e.getMessage(), e);
            throw new ElementNotClickableException("Click failed on " + elementName, e);
        }
    }

    public void type(WebElement element, String value, String elementName) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element)).clear();
            element.sendKeys(value);
            logger.info("Entered '{}' into: {}", value, elementName);
        } catch (Exception e) {
            logger.error("Failed to type into '{}': {}", elementName, e.getMessage(), e);
            throw new TypingFailedException("Typing failed on " + elementName, e);
        }
    }

    public String getText(WebElement element, String elementName) {
        try {
            String text = wait.until(ExpectedConditions.visibilityOf(element)).getText();
            logger.info("Fetched text from '{}': {}", elementName, text);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from '{}': {}", elementName, e.getMessage(), e);
            throw new GetTextFailedException("Get text failed on " + elementName, e);
        }
    }

    public boolean isDisplayed(WebElement element, String elementName) {
        try {
            boolean visible = wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
            logger.info("Is '{}' displayed? {}", elementName, visible);
            return visible;
        } catch (TimeoutException e) {
            logger.warn("'{}' not visible (timeout)", elementName);
            return false;
        } catch (Exception e) {
            logger.error("Failed to check display for '{}': {}", elementName, e.getMessage(), e);
            throw new ElementNotVisibleException("isDisplayed failed on " + elementName, e);
        }
    }

    // Text verification
    public boolean verifyTextEquals(WebElement element, String expectedText, String name) {
        String actual = getText(element, name);
        boolean match = actual.equals(expectedText);
        logger.info("Verify equals: " + name + " - Expected: '" + expectedText + "', Actual: '" + actual + "' = " + match);
        return match;
    }

    public boolean verifyTextContains(WebElement element, String partialText, String name) {
        String actual = getText(element, name);
        boolean match = actual.contains(partialText);
        logger.info("Verify contains: " + name + " - '" + partialText + "' in '" + actual + "' = " + match);
        return match;
    }

    // Dropdown methods
    public void selectByVisibleText(WebElement element, String visibleText, String name) {
        try {
            Select select = new Select(element);
            select.selectByVisibleText(visibleText);
            logger.info("Selected by visible text '" + visibleText + "' in: " + name);
        } catch (Exception e) {
            logger.error("SelectByVisibleText failed on: " + name + " - " + e.getMessage());
            throw e;
        }
    }

    public void selectByValue(WebElement element, String value, String name) {
        try {
            Select select = new Select(element);
            select.selectByValue(value);
            logger.info("Selected by value '" + value + "' in: " + name);
        } catch (Exception e) {
            logger.error("SelectByValue failed on: " + name + " - " + e.getMessage());
            throw e;
        }
    }

    public boolean isAlertPresentAndAccept() {
        try {
            //Alert alert = driver.switchTo().alert();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            String alertText = alert.getText();
            logger.info("Alert found with text: " + alertText);
            alert.accept(); // or alert.dismiss();
            logger.info("Alert accepted.");
            return true;
        } catch (NoAlertPresentException e) {
            logger.info("No alert present.");
            return false;
        } catch (Exception e) {
            logger.info("Error while handling alert: " + e.getMessage());
            return false;
        }
    }

    public boolean waitForText(String partialText) {
        try {
            //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(), '" + partialText + "')]")
            )) != null;
        } catch (TimeoutException e) {
            logger.info("Text not found within timeout: " + partialText);
            return false;
        }
    }

    // Enforce subclasses to implement page load check
    public abstract boolean isPageLoaded();
}

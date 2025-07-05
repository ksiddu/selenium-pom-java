package utils;

import drivers.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import drivers.ChromeDriverManager;
import contracts.IDriver;

public class DriverFactory {
    private static DriverFactory instance;
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    private DriverFactory() {
    }

    private void initDriver(String browser) {
        IDriver browserStrategy;

        switch (browser.toLowerCase()) {
            case "firefox":
                browserStrategy = new FirefoxDriverManager();
                break;
            case "chrome":
            default:
                browserStrategy = new ChromeDriverManager();
                break;
        }

        driverThread.set(browserStrategy.createDriver());
        //driver.manage().window().maximize();
        //return driver;
    }

    public static DriverFactory getInstance(String browser) {
        if (instance == null) {
            instance = new DriverFactory();
        }
        if (driverThread.get() == null) {
            instance.initDriver(browser);
        }

        return instance;
    }

    public static void setDriver(WebDriver driver) {
        driverThread.set(driver);
    }

    public  WebDriver getDriver() {
        return driverThread.get();
    }

    public static void quitBrowser() {
        if (driverThread.get() != null) {
            driverThread.get().quit();
            driverThread.remove();
        }
    }
}

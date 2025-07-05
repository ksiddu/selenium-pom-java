package drivers;

import contracts.IDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class ChromeDriverManager implements IDriver {

    @Override
    public WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        if (System.getProperty("headless", "false").equals("true")) {
            options.addArguments("--headless=new");
        }
        return new ChromeDriver(options);
    }
}

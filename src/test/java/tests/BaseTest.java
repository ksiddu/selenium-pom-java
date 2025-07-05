package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.DashboardPage;
import pages.App2LoginPage;
import pages.App1LoginPage;
import utils.AllureEnvironmentWriter;
import utils.DriverFactory;
import utils.PropertyUtil;
import java.lang.reflect.Method;

public abstract class BaseTest {

    protected WebDriver driver;
    protected App1LoginPage app1LoginPage;
    protected DashboardPage dashboardPage;
    protected App2LoginPage app2LoginPage;
    protected PropertyUtil prop;

    @BeforeClass
    public void setUp() {
        prop = new PropertyUtil();
        AllureEnvironmentWriter.writeAllureEnvironment();
        String browser = prop.get("browser");
        driver = DriverFactory.getInstance(browser).getDriver();// Use custom driver manager
        //DriverFactory.setDriver(driver);
        //driver = DriverFactory.getDriver();
        driver.manage().window().maximize();

        // Initialize page objects manually
        app1LoginPage = new App1LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        app2LoginPage = new App2LoginPage(driver);
        System.out.println("beforeClass: The thread ID in testLevelSetup is " + Thread.currentThread().getId());
    }

    @AfterClass
    public void tearDown() {

            System.out.println("afterClass: The thread ID in testLevelSetup is " + Thread.currentThread().getId());
            System.out.println("afterClass: Quit Driver");
            DriverFactory.quitBrowser();

    }

    @BeforeMethod
    public void beforeMethod(Method method){
        System.out.println("Running test method: " + method.getName());
        System.out.println("beforeMethod: The thread ID in beforeMethod is " + Thread.currentThread().getId());
    }

    public WebDriver getDriver() {
        return driver;
    }

    // Optional abstract method for subclasses to implement
    //public abstract void verifySetup();
}

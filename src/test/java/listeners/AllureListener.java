package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;
import utils.ImageUtil;
import utils.ScreenshotUtil;

public class AllureListener extends BaseTest implements ITestListener {

    private WebDriver driver;
    private ScreenshotUtil screenshotUtil;

    /*
    public AllureListener(WebDriver driver) {
        this.driver = driver;
        this.screenshotUtil = new ScreenshotUtil(driver);
    }

     */

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
        driver = ((BaseTest) testClass).getDriver();
        this.screenshotUtil = new ScreenshotUtil(driver);
        System.out.println("Failure: Screenshot captured");
        byte[] img = screenshotUtil.takeScreenshot("Failure: Screenshot captured - " + iTestResult.getName());

        // save screenshot locally
        System.out.println("Save screenshot locally");
        ImageUtil.writeImageFromBytes(img, ImageUtil.generateFileName(iTestResult));
    }

    // Other listener methods can be empty or implemented as needed
    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("onTestSuccess: Test Success - " + iTestResult.getName());

        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
        driver = ((BaseTest) testClass).getDriver();
        this.screenshotUtil = new ScreenshotUtil(driver);
        // save screenshot locally
        byte[] img = screenshotUtil.takeScreenshot("Success: Screenshot captured - " + iTestResult.getName());
        System.out.println("Save screenshot locally");
        ImageUtil.writeImageFromBytes(img, ImageUtil.generateFileName(iTestResult));
    }
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}

}

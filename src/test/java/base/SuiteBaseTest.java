package base;

import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.ConfigReader;
import utilities.PlaywrightManager;
import utilities.ScreenshotHelper;

public class SuiteBaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SuiteBaseTest.class);

    protected static String finalBrowser;
    protected static boolean finalHeadless;

    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() {
        LOGGER.info("╔════════════════════════════════════════╗");
        LOGGER.info("║      Test Suite Started                ║");
        LOGGER.info("╚════════════════════════════════════════╝");

        finalBrowser = System.getProperty("browser", ConfigReader.getBrowser());
        String headlessProp = System.getProperty("headless", String.valueOf(ConfigReader.isHeadless()));
        finalHeadless = Boolean.parseBoolean(headlessProp);

        LOGGER.info("Tests will run on: {}", finalBrowser);
        LOGGER.info("Headless mode: {}", finalHeadless);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        LOGGER.info("╔════════════════════════════════════════╗");
        LOGGER.info("║      Test Suite Finished               ║");
        LOGGER.info("╚════════════════════════════════════════╝");
    }

    @AfterMethod
    public void tearDownTest(ITestResult result) {
        String testName = result.getName();
        Page page = PlaywrightManager.getPage();

        LOGGER.info("Final URL: {}", page.url());
        if (result.getStatus() == ITestResult.FAILURE) {
            LOGGER.error("Test FAILED: {}", testName);
            ScreenshotHelper.captureScreenshot(result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            LOGGER.info("Test PASSED: {}", testName);
        } else if (result.getStatus() == ITestResult.SKIP) {
            LOGGER.warn("Test SKIPPED: {}", testName);
        }

        PlaywrightManager.teardownSuite();
        LOGGER.info("──────────────────────────────────────────");
    }
}
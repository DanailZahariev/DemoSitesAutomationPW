package demoqa.base;

import base.SuiteBaseTest;
import com.microsoft.playwright.Page;
import demoqa.pages.home.HomePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import uttilities.ConfigReader;
import uttilities.PlaywrightManager;

public class BaseTest extends SuiteBaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    protected HomePage homePage;

    @BeforeMethod
    public void setUpTest() {
        LOGGER.info("──────────────────────────────────────────");
        LOGGER.info("Starting test");

        String baseUrl = ConfigReader.getBaseUrl();
        PlaywrightManager.setUpSuite(finalBrowser, finalHeadless, baseUrl);
        Page page = PlaywrightManager.getPage();
        page.waitForLoadState();

        LOGGER.info("Page loaded successfully");
        LOGGER.info("Current URL: {}", page.url());
        LOGGER.info("Page title: {}", page.title());

        homePage = new HomePage(page);
    }
}

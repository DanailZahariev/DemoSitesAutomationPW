package saucedemo.base;

import base.SuiteBaseTest;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import saucedemo.pages.LoginPage;
import uttilities.PlaywrightManager;

public class BaseTest extends SuiteBaseTest {

    protected LoginPage loginPage;
    private final String url = "https://www.saucedemo.com/";
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUpTest() {
        LOGGER.info("──────────────────────────────────────────");
        LOGGER.info("Starting test");

        PlaywrightManager.setUpSuite(finalBrowser, finalHeadless, url);
        Page page = PlaywrightManager.getPage();
        page.waitForLoadState();

        LOGGER.info("Page loaded successfully");
        LOGGER.info("Current URL: {}", page.url());
        LOGGER.info("Page title: {}", page.title());

        loginPage = new LoginPage(page);
    }
}
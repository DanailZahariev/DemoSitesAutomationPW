package utilities.managers;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.ConfigReader;
import utilities.helpers.AdBlocker;

import java.util.List;

public class BrowserManager {

    private static final ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> contextThreadLocal = new ThreadLocal<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(BrowserManager.class);


    private BrowserManager() {
    }

    public static void launchBrowser(String browserType, boolean headless) {
        Playwright playwright = PlaywrightEngineManager.createPlaywright();

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setHeadless(headless)
                .setTimeout(ConfigReader.getTimeout())
                .setSlowMo(ConfigReader.getSlowMo())
                .setArgs(List.of(
                        "--no-sandbox",
                        "--disable-setuid-sandbox",
                        "--disable-dev-shm-usage"
                ));

        Browser browser = createBrowserType(playwright, browserType, launchOptions);
        browserThreadLocal.set(browser);

        LOGGER.info("Thread [{}]: Started {} browser (headless: {})",
                Thread.currentThread().getId(), browserType, headless);

    }

    public static void createContext() {
        Browser browser = getBrowser();
        if (browser == null) {
            throw new IllegalStateException("Browser not initialized. Call launchBrowser() first.");
        }

        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                .setViewportSize(ConfigReader.getViewportWidth(), ConfigReader.getViewportHeight());

        BrowserContext context = browser.newContext(contextOptions);

//        AdBlocker.blockAdsAndTracking(context);

        contextThreadLocal.set(context);
        LOGGER.debug("Thread [{}]: Browser context created.", Thread.currentThread().getId());

    }

    private static Browser createBrowserType(Playwright playwright, String browserType, BrowserType.LaunchOptions launchOptions) {
        return switch (browserType) {
            case "chromium", "chrome" -> playwright.chromium().launch(launchOptions);
            case "firefox" -> playwright.firefox().launch(launchOptions);
            case "webkit" -> playwright.webkit().launch(launchOptions);
            default -> {
                LOGGER.warn("Unknown browser: {}. Using Chromium.", browserType);
                yield playwright.chromium().launch(launchOptions);
            }
        };
    }

    public static void closeContext() {
        BrowserContext context = getContext();
        if (context != null) {
            context.close();
            contextThreadLocal.remove();
            LOGGER.debug("Thread [{}]: Browser context closed.", Thread.currentThread().getId());
        }
    }

    public static void closeBrowser() {
        Browser browser = getBrowser();
        if (browser != null) {
            browser.close();
            browserThreadLocal.remove();
            LOGGER.debug("Thread [{}]: Browser closed.", Thread.currentThread().getId());
        }
    }

    public static void closeAll() {
        closeContext();
        closeBrowser();
        LOGGER.info("Thread [{}]: All browser resources closed.", Thread.currentThread().getId());
    }

    public static Browser getBrowser() {
        return browserThreadLocal.get();
    }

    public static BrowserContext getContext() {
        return contextThreadLocal.get();
    }
}

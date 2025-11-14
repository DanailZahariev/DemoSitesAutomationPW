package uttilities;

import com.microsoft.playwright.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class PlaywrightManager {

    private static final ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> contextThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaywrightManager.class);

    public static void setUpSuite(String browserType, boolean headless, String url) {

        Playwright playwright = Playwright.create();
        playwrightThreadLocal.set(playwright);

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setHeadless(headless).
                setTimeout(ConfigReader.getTimeout()).setArgs(List.of("--no-sandbox"));

        BrowserContext context;

        Browser browser = switch (browserType) {
            case "chromium", "chrome" -> playwright.chromium().launch(launchOptions);
            case "firefox" -> playwright.firefox().launch(launchOptions);
            case "webkit" -> playwright.webkit().launch(launchOptions);
            default -> {
                LOGGER.warn("Unknown browser: {}. Using Chromium.", browserType);
                yield playwright.chromium().launch(launchOptions);
            }
        };

        browserThreadLocal.set(browser);
        LOGGER.info("Thread [{}]: Started {} browser (headless: {})", Thread.currentThread().getId(), browserType, headless);

        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                .setViewportSize(ConfigReader.getViewportWidth(), ConfigReader.getViewportHeight());
        context = browser.newContext(contextOptions);

        contextThreadLocal.set(context);

        Page page = context.newPage();
        pageThreadLocal.set(page);

        LOGGER.info("Thread [{}]: Navigating to: {}", Thread.currentThread().getId(), url);
        page.navigate(url);
    }

    public static void teardownSuite() {

        Page page = pageThreadLocal.get();
        if (page != null) {
            page.close();
            pageThreadLocal.remove();
        }

        BrowserContext context = contextThreadLocal.get();
        if (context != null) {
            context.close();
            contextThreadLocal.remove();
        }

        Browser browser = browserThreadLocal.get();
        if (browser != null) {
            browser.close();
            browserThreadLocal.remove();
        }

        Playwright playwright = playwrightThreadLocal.get();
        if (playwright != null) {
            playwright.close();
            playwrightThreadLocal.remove();
        }
        LOGGER.info("Thread [{}]: All Playwright resources closed.", Thread.currentThread().getId());
    }

    public static Page getPage() {
        Page page = pageThreadLocal.get();
        if (page == null) {
            LOGGER.error("Page not initialized for this thread");
            throw new IllegalStateException("Page not initialized. Call setupTestThread() first.");
        }
        return page;
    }

    public static BrowserContext getContext() {
        return contextThreadLocal.get();
    }
}
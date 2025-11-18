package utilities.managers;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.ConfigReader;

public class PageManager {

    private static final ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(PageManager.class);

    private PageManager() {
    }

    public static Page createPage() {
        BrowserContext context = BrowserManager.getContext();
        if (context == null) {
            throw new IllegalStateException("Browser context not initialized. Call launchBrowser() first.");
        }

        Page page = context.newPage();
        pageThreadLocal.set(page);
        LOGGER.debug("Thread [{}]: Page created.", Thread.currentThread().getId());

        return page;
    }

    public static void navigateTo(String url, WaitUntilState waitUntilState) {
        Page page = getPage();
        if (page == null) {
            throw new IllegalArgumentException("Page not initialized. Call createPage() first.");
        }

        LOGGER.info("Thread [{}]: Navigating to: {}", Thread.currentThread().getId(), url);

        page.navigate(url, new Page.NavigateOptions()
                .setTimeout(ConfigReader.getTimeout())
                .setWaitUntil(waitUntilState));
    }

    public static Page getPage() {
        Page page = pageThreadLocal.get();
        if (page == null) {
            throw new IllegalArgumentException("Page not initialized for this thread. Call createPage() first.");
        }
        return page;
    }

    public static void closePage() {
        Page page = pageThreadLocal.get();
        if (page != null) {
            page.close();
            pageThreadLocal.remove();
            LOGGER.debug("Thread [{}]: Page closed.", Thread.currentThread().getId());
        }
    }
}

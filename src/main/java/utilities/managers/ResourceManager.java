package utilities.managers;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceManager.class);

    private ResourceManager() {
    }

    public static void setUpBrowserAndNavigate(String browserType, boolean headless, String url) {
        BrowserManager.launchBrowser(browserType, headless);
        BrowserManager.createContext();
        PageManager.createPage();
        PageManager.navigateTo(url, WaitUntilState.LOAD);

    }

    public static void closeBrowser() {
        PageManager.closePage();
        BrowserManager.closeAll();
        LOGGER.info("Thread [{}]: All browser resources closed.", Thread.currentThread().getId());
    }

    public static Page getPage() {
        return PageManager.getPage();
    }

    public static BrowserContext getContext() {
        return BrowserManager.getContext();
    }
}

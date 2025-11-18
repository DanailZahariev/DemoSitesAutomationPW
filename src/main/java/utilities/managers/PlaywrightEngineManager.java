package utilities.managers;

import com.microsoft.playwright.Playwright;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlaywrightEngineManager {

    private static final ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaywrightEngineManager.class);

    private PlaywrightEngineManager() {
    }

    public static Playwright createPlaywright() {
        Playwright playwright = getPlaywright();
        if (playwright == null) {
            playwright = Playwright.create();
            playwrightThreadLocal.set(playwright);
            LOGGER.debug("Thread [{}]: Playwright engine initialized.", Thread.currentThread().getId());
        }
        return playwright;
    }

    public static void closePlaywright() {
        Playwright playwright = getPlaywright();
        if (playwright != null) {
            playwright.close();
            playwrightThreadLocal.remove();
            LOGGER.debug("Thread [{}]: Playwright engine closed.", Thread.currentThread().getId());
        }
    }

    private static Playwright getPlaywright() {
        return playwrightThreadLocal.get();
    }
}

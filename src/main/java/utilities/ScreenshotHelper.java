package utilities;

import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotHelper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    private static final Logger LOGGER = LoggerFactory.getLogger(ScreenshotHelper.class);

    public static String captureScreenshot(String testName) {

        if (!ConfigReader.isScreenshotOnFailure()) {
            return null;
        }

        Page page = PlaywrightManager.getPage();

        if (page == null) {
            LOGGER.error("Cannot capture screenshot: Page is null");
            return null;
        }

        try {
            String timestamp = LocalDateTime.now().format(FORMATTER);
            String screenshotDir = ConfigReader.getScreenshotDir();
            String filename = String.format("%s/%s_%s.png", screenshotDir, testName, timestamp);
            Files.createDirectories(Paths.get(screenshotDir));

            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get(filename))
                    .setFullPage(true));

            LOGGER.debug("Screenshot saved: {}", filename);
            return filename;
        } catch (Exception e) {
            LOGGER.error("Failed to capture screenshot: {}", e.getMessage());
            return null;
        }
    }
}

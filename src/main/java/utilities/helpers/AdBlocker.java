package utilities.helpers;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class AdBlocker {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdBlocker.class);
    private static final List<String> AD_PATTERNS = loadAdPatterns();

    private AdBlocker() {
    }

    private static List<String> loadAdPatterns() {
        try (var inputStream = AdBlocker.class.getClassLoader().getResourceAsStream("adblock-patterns.txt")) {
            if (inputStream == null) {
                LOGGER.warn("Ad patterns file not found!");
                return List.of();
            }
            return new String(inputStream.readAllBytes()).lines().toList();
        } catch (IOException e) {
            LOGGER.warn("Could not load ad patterns, using defaults", e);
            return List.of();
        }
    }

    public static void blockAdsAndTracking(BrowserContext context) {
        context.route("**/*", route -> {
            Request request = route.request();
            String requestUrl = request.url().toLowerCase();
            String resourceType = request.resourceType();
            if (AD_PATTERNS.stream().anyMatch(requestUrl::contains)) {
                route.abort();
                LOGGER.info("Blocked ad request: {}", route.request().url());
            } else if (AD_PATTERNS.stream().anyMatch(resourceType::contains)) {
                route.abort();
            } else {
                route.resume();
            }
        });
    }

}
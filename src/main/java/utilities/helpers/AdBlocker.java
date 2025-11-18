package utilities.helpers;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class AdBlocker {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdBlocker.class);

    private static final List<String> BLOCKED_DOMAINS = Arrays.asList(
            "doubleclick",
            "googlesyndication",
            "googleadservices",
            "google-analytics",
            "googletagmanager",
            "googletagservices",
            "adservice",
            "analytics",
            "tracking",
            "pagead",
            "adserver",
            "advertisement"
    );

    private static final List<String> BLOCKED_PATTERNS = Arrays.asList(
            "/ads/",
            "/ad/",
            "_ads"
    );

    private static final List<String> BLOCKED_IMAGE_PATTERNS = Arrays.asList(
            "ads",
            "banner",
            "sponsor"
    );

    private AdBlocker() {
    }

    public static void blockAdsAndTracking(BrowserContext context) {
        context.route("**/*", route -> {
            Request request = route.request();
            String requestUrl = request.url().toLowerCase();
            String resourceType = request.resourceType();

            if (shouldBlockRequest(requestUrl, resourceType)) {
                route.abort();
                LOGGER.debug("Blocked request: {} (type: {})", requestUrl, resourceType);
            } else {
                route.resume();
            }
        });

        LOGGER.info("Ad and tracking blocking enabled for browser context");
    }

    private static boolean shouldBlockRequest(String url, String resourceType) {
        if (containsAny(url, BLOCKED_DOMAINS)) {
            return true;
        }

        if (containsAny(url, BLOCKED_PATTERNS)) {
            return true;
        }

        return "image".equals(resourceType) && containsAny(url, BLOCKED_IMAGE_PATTERNS);
    }

//    public static void blockCustomDomains(BrowserContext context, List<String> blockedDomains) {
//        context.route("**/*", route -> {
//            Request request = route.request();
//            String requestUrl = request.url().toLowerCase();
//
//            if (containsAny(requestUrl, blockedDomains)) {
//                route.abort();
//                LOGGER.debug("Blocked custom domain request: {}", requestUrl);
//            } else {
//                route.resume();
//            }
//        });
//
//        LOGGER.info("Custom domain blocking enabled: {}", blockedDomains);
//    }
//
//    public static void blockResourceTypes(BrowserContext context, List<String> resourceTypes) {
//        context.route("**/*", route -> {
//            Request request = route.request();
//            String resourceType = request.resourceType();
//
//            if (resourceTypes.contains(resourceType)) {
//                route.abort();
//                LOGGER.debug("Blocked resource type: {} for URL: {}", resourceType, request.url());
//            } else {
//                route.resume();
//            }
//        });
//
//        LOGGER.info("Resource type blocking enabled: {}", resourceTypes);
//    }

    private static boolean containsAny(String text, List<String> patterns) {
        return patterns.stream().anyMatch(text::contains);
    }
}
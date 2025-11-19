package utilities.helpers;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AdBlocker {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdBlocker.class);
    private static final List<String> AD_PATTERNS = List.of(
            "doubleclick",
            "googlesyndication",
            "googleadservices",
            "google-analytics",
            "googletagmanager",
            "googletagservices",
            "adservice",
            "/ads/",
            "/ad/",
            "_ads",
            "analytics",
            "tracking",
            "pagead",
            "adserver",
            "advertisement"
    );

    private static final List<String> IMAGE_AD_PATTERNS = List.of(
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

            boolean isGeneralAd = AD_PATTERNS.stream().anyMatch(requestUrl::contains);

            boolean isImageAd = resourceType.equals("image") &&
                    IMAGE_AD_PATTERNS.stream().anyMatch(requestUrl::contains);

            if (isGeneralAd || isImageAd) {
                LOGGER.debug("Blocked ad request: {}", requestUrl);
                route.abort();
            } else {
                route.resume();
            }
        });
    }

}
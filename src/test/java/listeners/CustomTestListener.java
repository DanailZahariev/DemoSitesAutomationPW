package listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import utilities.ScreenshotHelper;

import java.io.File;

public class CustomTestListener implements ITestListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomTestListener.class);
    private int passCount = 0;
    private int failCount = 0;
    private int skipCount = 0;


    @Override
    public void onTestSuccess(ITestResult result) {
        passCount++;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        failCount++;
        LOGGER.error("❌ FAILED: {}", result.getName());

        String screenshotPath = ScreenshotHelper.captureScreenshot(result.getName());

        if (screenshotPath != null) {
            File screenshot = new File(screenshotPath);
            if (screenshot.exists()) {
                String relativePath = screenshotPath.replace("\\", "/")
                        .replace("target/surefire-reports/", "");

                Reporter.log("<a href='" + relativePath + "' target='_blank'>");
                Reporter.log("<img src='" + relativePath + "' height='400' width='600' style='border:2px solid red;'/>");
                Reporter.log("</a><br>");

                LOGGER.info("\uD83D\uDCF8 Screenshot attached: {}", screenshotPath);
            }
        }

        if (result.getThrowable() != null) {
            Reporter.log("<br><b>Error:</b><br>");
            Reporter.log("<pre>" + result.getThrowable().getMessage() + "</pre>");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        skipCount++;
    }

    @Override
    public void onFinish(ITestContext context) {
        int totalTests = passCount + failCount + skipCount;
        LOGGER.info("\n==========================================");
        LOGGER.info("TestNG Results Summary:");
        LOGGER.debug("Total tests run: {}, Passes: {}, Failures: {}, Skips: {}", totalTests, passCount, failCount, skipCount);
        LOGGER.info("==========================================\n");
    }

    @Override
    public void onStart(ITestContext context) {
        LOGGER.info("Starting Test Suite: {}", context.getName());
    }
}

package listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

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

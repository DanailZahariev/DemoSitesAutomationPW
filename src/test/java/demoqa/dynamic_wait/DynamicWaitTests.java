package demoqa.dynamic_wait;

import com.microsoft.playwright.assertions.LocatorAssertions;
import demoqa.base.BaseTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Test(suiteName = "Dynamic wait Tests")
public class DynamicWaitTests extends BaseTest {

    public void testVisibleAfterButtonText() {
        var dynamicPage = homePage.goToElements().clickDynamicProperties();
        String expectedText = "Visible After 5 Seconds";

        assertThat(dynamicPage.getVisibleAfterText())
                .hasText(expectedText, new LocatorAssertions.HasTextOptions().setTimeout(6000));
    }

    public void testProgressBar() {
        var progressBarPage = homePage.goToWidgets().clickProgressMenu();
        progressBarPage.clickStartButton();
        String expectedValue = "100";

        assertThat(progressBarPage.getProgressValue())
                .hasAttribute("aria-valuenow", expectedValue, new LocatorAssertions.HasAttributeOptions().setTimeout(20000));
    }
}

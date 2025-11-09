package demoqa.dynamic_wait;

import demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(suiteName = "Dynamic wait Tests")
public class DynamicWaitTests extends BaseTest {

    public void testVisibleAfterButtonText() {
        var dynamicPage = homePage.goToElements().clickDynamicProperties();
        String expectedText = "Visible After 5 Seconds";
        String actualText = dynamicPage.getVisibleAfterText();

        Assert.assertEquals(actualText, expectedText, "Text is not correct");
    }

    public void testProgressBar() {
        var progressBarPage = homePage.goToWidgets().clickProgressMenu();
        progressBarPage.clickStartButton();
        String expectedValue = "100";
        String actualValue = progressBarPage.getProgressValue();

        Assert.assertEquals(actualValue, expectedValue, "Progress value is not correct");

    }
}

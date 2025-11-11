package demoqa.widgets;

import demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Test(suiteName = "Auto Complete Tests")
public class AutoCompleteTests extends BaseTest {

    public void testSingleColorOption() {
        var autoCompletePage = homePage.goToWidgets().clickAutoComplete();
        autoCompletePage.selectSingleColor("bl", "Blue");

        assertThat(autoCompletePage.getSingleValueWrapperLocator()).hasText("Blue");
    }

    public void testMultiColorOption() {
        var autoCompletePage = homePage.goToWidgets().clickAutoComplete();
        autoCompletePage.selectMultiColor("bl", "Blue");
        autoCompletePage.selectMultiColor("re", "Red");
        autoCompletePage.selectMultiColor("gr", "Green");

        assertThat(autoCompletePage.getMultiValueLabelsLocator()).hasCount(3);

        autoCompletePage.removeMultiColor("Green");

        assertThat(autoCompletePage.getMultiValueLabelsLocator()).hasCount(2);

        List<String> remainingColors = autoCompletePage.getMultiSelectedValues();
        Assert.assertTrue(remainingColors.contains("Red"));
        Assert.assertTrue(remainingColors.contains("Blue"));
        Assert.assertFalse(remainingColors.contains("Green"));
    }
}

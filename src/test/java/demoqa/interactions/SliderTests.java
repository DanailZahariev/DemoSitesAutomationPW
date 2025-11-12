package demoqa.interactions;

import demoqa.base.BaseTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Test(suiteName = "Slider Tests")
public class SliderTests extends BaseTest {

    public void testSliderResult() {
        var sliderPage = homePage.goToWidgets().clickSlider();
        sliderPage.moveSlider("100");

        assertThat(sliderPage.getSliderResult()).hasAttribute("value", "100");
    }
}

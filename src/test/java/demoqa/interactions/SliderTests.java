package demoqa.interactions;

import demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(suiteName = "Slider Tests")
public class SliderTests extends BaseTest {

    public void testSliderResult() {
        var sliderPage = homePage.goToWidgets().clickSlider();
        sliderPage.moveSlider("100");

        String actual = sliderPage.getSliderValue();
        String expected = "100";

        Assert.assertEquals(actual, expected, "Slider value is not correct");
    }
}

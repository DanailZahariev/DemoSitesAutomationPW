package demoqa.pages.widgets;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SliderPage extends BasePage {

    private final Locator slider;
    private final Locator sliderValue;

    public SliderPage(Page page) {
        super(page);
        this.slider = page.locator("//div[@id='sliderContainer']//input[@type='range']");
        this.sliderValue = page.locator("#sliderValue");
    }

    public void moveSlider(String value) {
        fill(slider, String.valueOf(value));
    }

    public String getSliderValue() {
        return getByAttributeValue(sliderValue, "value");
    }

}

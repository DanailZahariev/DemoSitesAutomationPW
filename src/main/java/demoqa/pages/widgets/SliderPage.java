package demoqa.pages.widgets;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SliderPage extends BasePage {

    private final Locator slider;

    public SliderPage(Page page) {
        super(page);
        this.slider = page.locator("//div[@id='sliderContainer']//input[@type='range']");
    }

    public void moveSlider(String value) {
        fill(slider, String.valueOf(value));
    }

    public Locator getSliderResult() {
        return this.slider;
    }

}

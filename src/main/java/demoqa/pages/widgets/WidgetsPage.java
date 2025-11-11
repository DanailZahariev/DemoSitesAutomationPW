package demoqa.pages.widgets;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import demoqa.pages.home.HomePage;

public class WidgetsPage extends HomePage {

    private final Locator datePickerMenuItem;
    private final Locator selectMenuItem;
    private final Locator progressBarMenuItem;
    private final Locator sliderMenuItem;

    public WidgetsPage(Page page) {
        super(page);
        this.datePickerMenuItem = page.locator("//li[@id='item-2']//span[text()='Date Picker']");
        this.selectMenuItem = page.locator("//li[@id='item-8']//span[text()='Select Menu']");
        this.progressBarMenuItem = page.locator("//li[@id='item-4']//span[text()='Progress Bar']");
        this.sliderMenuItem = page.locator("//li[@id='item-3']//span[text()='Slider']");
    }

    public SliderPage clickSlider() {
        click(sliderMenuItem);
        return new SliderPage(this.page);
    }

    public SelectMenuPage clickSelectMenu() {
        click(selectMenuItem);
        return new SelectMenuPage(this.page);
    }

    public DatePickerPage clickDatePicker() {
        click(datePickerMenuItem);
        return new DatePickerPage(this.page);
    }

    public ProgressBarPage clickProgressMenu() {
        click(progressBarMenuItem);
        return new ProgressBarPage(this.page);
    }
}

package demoqa.pages.widgets;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import demoqa.pages.home.HomePage;

public class WidgetsPage extends HomePage {

    private final Locator datePickerMenuItem;
    private final Locator selectMenuItem;
    private final Locator progressBarMenuItem;

    public WidgetsPage(Page page) {
        super(page);
        this.datePickerMenuItem = page.locator("//li[@id='item-2']//span[text()='Date Picker']");
        this.selectMenuItem = page.locator("//li[@id='item-8']//span[text()='Select Menu']");
        this.progressBarMenuItem = page.locator("//li[@id='item-4']//span[text()='Progress Bar']");
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

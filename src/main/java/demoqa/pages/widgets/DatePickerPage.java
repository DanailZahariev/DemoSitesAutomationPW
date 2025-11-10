package demoqa.pages.widgets;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DatePickerPage extends BasePage {

    private final Locator selectDateField;
    private final Locator monthDropDown;
    private final Locator yearDropDown;

    public DatePickerPage(Page page) {
        super(page);
        this.selectDateField = page.locator("#datePickerMonthYearInput");
        this.monthDropDown = page.locator(".react-datepicker__month-select");
        this.yearDropDown = page.locator(".react-datepicker__year-select");
    }

    private Locator getDayLocator(String day) {
        String xpath = String.format(
                "//div[contains(@class,'react-datepicker__day') and not(contains(@class,'outside-month')) and text()='%s']",
                day
        );
        return this.page.locator(xpath);
    }

    public void clickDay(String day) {
        getDayLocator(day).click();
    }

    public void selectMonth(String month) {
        monthDropDown.selectOption(month);
    }

    public void selectYear(String year) {
        yearDropDown.selectOption(year);
    }

    public void clickSelectDate() {
        selectDateField.click();
    }

    public String getDate() {
        return getValue(selectDateField);
    }

    public boolean isDayInMonth(String day) {
        return getDayLocator(day).isVisible();
    }
}

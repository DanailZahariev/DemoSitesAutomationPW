package demoqa.widgets;

import demoqa.base.BaseTest;
import org.testng.annotations.Test;

import java.time.Month;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Test(suiteName = "Date Tests")
public class DateTests extends BaseTest {

    public void testSelectingDate() {
        String month = "May";
        var monthCast = Month.valueOf(month.toUpperCase()).getValue();
        String expectedMonth = String.format("%02d", monthCast);
        String year = "1988";
        String day = "10";

        var datePickerPage = homePage.goToWidgets().clickDatePicker();
        datePickerPage.clickSelectDate();
        datePickerPage.selectMonth(month);
        datePickerPage.selectYear(year);
        datePickerPage.clickDay(day);

        String expectedDate = expectedMonth + "/" + day + "/" + year;

        assertThat(datePickerPage.getSelectDateFieldLocator()).hasValue(expectedDate);
    }
}

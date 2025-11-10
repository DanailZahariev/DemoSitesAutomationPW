package demoqa.widgets;

import demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Month;

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
        String actualDate = datePickerPage.getDate();

        String expectedDate = expectedMonth + "/" + day + "/" + year;

        Assert.assertEquals(actualDate, expectedDate, "Date is not correct");
    }
}

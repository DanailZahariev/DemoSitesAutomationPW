package demoqa.widgets;

import demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Test(suiteName = "Select Drop Down Tests")
public class SelectDropDownTests extends BaseTest {

    public void testMultiSelectDropDown() {
        var selectMenuPage = homePage.goToWidgets().clickSelectMenu();

        selectMenuPage.setStandardMultiSelect("Volvo");
        selectMenuPage.setStandardMultiSelect(1);
        selectMenuPage.setStandardMultiSelect("Audi");
        selectMenuPage.setStandardMultiSelect(2);
        selectMenuPage.deselectStandardMulti("Saab");

        assertThat(selectMenuPage.getCheckedOptionsLocator()).hasCount(3);

        var actualSelectedOptions = selectMenuPage.getAllSelectedMultiOptions();

        Assert.assertTrue(actualSelectedOptions.contains("Volvo"), "Volvo is not selected");
        Assert.assertTrue(actualSelectedOptions.contains("Audi"), "Audi is not selected");
        Assert.assertTrue(actualSelectedOptions.contains("Opel"), "Opel is not selected");
        Assert.assertFalse(actualSelectedOptions.contains("Saab"), "Saab was not deselected");
    }
}

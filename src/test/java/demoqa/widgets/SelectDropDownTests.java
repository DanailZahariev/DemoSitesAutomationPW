package demoqa.widgets;

import demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(suiteName = "Select Drop Down Tests")
public class SelectDropDownTests extends BaseTest {

    public void testMultiSelectDropDown() {
        var selectMenuPage = homePage.goToWidgets().clickSelectMenu();
        selectMenuPage.setStandardMultiSelect("Volvo");
        selectMenuPage.setStandardMultiSelect(1);
        selectMenuPage.setStandardMultiSelect("Audi");
        selectMenuPage.setStandardMultiSelect(2);
        selectMenuPage.deselectStandardMulti("Saab");
        var actualSelectedOptions = selectMenuPage.getAllSelectedMultiOptions();

        Assert.assertTrue(actualSelectedOptions.contains("Volvo"), "Volvo is not selected");
        Assert.assertFalse(actualSelectedOptions.contains("Saab"), "Volvo is not selected");
        Assert.assertTrue(actualSelectedOptions.contains("Audi"), "Audi is not selected");
        Assert.assertTrue(actualSelectedOptions.contains("Opel"), "Opel is not selected");
    }
}

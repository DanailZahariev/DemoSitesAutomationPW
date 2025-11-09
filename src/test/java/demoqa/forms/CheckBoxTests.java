package demoqa.forms;

import demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(suiteName = "Check Box Tests")
public class CheckBoxTests extends BaseTest {

    public void testCheckBox() {
        var formsPage = homePage.gotoForms().clickPracticeForm();
        formsPage.clickSportCheckBox();
        formsPage.clickReadingCheckBox();
        formsPage.clickMusicCheckBox();
        formsPage.uncheckReadingCheckBox();

        boolean isReadingCheckBoxSelected = formsPage.isReadingCheckBoxSelected();

        Assert.assertFalse(isReadingCheckBoxSelected,
                "Reading checkbox is selected");
    }
}

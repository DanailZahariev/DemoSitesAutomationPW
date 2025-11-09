package demoqa.forms;

import demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(suiteName = "Radio Button Tests")
public class RadioButtonTests extends BaseTest {

    public void testRadioButton() {
        var formsPage = homePage.gotoForms().clickPracticeForm();

        formsPage.clickMusicCheckBox();

        Assert.assertTrue(formsPage.isMusicCheckBoxSelected(),
                "Female radio button is not clicked");
    }
}


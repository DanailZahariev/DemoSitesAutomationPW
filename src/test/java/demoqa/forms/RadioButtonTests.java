package demoqa.forms;

import demoqa.base.BaseTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Test(suiteName = "Radio Button Tests")
public class RadioButtonTests extends BaseTest {

    public void testRadioButton() {
        var formsPage = homePage.gotoForms().clickPracticeForm();

        formsPage.clickMusicCheckBox();

        assertThat(formsPage.getMusicHobbyLocator()).isChecked();
    }
}


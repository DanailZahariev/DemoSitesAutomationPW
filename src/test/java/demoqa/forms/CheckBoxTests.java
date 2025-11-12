package demoqa.forms;

import demoqa.base.BaseTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Test(suiteName = "Check Box Tests")
public class CheckBoxTests extends BaseTest {

    public void testCheckBox() {
        var formsPage = homePage.gotoForms().clickPracticeForm();
        formsPage.clickSportCheckBox();
        formsPage.clickReadingCheckBox();
        formsPage.clickMusicCheckBox();
        formsPage.uncheckReadingCheckBox();

       assertThat(formsPage.getSportHobbyLocator()).isChecked();
       assertThat(formsPage.getReadingHobbyLocator()).not().isChecked();
       assertThat(formsPage.getMusicHobbyLocator()).isChecked();
    }
}

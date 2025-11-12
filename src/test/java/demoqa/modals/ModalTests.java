package demoqa.modals;

import demoqa.base.BaseTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Test(suiteName = "Modal Tests")
public class ModalTests extends BaseTest {


    public void testModal() {
        String expectedText = "small modal";
        var alertFrameWindowPage = homePage.goToAlertFrameWindow();
        var modelDialogsPage = alertFrameWindowPage.clickModalDialogs();
        modelDialogsPage.clickSmallModalButton();

        assertThat(modelDialogsPage.getSmallModalTextLocator()).containsText(expectedText);
        modelDialogsPage.clickCloseSmallModalButton();
    }
}

package demoqa.modals;

import demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(suiteName = "Modal Tests")
public class ModalTests extends BaseTest {


    public void testModal() {
        String expectedText = "small modal";
        var alertFrameWindowPage = homePage.goToAlertFrameWindow();
        var modelDialogsPage = alertFrameWindowPage.clickModalDialogs();
        modelDialogsPage.clickSmallModalButton();

        var actualText = modelDialogsPage.getSmallModalText();

        Assert.assertTrue(actualText.contains(expectedText), "Text is not correct");
        modelDialogsPage.clickCloseSmallModalButton();
    }
}

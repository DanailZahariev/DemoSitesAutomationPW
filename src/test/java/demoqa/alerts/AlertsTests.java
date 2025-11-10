package demoqa.alerts;

import demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(suiteName = "Alerts Tests")
public class AlertsTests extends BaseTest {


    public void testInformationAlert() {
        String expectedAlertText = "You clicked a button";

        var alertsPage = homePage.goToAlertFrameWindow().clickAlerts();

        var actualAlertText = alertsPage.clickInformationAlertAndGetText();

        Assert.assertEquals(actualAlertText, expectedAlertText,
                "\n Actual & expected alert text are not equal\n");
    }

    public void testAcceptConfirmationAlert() {
        String expectedText = "You selected Ok";

        var alertsPage = homePage.goToAlertFrameWindow().clickAlerts();

        alertsPage.clickConfirmationAlertAndAccept();
        String actualText = alertsPage.getConfirmationResult();

        Assert.assertEquals(actualText, expectedText, "\n Actual & expected alert text are not equal\n");
    }

    public void testDismissConfirmationAlert() {
        String expectedText = "You selected Cancel";

        var alertsPage = homePage.goToAlertFrameWindow().clickAlerts();

        alertsPage.clickConfirmationAlertAndDismiss();
        String actualText = alertsPage.getConfirmationResult();

        Assert.assertEquals(actualText, expectedText, "\n Actual & expected alert text are not equal\n");
    }

    public void testPromptAlert() {
        String inputText = "Dani";
        String expectedText = "You entered " + inputText;

        var alertsPage = homePage.goToAlertFrameWindow().clickAlerts();

        alertsPage.clickPromptAlertAndEnterText(inputText);

        String actualText = alertsPage.getPromptAlertText();

        Assert.assertEquals(actualText, expectedText, "\n Actual & expected alert text are not equal\n");
    }
}

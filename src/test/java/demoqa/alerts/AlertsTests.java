package demoqa.alerts;

import demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

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

        assertThat(alertsPage.getConfirmationResult()).hasText(expectedText);
    }

    public void testDismissConfirmationAlert() {
        String expectedText = "You selected Cancel";

        var alertsPage = homePage.goToAlertFrameWindow().clickAlerts();

        alertsPage.clickConfirmationAlertAndDismiss();

        assertThat(alertsPage.getConfirmationResult()).hasText(expectedText);
    }

    public void testPromptAlert() {
        String inputText = "Dani";
        String expectedText = "You entered " + inputText;

        var alertsPage = homePage.goToAlertFrameWindow().clickAlerts();

        alertsPage.clickPromptAlertAndEnterText(inputText);

        assertThat(alertsPage.getPromptAlertText()).hasText(expectedText);
    }
}

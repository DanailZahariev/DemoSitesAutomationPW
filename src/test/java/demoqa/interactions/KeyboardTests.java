package demoqa.interactions;

import demoqa.base.BaseTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Test(suiteName = "Keyboard Tests")
public class KeyboardTests extends BaseTest {

    public void testAppUsingKeyboard() {
        String fullName = "John Doe";
        String email = "johndoe@gmail.com";
        String[] currentAddress = {"123 Selenium Avenue", "Suite 400", "Sofia"};

        var textBoxPage = homePage.goToElements().clickTextBox();

        textBoxPage.submitUserInformation(fullName, email, currentAddress);

        assertThat(textBoxPage.getNameResult()).containsText(fullName);
        assertThat(textBoxPage.getEmailResult()).containsText(email);
        assertThat(textBoxPage.getCurrentAddressResult()).containsText(currentAddress[0]);
        assertThat(textBoxPage.getCurrentAddressResult()).containsText(currentAddress[2]);
    }

}

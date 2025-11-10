package demoqa.interactions;

import demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(suiteName = "Keyboard Tests")
public class KeyboardTests extends BaseTest {

    public void testAppUsingKeyboard() {
        String fullName = "John Doe";
        String email = "johndoe@gmail.com";
        String currentAddress1 = "123 Selenium Avenue";
        String currentAddress2 = "Suite 400";
        String currentAddress3 = "Sofia";

        var textBoxPage = homePage.goToElements().clickTextBox();
        textBoxPage.setFullNameField(fullName);
        textBoxPage.setEmailField(email);
        textBoxPage.setCurrentAddress(currentAddress1);
        textBoxPage.setCurrentAddress(currentAddress2);
        textBoxPage.setCurrentAddress(currentAddress3);
        textBoxPage.clickSubmit();

        String actualName = textBoxPage.getNameResult();
        String actualEmail = textBoxPage.getEmailResult();
        String actualAddress = textBoxPage.getCurrentAddressResult();
        String expectedAddress = String.format("""
                Current Address :%s
                %s
                %s
                \s""", currentAddress1, currentAddress2, currentAddress3);

        Assert.assertTrue(actualName.contains(fullName), "Name is not correct");
        Assert.assertTrue(actualEmail.contains(email), "Email is not correct");
        Assert.assertEquals(actualAddress, expectedAddress, "Address is not correct");
    }

}

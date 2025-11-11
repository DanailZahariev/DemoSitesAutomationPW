package saucedemo.tests.login;

import org.testng.annotations.Test;
import saucedemo.base.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Test(suiteName = "Login Tests")
public class LoginTests extends BaseTest {

    public void testLoginErrorMessage() {
        loginPage.setUsernameField("standard_user");
        loginPage.setPasswordField("invalid_password");
        loginPage.clickLoginButton();

        String expectedMessage = "Epic sadface: Username and password do not match any user in this service";

        assertThat(loginPage.getErrorMessage()).hasText(expectedMessage);
    }
}

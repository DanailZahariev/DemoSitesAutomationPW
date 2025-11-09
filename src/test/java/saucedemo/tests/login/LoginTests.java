package saucedemo.tests.login;

import org.testng.Assert;
import org.testng.annotations.Test;
import saucedemo.base.BaseTest;

@Test(suiteName = "Login Tests")
public class LoginTests extends BaseTest {

    public void testLoginErrorMessage() {
        loginPage.setUsernameField("standard_user");
        loginPage.setPasswordField("invalid_password");
        loginPage.clickLoginButton();

        String expectedMessage = "Epic sadface: Username and password do not match any user in this service";

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, expectedMessage);
    }
}

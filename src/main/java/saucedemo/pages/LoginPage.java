package saucedemo.pages;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    private final Locator usernameField;
    private final Locator passwordField;
    private final Locator loginButton;
    private final Locator errorMessage;

    public LoginPage(Page page) {
        super(page);
        this.usernameField = page.locator("#user-name");
        this.passwordField = page.locator("#password");
        this.loginButton = page.locator("#login-button");
        this.errorMessage = page.locator("#login_button_container h3");
    }

    public void setUsernameField(String username) {
        fill(usernameField, username);
    }

    public void setPasswordField(String password) {
        fill(passwordField, password);
    }

    public ProductPage clickLoginButton() {
        click(loginButton);
        return new ProductPage(this.page);
    }

    public ProductPage loginIntoApplication(String username, String password) {
        setUsernameField(username);
        setPasswordField(password);
        return clickLoginButton();
    }

//    public String getErrorMessage() {
//        return getText(errorMessage);
//    }

    public Locator getErrorMessage() {
        return this.errorMessage;
    }
}

package demoqa.pages.elements;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class TextBoxPage extends BasePage {

    private final Locator fullNameField;
    private final Locator emailField;
    private final Locator currentAddress;
    private final Locator submitBtn;
    private final Locator nameResult;
    private final Locator emailResult;
    private final Locator currentAddressResult;

    public TextBoxPage(Page page) {
        super(page);
        this.fullNameField = page.locator("#userName");
        this.emailField = page.locator("#userEmail");
        this.currentAddress = page.locator("//textarea[@id='currentAddress']");
        this.submitBtn = page.locator("#submit");
        this.nameResult = page.locator("#name");
        this.emailResult = page.locator("#email");
        this.currentAddressResult = page.locator("//p[@id='currentAddress']");
    }

    public void clickSubmit() {
        click(submitBtn);
    }

    public void setFullNameField(String fullName) {
        fill(fullNameField, fullName);
    }

    public void setEmailField(String email) {
        fill(emailField, email);
    }

    public void setCurrentAddress(String address) {
        currentAddress.pressSequentially(address + "\n");
    }

    public void submitUserInformation(String fullName, String email, String... currentAddress) {
        setFullNameField(fullName);
        setEmailField(email);
        for (String address : currentAddress) {
            setCurrentAddress(address);
        }
        clickSubmit();
    }

    public Locator getNameResult() {
        return nameResult;
    }

    public Locator getEmailResult() {
        return emailResult;
    }

    public Locator getCurrentAddressResult() {
        return currentAddressResult;
    }
}
